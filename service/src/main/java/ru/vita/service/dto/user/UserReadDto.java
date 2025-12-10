package ru.vita.service.dto.user;

import lombok.Builder;
import lombok.Value;
import ru.vita.service.entity.enums.Role;

import java.time.Instant;

@Value
@Builder
public class UserReadDto {
    Long id;
    String username;
    Instant registrationDate;
    String email;
    Boolean active;
    Role role;
    Integer roomId;
    Integer structureDivisionId;
}
