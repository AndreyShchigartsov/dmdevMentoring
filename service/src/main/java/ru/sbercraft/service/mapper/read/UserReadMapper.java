package ru.sbercraft.service.mapper.read;

import org.springframework.stereotype.Component;
import ru.sbercraft.service.dto.user.UserReadDto;
import ru.sbercraft.service.entity.User;
import ru.sbercraft.service.mapper.Mapper;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User entity) {
        return UserReadDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .registrationDate(entity.getRegistrationDate())
                .email(entity.getEmail())
                .active(entity.isActive())
                .role(entity.getRole())
//                .roomId(entity.getRoom().getId())
//                .structureDivisionId(entity.getStructureDivision().getId())
                .build();
    }
}
