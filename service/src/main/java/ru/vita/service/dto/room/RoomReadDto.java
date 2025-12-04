package ru.vita.service.dto.room;

import lombok.Builder;
import lombok.Value;
import ru.vita.service.entity.StructureDivision;

@Value
@Builder
public class RoomReadDto {
    Integer id;
    String corps;
    Integer roomNumber;
    Integer seatsValue;
    StructureDivision provinceId;
}
