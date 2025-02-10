package com.dmdev.validator;

import com.dmdev.dto.CreateSubscriptionDto;
import com.dmdev.entity.Provider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class CreateSubscriptionValidatorTest {

    private final CreateSubscriptionValidator createSubscriptionValidator = CreateSubscriptionValidator.getInstance();

    @Test
    void checkThatEverythingIsValid() {
        CreateSubscriptionDto dto = createSubscriptionDto(1, "Andrey", Provider.APPLE.name(), Instant.now().plus(10, ChronoUnit.DAYS));

        ValidationResult actualResult = createSubscriptionValidator.validate(dto);

        assertThat(actualResult.getErrors()).hasSize(0);
    }

    @ParameterizedTest
    @MethodSource("creates")
    void checkNotValidCase(Integer id, String name, String company, Instant expirationDate, Integer countError) {
        CreateSubscriptionDto dto = createSubscriptionDto(id, name, company, expirationDate);

        ValidationResult actualResult = createSubscriptionValidator.validate(dto);

        assertThat(actualResult.getErrors()).hasSize(countError);
    }

    private CreateSubscriptionDto createSubscriptionDto(Integer id, String name, String company, Instant expirationDate) {
        return CreateSubscriptionDto.builder()
                .userId(id)
                .name(name)
                .provider(company)
                .expirationDate(expirationDate)
                .build();
    }

    private static Stream<Arguments> creates() {
        return Stream.of(
                Arguments.of(1, "Andrey", "apple", Instant.now().plus(10, ChronoUnit.DAYS), 0),
                Arguments.of(null, "Andrey", "apple", Instant.now().plus(10, ChronoUnit.DAYS), 1),
                Arguments.of(1, "", "apple", Instant.now().plus(10, ChronoUnit.DAYS), 1),
                Arguments.of(1, "", Provider.APPLE.name(), Instant.now().plus(10, ChronoUnit.DAYS), 1),
                Arguments.of(1, "", "Sber", Instant.now().plus(10, ChronoUnit.DAYS), 2),
                Arguments.of(1, "", "Sber", Instant.now().minus(10, ChronoUnit.DAYS), 3),
                Arguments.of(null, "", "Sber", Instant.now().minus(10, ChronoUnit.DAYS), 4)
        );
    }
}