package ru.sbercraft.service.mapper;

import liquibase.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.sbercraft.service.dto.UserCreateEditDto;
import ru.sbercraft.service.entity.User;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateEditDto object) {
        return null;
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
//        Optional.ofNullable(fromObject.getRawPassword())
//                .filter(StringUtils::hasText)
//                .map(passwordEncoder::encode)
//                .ifPresent(toObject::setPassword);
        return Mapper.super.map(fromObject, toObject);
    }
}
