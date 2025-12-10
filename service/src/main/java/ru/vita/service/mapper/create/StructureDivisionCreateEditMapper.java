package ru.vita.service.mapper.create;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vita.service.dto.structure.division.StructureDivisionCreateEditDto;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.mapper.Mapper;
import ru.vita.service.repository.StructureDivisionRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StructureDivisionCreateEditMapper implements Mapper<StructureDivisionCreateEditDto, StructureDivision> {

    private final StructureDivisionRepository repository;

    @Override
    public StructureDivision map(StructureDivisionCreateEditDto object) {
        return StructureDivision.builder()
                .parent(getParentStructureDivision(object.getParentId()))
                .typeStructure(object.getTypeStructure())
                .name(object.getName())
                .build();
    }

    @Override
    public StructureDivision map(StructureDivisionCreateEditDto fromObject, StructureDivision toObject) {
        toObject.setParent(getParentStructureDivision(fromObject.getParentId()));
        toObject.setTypeStructure(fromObject.getTypeStructure());
        toObject.setName(fromObject.getName());
        return toObject;
    }

    private StructureDivision getParentStructureDivision(Integer parentId) {
        return Optional.ofNullable(parentId)
                .flatMap(repository::findById)
                .orElse(null);
    }
}
