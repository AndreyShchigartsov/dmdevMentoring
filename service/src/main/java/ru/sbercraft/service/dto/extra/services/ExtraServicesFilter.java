package ru.sbercraft.service.dto.extra.services;

import lombok.Builder;
import lombok.Value;
import ru.sbercraft.service.entity.StructureDivision;

@Value
@Builder
public class ExtraServicesFilter {
    StructureDivision structureDivision;
    String name;
}
