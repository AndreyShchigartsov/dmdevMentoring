package ru.vita.service.dto.structure.division;


import lombok.Builder;
import lombok.Value;
import ru.vita.service.entity.enums.Structure;

@Value
@Builder
public class StructureDivisionReadDto {
    Integer id;
    StructureDivisionReadDto parentId;
    Structure typeStructure;
    String name;
}