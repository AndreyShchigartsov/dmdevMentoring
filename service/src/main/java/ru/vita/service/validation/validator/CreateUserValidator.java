package ru.vita.service.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vita.service.dto.user.UserCreateEditDto;
import ru.vita.service.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class CreateUserValidator implements Validator<UserCreateEditDto>{

    private final UserRepository repository;

    @Override
    public ValidationResult isValid(UserCreateEditDto object) {
        ValidationResult validationResult = new ValidationResult();
        if (repository.findByUsername(object.getUsername()).isPresent()){
            validationResult.add(Error.of("Ошибка создания","Пользователь уже существует"));
        }
        return validationResult;
    }
}
