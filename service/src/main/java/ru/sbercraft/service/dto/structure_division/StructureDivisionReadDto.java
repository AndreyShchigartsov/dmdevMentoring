package ru.sbercraft.service.dto.structure_division;


import lombok.Builder;
import lombok.Getter;
import ru.sbercraft.service.entity.StructureDivision;
import ru.sbercraft.service.entity.enums.Structure;

@Getter
@Builder
public class StructureDivisionReadDto {
    private Integer id;
    //todo исправить на Integer
    private StructureDivision parentId;
    private Structure typeStructure;
    private String name;
}