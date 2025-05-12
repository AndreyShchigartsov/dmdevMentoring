package ru.sbercraft.service.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import ru.sbercraft.service.entity.enums.Role;

@Getter
@Setter
@Builder
public class UserFilter {
    String username;
    Role role;
}
