package ru.vita.service.validation.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
