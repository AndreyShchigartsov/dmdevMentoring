package ru.sbercraft.service.dto.structureDivision;


import lombok.Builder;
import lombok.Getter;
import ru.sbercraft.service.entity.StructureDivision;
import ru.sbercraft.service.entity.enums.Structure;

@Getter
@Builder
public class StructureDivisionReadDto {
    private StructureDivision parentId;
    private Structure typeStructure;
    private String name;
}