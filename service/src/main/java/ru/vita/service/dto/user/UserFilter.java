package ru.vita.service.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.vita.service.entity.enums.Role;

@Getter
@Setter
@Builder
public class UserFilter {
    String username;
    Role role;
}
