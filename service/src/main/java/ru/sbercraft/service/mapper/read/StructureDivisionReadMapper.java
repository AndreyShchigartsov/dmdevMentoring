package ru.sbercraft.service.mapper.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sbercraft.service.dto.structureDivision.StructureDivisionReadDto;
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
                .parentId(getStructureDivision(entity.getParent().getId()))
                .typeStructure(entity.getTypeStructure())
                .name(entity.getName())
                .build();
    }

    private StructureDivision getStructureDivision(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(repository::findById)
                .orElse(null);
    }
}
