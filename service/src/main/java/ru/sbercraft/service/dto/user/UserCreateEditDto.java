package ru.sbercraft.service.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCreateEditDto {
    private String username;
    private String rawPassword;
}
