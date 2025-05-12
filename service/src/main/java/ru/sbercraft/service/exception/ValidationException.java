package ru.sbercraft.service.exception;

import lombok.Getter;
import ru.sbercraft.service.validation.validator.Error;

import java.util.List;

public class ValidationException extends Exception{

    @Getter
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
