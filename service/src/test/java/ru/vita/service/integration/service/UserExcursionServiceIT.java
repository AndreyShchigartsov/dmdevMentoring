package ru.vita.service.integration.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.userExcurtion.UserExcursionCreateEditDto;
import ru.vita.service.dto.userExcurtion.UserExcursionReadDto;
import ru.vita.service.entity.enums.PaymentMethod;
import ru.vita.service.entity.enums.PaymentStatus;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.service.UserExcursionService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.vita.service.entity.enums.PaymentStatus.CONFIRMED;

@RequiredArgsConstructor
class UserExcursionServiceIT extends IntegrationTestBase {

    private final UserExcursionService userExcursionService;

    @Test
    void checkThatAllUserExcursionGetOk() {
        List<UserExcursionReadDto> userExcursions = userExcursionService.findAll();

        assertEquals(10, userExcursions.size());
    }

    @Test
    void checkThatGetUserExcursionByIdOk() {
        UserExcursionReadDto userExcursions = userExcursionService.findById(2L);

        assertEquals("Andrey", userExcursions.getUsername());
    }

    @Test
    void checkThatGetAllListNotPaidExcursionOk() {
        List<UserExcursionReadDto> userExcursion = userExcursionService.findAllNotPaidExcursion();

        assertEquals(2, userExcursion.size());
    }

    @Test
    void checkThatUserGetAllMyExcursion() {
        Long userId = 1L;
        List<UserExcursionReadDto> userExcursion = userExcursionService.findAllExcursionByUserId(userId);

        assertEquals(3, userExcursion.size());
    }

    @Test
    void checkThatGetExceptionIfUserIdNotValid() {
        Long userId1 = -1L;
        Long userId2 = null;
        assertThrows(ResponseStatusException.class, () -> userExcursionService.findAllExcursionByUserId(userId1));
        assertThrows(ResponseStatusException.class, () -> userExcursionService.findAllExcursionByUserId(userId2));
    }

    @Test
    void checkThatSuccessfullyCreateUserExcursion() {
        UserExcursionCreateEditDto userExcursionDto = getCreateDto(
                1L,
                6,
                BigDecimal.valueOf(400),
                PaymentStatus.PENDING.name(),
                PaymentMethod.CASH.name(),
                "Умеет смеяться");

        UserExcursionReadDto userExcursionReadDto = userExcursionService.create(userExcursionDto);

        assertEquals(11, userExcursionService.findAll().size());
        assertEquals("Andrey", userExcursionReadDto.getUsername());
        assertEquals("Плавание", userExcursionReadDto.getExcursion());
    }

    @Test
    void checkAllExceptionWhenCreating() {
        assertThrows(ResponseStatusException.class, () -> userExcursionService.create(getCreateDto(-1L, 2, BigDecimal.valueOf(200), PaymentStatus.PENDING.name(), PaymentMethod.CASH.name(), "Умеет смеяться")));
        assertThrows(ResponseStatusException.class, () -> userExcursionService.create(getCreateDto(1L, -2, BigDecimal.valueOf(200), PaymentStatus.PENDING.name(), PaymentMethod.CASH.name(), "Умеет смеяться")));
        assertThrows(ResponseStatusException.class, () -> userExcursionService.create(getCreateDto(99L, 2, BigDecimal.valueOf(200), PaymentStatus.PENDING.name(), PaymentMethod.CASH.name(), "Умеет смеяться")));
        assertThrows(ResponseStatusException.class, () -> userExcursionService.create(getCreateDto(1L, 99, BigDecimal.valueOf(200), PaymentStatus.PENDING.name(), PaymentMethod.CASH.name(), "Умеет смеяться")));
        assertThrows(ResponseStatusException.class, () -> userExcursionService.create(null));
    }

    @Test
    void checkThatUserSuccessfullyRaidExcursion() {
        boolean result = userExcursionService.payExcursionOptimisticLockAndAtomicUpdate(1L);

        assertTrue(result);
        assertEquals(CONFIRMED, userExcursionService.findById(1L).getPaymentStatus());
    }

    @Test
    void checkThatGetExceptionIfUserExcursionNotFound() {
        assertThrows(ResponseStatusException.class, () -> userExcursionService.payExcursionOptimisticLockAndAtomicUpdate(99L));
    }

    @Test
    void checkThatGetExceptionIfExcursionAreRaid() {
        assertThrows(ResponseStatusException.class, () -> userExcursionService.payExcursionOptimisticLockAndAtomicUpdate(3L));
    }

    @Test
    void checkThatAllOkIfPessimisticLock() {
        boolean result = userExcursionService.payExcursionPessimisticLock(1L);

        assertTrue(result);
        assertEquals(CONFIRMED, userExcursionService.findById(1L).getPaymentStatus());
    }

    @Test
    void checkThatGetExceptionIfUserExcursionNotFoundPessimisticLock() {
        assertThrows(ResponseStatusException.class, () -> userExcursionService.payExcursionPessimisticLock(99L));
    }

    @Test
    void checkThatGetExceptionIfExcursionAreRaidPessimisticLock() {
        assertThrows(ResponseStatusException.class, () -> userExcursionService.payExcursionPessimisticLock(3L));
    }

    private UserExcursionCreateEditDto getCreateDto(
            Long userId,
            Integer excursionId,
            BigDecimal paymentPrice,
            String paymentStatus,
            String paymentMethod,
            String notes) {
        return UserExcursionCreateEditDto.builder()
                .userId(userId)
                .excursionId(excursionId)
                .purchasePrice(paymentPrice)
                .paymentStatus(paymentStatus)
                .paymentMethod(paymentMethod)
                .notes(notes)
                .build();
    }
}