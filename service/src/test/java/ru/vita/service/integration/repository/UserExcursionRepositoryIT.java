package ru.vita.service.integration.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import ru.vita.service.entity.UserExcursion;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.UserExcursionRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vita.service.entity.enums.PaymentStatus.*;

@RequiredArgsConstructor
class UserExcursionRepositoryIT extends IntegrationTestBase {

    private final UserExcursionRepository userExcursionRepository;

    private final EntityManager entityManager;

    @Test
    void checkThatGetAllUserExcursionByPaymentStatus() {
        List<UserExcursion> userExcursions = userExcursionRepository.findAllByPaymentStatus(CONFIRMED);

        entityManager.clear();
        assertEquals(2, userExcursions.size());
        assertEquals(2L, userExcursions.get(0).getUser().getId());
        assertEquals(0, userExcursions.get(0).getVersion());
        assertThrows(LazyInitializationException.class, () -> userExcursions.get(0).getUser().getUsername());
    }

    @Test
    void checkThatGetAllExcursionHaveUserById() {
        List<UserExcursion> excursions = userExcursionRepository.findAllExcursionByUserId(1L);

        assertEquals(3, excursions.size());
    }

    @Test
    void checkThatGetAllExcursionHaveUserByUsername() {
        String username = "Andrey";
        List<UserExcursion> excursions = userExcursionRepository.findAllExcursionByUsername(username);

        entityManager.clear();
        assertEquals(3, excursions.size());
        assertEquals(1L, excursions.get(0).getUser().getId());
        assertEquals(CANCELLED, excursions.get(0).getPaymentStatus());
        assertThrows(LazyInitializationException.class, () -> excursions.get(0).getUser().getUsername());
    }

    @Test
    void checkThatSuccessfullyUpdateStatusInConfirmed() {
        Long userExcursionId = 1L;
        UserExcursion userExcursionBeforeUpdate = userExcursionRepository.findById(userExcursionId).orElseThrow();

        int result = userExcursionRepository.updateToConfirmedIfPending(1L, userExcursionBeforeUpdate.getVersion());

        entityManager.clear();
        UserExcursion userExcursionAfterUpdate = userExcursionRepository.findById(userExcursionId).orElseThrow();
        assertEquals(1, result);
        assertEquals(userExcursionBeforeUpdate.getVersion() + 1, userExcursionAfterUpdate.getVersion());
        assertEquals(CANCELLED, userExcursionBeforeUpdate.getPaymentStatus());
        assertEquals(CONFIRMED, userExcursionAfterUpdate.getPaymentStatus());
    }

    @Test
    void checkThatFindByIdWithLockOk() {
        UserExcursion ue = userExcursionRepository.findByIdWithLock(1L).orElseThrow();

        assertEquals("не умеет плавать", ue.getNotes());
    }


    @Test
    void checkThatOptimisticLockWorking() {
        Long id = 3L;
        UserExcursion ue = userExcursionRepository.findById(id).orElseThrow();
        Integer originalVersion = ue.getVersion();

        String updateOtherTransaction = "UPDATE user_excursion SET payment_status = 'CANCELLED', version = version + 1 " +
                        "WHERE id = " + 3 + " AND version = " + originalVersion;

        entityManager.createNativeQuery(updateOtherTransaction).executeUpdate();

        // Теперь наш объект ue имеет устаревшую версию
        ue.setPaymentStatus(PENDING);

        // При flush Hibernate обнаружит что версия изменилась
        assertThrows(OptimisticLockException.class, () -> {entityManager.flush();});

        // Обновляем объект из БД
        entityManager.refresh(ue);
        assertThat(ue.getPaymentStatus()).isEqualTo(CANCELLED); // изменение "другой транзакции"
        assertThat(ue.getVersion()).isEqualTo(originalVersion + 1);
    }

}