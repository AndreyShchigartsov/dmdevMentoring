package ru.vita.service.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.vita.service.dto.user.UserCreateEditDto;
import ru.vita.service.repository.UserRepository;
import ru.vita.service.validation.UsernameUnique;

@RequiredArgsConstructor
public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, UserCreateEditDto> {

    private final UserRepository repository;

    @Override
    public boolean isValid(UserCreateEditDto dto, ConstraintValidatorContext constraintValidatorContext) {
        return repository.findByUsername(dto.getUsername())
                .isEmpty();
    }
}
