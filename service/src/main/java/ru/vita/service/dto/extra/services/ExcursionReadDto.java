package ru.vita.service.dto.extra.services;

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
