package com.dmdev.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static com.dmdev.entity.Provider.*;
import static org.assertj.core.api.Assertions.*;

class ProviderTest {

    @ParameterizedTest
    @MethodSource("companies")
    void checkProvider(String company, Optional<Provider> companyExist) {
        Optional<Provider> byCompany = findByNameOpt(company);
        assertThat(byCompany).isEqualTo(companyExist);
    }

    private static Stream<Arguments> companies() {
        return Stream.of(
                Arguments.of("apple", Optional.of(APPLE)),
                Arguments.of("Sber", Optional.empty())
        );
    }
}