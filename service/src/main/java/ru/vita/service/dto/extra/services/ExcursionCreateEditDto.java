package ru.vita.service.dto.extra.services;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExcursionCreateEditDto  {

    @NotNull
    Integer provinceId;

    @NotNull
    @Size(min = 4, max = 32)
    String service;

    @NotNull
    @Max(5000)
    Integer price;

    @NotNull
    @Max(300)
    Integer duration;
}
