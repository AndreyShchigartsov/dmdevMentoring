package ru.vita.service.mapper.read;

import org.springframework.stereotype.Component;
import ru.vita.service.dto.user.UserReadDto;
import ru.vita.service.entity.User;
import ru.vita.service.mapper.Mapper;

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
                //todo доработать
//                .roomId(entity.getRoom().getId())
//                .structureDivisionId(entity.getStructureDivision().getId())
                .build();
    }
}
