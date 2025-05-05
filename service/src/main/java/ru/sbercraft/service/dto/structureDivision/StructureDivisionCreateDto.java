package ru.sbercraft.service.dto.structureDivision;

import lombok.Builder;
import lombok.Getter;
import ru.sbercraft.service.entity.enums.Structure;

@Getter
@Builder
public class StructureDivisionCreateDto {
    Integer parentId;
    Structure typeStructure;
    String name;
}
