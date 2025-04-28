package ru.sbercraft.service.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,             // Администратор (полный доступ ко всем функциям сайта)
    MODERATOR,         // Модератор (доступ к управлению контентом и пользователями)
    EDITOR,            // Редактор (доступ к созданию и редактированию контента)
    USER,              // Пользователь (базовый доступ, например, просмотр и комментирование)
    GUEST              // Гость (минимальный доступ, только просмотр)
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}