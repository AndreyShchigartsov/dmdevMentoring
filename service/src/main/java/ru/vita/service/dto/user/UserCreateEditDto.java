package ru.vita.service.dto.user;

import jakarta.validation.constraints.Size;
import lombok.Value;
import ru.vita.service.entity.enums.Role;
import ru.vita.service.validation.UsernameUnique;

@Value
@UsernameUnique
public class UserCreateEditDto {

    @Size(min = 4, max = 64)
    String username;

    @Size(min = 4, max = 32)
    String rawPassword;

    Role role;
}
