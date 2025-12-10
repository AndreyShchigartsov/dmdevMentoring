package ru.vita.service.dto.excursion;

import lombok.Builder;
import lombok.Value;
import ru.vita.service.entity.StructureDivision;

@Value
@Builder
public class ExcursionsFilter {
    StructureDivision structureDivision;
    String name;
}
