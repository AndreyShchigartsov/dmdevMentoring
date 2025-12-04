package ru.vita.service.dto.room;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoomCreateEditDto {
    String corps;
    Integer roomNumber;
    Integer seatsValue;
    Integer provinceId;
}
