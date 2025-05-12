package ru.sbercraft.service.dto.room;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.sbercraft.service.entity.StructureDivision;

@Getter
@Setter
@Builder
public class RoomReadDto {
    private Integer id;
    private String corps;
    private Integer roomNumber;
    private Integer seatsValue;
    private StructureDivision provinceId;
}
