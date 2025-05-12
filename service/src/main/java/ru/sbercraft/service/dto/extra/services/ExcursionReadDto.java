package ru.sbercraft.service.dto.extra.services;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@Builder
public class ExcursionReadDto {
    private Integer id;
    private String service;
    private Integer price;
    private Long duration;
}
