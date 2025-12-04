package ru.vita.service.dto.extra.services;

import lombok.Builder;
import lombok.Value;
import ru.vita.service.entity.StructureDivision;

@Value
@Builder
public class ExtraServicesFilter {
    StructureDivision structureDivision;
    String name;
}
