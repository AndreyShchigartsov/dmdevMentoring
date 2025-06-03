package ru.sbercraft.service.entity.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Optional;

public enum Role implements GrantedAuthority {
    ADMIN,             // Администратор (полный доступ ко всем функциям сайта)
    WORKER,            // Работник
    CAMPER,            // Отдыхающий
    USER;              // Пользователь

    @Override
    public String getAuthority() {
        return name();
    }

    public static Optional<Role> find(String role) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(role))
                .findFirst();
    }
}