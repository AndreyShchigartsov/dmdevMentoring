package ru.sbercraft.service.validation.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
