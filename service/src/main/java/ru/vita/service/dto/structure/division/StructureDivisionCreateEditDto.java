package ru.vita.service.dto.structure.division;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import ru.vita.service.entity.enums.Structure;

@Value
@Builder
public class StructureDivisionCreateEditDto {
    Integer parentId;
    Structure typeStructure;
    @Size(min = 4, max = 32)
    String name;
}
