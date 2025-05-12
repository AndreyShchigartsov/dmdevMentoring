package ru.sbercraft.service.dto.structure.division;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import ru.sbercraft.service.entity.enums.Structure;

@Getter
@Builder
public class StructureDivisionCreateEditDto {
    private Integer parentId;
    private Structure typeStructure;
    @Size(min = 4, max = 32)
    private String name;
}
