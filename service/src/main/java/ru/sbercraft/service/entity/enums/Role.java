package ru.sbercraft.service.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,             // Администратор (полный доступ ко всем функциям сайта)
    WORKER,            // Работник
    CAMPER,            // Отдыхающий
    USER;              // Пользователь

    @Override
    public String getAuthority() {
        return name();
    }
}