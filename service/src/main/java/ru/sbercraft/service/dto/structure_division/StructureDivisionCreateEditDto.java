package ru.sbercraft.service.dto.structure_division;

import lombok.Builder;
import lombok.Getter;
import ru.sbercraft.service.entity.enums.Structure;

@Getter
@Builder
public class StructureDivisionCreateEditDto {
    Integer parentId;
    Structure typeStructure;
    String name;
}
