package ru.sbercraft.service.dto;

import lombok.Builder;
import lombok.Value;
import ru.sbercraft.service.entity.StructureDivision;

@Value
@Builder
public class ExtraServicesFilter {
    StructureDivision structureDivision;
    String name;
}
