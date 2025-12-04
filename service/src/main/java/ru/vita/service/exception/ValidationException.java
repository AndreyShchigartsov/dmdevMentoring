package ru.vita.service.exception;

import lombok.Getter;
import ru.vita.service.validation.validator.Error;

import java.util.List;

@Getter
public class ValidationException extends Exception {

    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
