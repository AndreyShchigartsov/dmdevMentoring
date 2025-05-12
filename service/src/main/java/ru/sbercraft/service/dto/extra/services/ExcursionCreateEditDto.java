package ru.sbercraft.service.dto.extra.services;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExcursionCreateEditDto  {
    @NotNull
    private Integer provinceId;
    @NotNull
    @Size(min = 4, max = 32)
    private String service;
    @NotNull
    @Max(5000)
    private Integer price;
    @NotNull
    @Max(300)
    private Integer duration;
}
