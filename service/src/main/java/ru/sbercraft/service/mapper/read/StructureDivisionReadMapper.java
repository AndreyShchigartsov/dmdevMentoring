package ru.sbercraft.service.mapper.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sbercraft.service.dto.structure.division.StructureDivisionReadDto;
import ru.sbercraft.service.entity.StructureDivision;
import ru.sbercraft.service.mapper.Mapper;
import ru.sbercraft.service.repository.StructureDivisionRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StructureDivisionReadMapper implements Mapper<StructureDivision, StructureDivisionReadDto> {

    private final StructureDivisionRepository repository;

    @Override
    public StructureDivisionReadDto map(StructureDivision entity) {
        return StructureDivisionReadDto.builder()
                .id(entity.getId())
                .parentId(getStructureDivision(entity.getParent()))
                .typeStructure(entity.getTypeStructure())
                .name(entity.getName())
                .build();
    }

    private StructureDivisionReadDto getStructureDivision(StructureDivision structureDivision) {
        return Optional.ofNullable(structureDivision)
                .map(StructureDivision::getId)
                .flatMap(repository::findById)
                .map(this::map)
                .orElse(null);
    }
}
