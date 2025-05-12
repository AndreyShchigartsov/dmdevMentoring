package ru.sbercraft.service.dto.structure.division;


import lombok.Builder;
import lombok.Getter;
import ru.sbercraft.service.entity.enums.Structure;

@Getter
@Builder
public class StructureDivisionReadDto {
    private Integer id;
    private StructureDivisionReadDto parentId;
    private Structure typeStructure;
    private String name;
}