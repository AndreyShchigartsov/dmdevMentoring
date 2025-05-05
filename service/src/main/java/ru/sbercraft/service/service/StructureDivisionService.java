package ru.sbercraft.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sbercraft.service.dto.structureDivision.StructureDivisionCreateDto;
import ru.sbercraft.service.dto.structureDivision.StructureDivisionReadDto;
import ru.sbercraft.service.mapper.create.StructureDivisionCreateMapper;
import ru.sbercraft.service.mapper.read.StructureDivisionReadMapper;
import ru.sbercraft.service.repository.StructureDivisionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StructureDivisionService {

    private final StructureDivisionRepository repository;

    private final StructureDivisionReadMapper readStructureDivisionMapper;

    private final StructureDivisionCreateMapper createStructureDivisionMapper;

    public StructureDivisionReadDto getStructureDivision(Integer id) {
        return repository.findById(id)
                .map(readStructureDivisionMapper::map)
                .orElse(null);
    }

    public List<StructureDivisionReadDto> getListStructureDivision() {
        return repository.findAll().stream()
                .map(readStructureDivisionMapper::map)
                .collect(Collectors.toList());
//                .toList(); // проверить разницу
    }

    public StructureDivisionReadDto create(StructureDivisionCreateDto division) {
        return null;
    }
}
