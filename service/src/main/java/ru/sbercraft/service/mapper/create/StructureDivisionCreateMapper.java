package ru.sbercraft.service.mapper.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sbercraft.service.dto.structureDivision.StructureDivisionCreateDto;
import ru.sbercraft.service.entity.StructureDivision;
import ru.sbercraft.service.mapper.Mapper;
import ru.sbercraft.service.repository.StructureDivisionRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StructureDivisionCreateMapper implements Mapper<StructureDivisionCreateDto, StructureDivision> {

    private final StructureDivisionRepository repository;

    @Override
    public StructureDivision map(StructureDivisionCreateDto object) {
        return StructureDivision.builder()
                .parent(getParentStructureDivision(object.getParentId()))
                .typeStructure(object.getTypeStructure())
                .name(object.getName())
                .build();
    }

    private StructureDivision getParentStructureDivision(Integer parentId) {
        return Optional.ofNullable(parentId)
                .flatMap(repository::findById)
                .orElse(null);
    }
}
