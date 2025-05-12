package ru.sbercraft.service.dto.user;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import ru.sbercraft.service.entity.enums.Role;
import ru.sbercraft.service.validation.UsernameUnique;

@Getter
@Builder
@UsernameUnique
public class UserCreateEditDto {

    @Size(min = 4, max = 64)
    private String username;

    @Size(min = 4, max = 32)
    private String rawPassword;

    private Role role;
}
