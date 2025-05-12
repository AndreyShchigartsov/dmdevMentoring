package ru.sbercraft.service.dto.user;

import lombok.Builder;
import lombok.Getter;
import ru.sbercraft.service.entity.enums.Role;

@Getter
@Builder
public class UserCreateEditDto {
    private String username;
    private String rawPassword;
    private Role role;
}
