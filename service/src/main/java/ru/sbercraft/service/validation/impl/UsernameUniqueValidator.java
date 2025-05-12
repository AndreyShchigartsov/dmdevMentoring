package ru.sbercraft.service.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.sbercraft.service.dto.user.UserCreateEditDto;
import ru.sbercraft.service.repository.UserRepository;
import ru.sbercraft.service.validation.UsernameUnique;

@RequiredArgsConstructor
public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, UserCreateEditDto> {

    private final UserRepository repository;

    @Override
    public boolean isValid(UserCreateEditDto dto, ConstraintValidatorContext constraintValidatorContext) {
        return repository.findByUsername(dto.getUsername())
                .isEmpty();
    }
}
