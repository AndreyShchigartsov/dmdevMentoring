package ru.vita.service.mapper.create;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.vita.service.dto.user.UserCreateEditDto;
import ru.vita.service.entity.User;
import ru.vita.service.entity.enums.Role;
import ru.vita.service.mapper.Mapper;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
        user.setUsername(object.getUsername());
        user.setRegistrationDate(Instant.now());
        user.setRole(Role.USER);

        return user;
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
            toObject.setUsername(fromObject.getUsername());
            toObject.setRole(fromObject.getRole());
            return toObject;
    }
}