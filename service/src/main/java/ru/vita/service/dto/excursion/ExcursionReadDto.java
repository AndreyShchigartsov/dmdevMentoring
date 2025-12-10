package ru.vita.service.dto.excursion;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExcursionReadDto {
    Integer id;
    String service;
    Integer price;
    Long duration;
}
