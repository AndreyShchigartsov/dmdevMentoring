package ru.vita.service.integration.mapper.create;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.vita.service.dto.userExcurtion.UserExcursionCreateEditDto;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.mapper.create.UserExcursionCreateEditMapper;

import java.math.BigDecimal;

@RequiredArgsConstructor
class UserExcursionCreateEditMapperIT extends IntegrationTestBase {

    private final UserExcursionCreateEditMapper createEditMapper;

    @Test
    void checkThatGetExceptionIfUserIdNull() {
        UserExcursionCreateEditDto createEditDto = UserExcursionCreateEditDto.builder()
                .userId(null)
                .excursionId(1)
                .build();
    }


    private UserExcursionCreateEditDto getUserExcursionCreateEditDto(
            Long userId,
            Integer excursionId,
            BigDecimal paymentPrice,
            String paymentStatus,
            String paymentMethod,
            String paymentReference,
            String notes) {
        return UserExcursionCreateEditDto.builder()
                .userId(userId)
                .excursionId(excursionId)
                .purchasePrice(paymentPrice)
                .paymentStatus(paymentStatus)
                .paymentMethod(paymentMethod)
                .paymentReference(paymentReference)
                .notes(notes)
                .build();
    }

}