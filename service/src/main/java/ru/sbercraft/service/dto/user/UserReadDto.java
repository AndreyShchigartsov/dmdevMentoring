package ru.sbercraft.service.dto.user;

import lombok.Builder;
import lombok.Getter;
import ru.sbercraft.service.entity.enums.Role;

import java.time.Instant;

@Getter
@Builder
public class UserReadDto {
    private Long id;
    private String username;
    private Instant registrationDate;
    private String email;
    private Boolean active;
    private Role role;
    private Integer roomId;
    private Integer structureDivisionId;
}
