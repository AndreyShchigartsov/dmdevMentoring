package com.dmdev.validator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ValidationResultTest {

    private final ValidationResult validationResult = new ValidationResult();

    @Test
    void checkThatToValidResultCanAddValue() {
        Error error = Error.of(301, "I am Error");
        validationResult.add(error);

        List<Error> errors = validationResult.getErrors();
        boolean isErrors = validationResult.hasErrors();

        assertThat(errors).hasSize(1);
        assertThat(errors.get(0).getCode()).isEqualTo(error.getCode());
        assertThat(errors.get(0).getMessage()).isEqualTo(error.getMessage());
        assertThat(isErrors).isEqualTo(true);
    }
}