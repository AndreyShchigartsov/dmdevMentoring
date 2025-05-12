package ru.sbercraft.service.mapper.read;

import org.springframework.stereotype.Component;
import ru.sbercraft.service.dto.room.RoomReadDto;
import ru.sbercraft.service.entity.Room;
import ru.sbercraft.service.mapper.Mapper;

@Component
public class RoomReadMapper implements Mapper<Room, RoomReadDto> {
    @Override
    public RoomReadDto map(Room entity) {
        return RoomReadDto.builder()
                .id(entity.getId())
                .corps(entity.getCorps())
                .roomNumber(entity.getRoomNumber())
                .seatsValue(entity.getSeatsValue())
                .provinceId(entity.getStructureDivision())
                .build();
    }
}
