package ru.sbercraft.service.dto.room;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoomCreateEditDto {
    private String corps;
    private Integer roomNumber;
    private Integer seatsValue;
    private Integer provinceId;
}
