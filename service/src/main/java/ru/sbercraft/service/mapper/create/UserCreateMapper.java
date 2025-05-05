package ru.sbercraft.service.mapper.create;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.sbercraft.service.dto.user.UserCreateEditDto;
import ru.sbercraft.service.entity.User;
import ru.sbercraft.service.entity.enums.Role;
import ru.sbercraft.service.mapper.Mapper;

import java.time.Instant;
import java.util.Optional;

@Component
public class UserCreateMapper implements Mapper<UserCreateEditDto, User> {

    private PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);

        return user.builder()
                .username(object.getUsername())
                .password(object.getRawPassword())
                .registrationDate(Instant.now())
                .role(Role.USER)
                .build();
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {

        return Mapper.super.map(fromObject, toObject);
    }
}