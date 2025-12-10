package ru.vita.service.mapper.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vita.service.dto.room.RoomCreateEditDto;
import ru.vita.service.entity.Room;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.mapper.Mapper;
import ru.vita.service.repository.StructureDivisionRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoomCreateEditMapper implements Mapper<RoomCreateEditDto, Room> {

    private final StructureDivisionRepository repository;

    @Override
    public Room map(RoomCreateEditDto dto) {
        return Room.builder()
                .corps(dto.getCorps())
                .roomNumber(dto.getRoomNumber())
                .seatsValue(dto.getSeatsValue())
                .structureDivision(getStructureDivision(dto.getProvinceId()))
                .build();
    }

    private StructureDivision getStructureDivision(Integer provinceId) {
        return Optional.of(provinceId)
                    .flatMap(repository::findById)
                    .orElse(null);
    }
}
