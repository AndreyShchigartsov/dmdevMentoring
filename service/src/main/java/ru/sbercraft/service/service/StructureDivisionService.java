package ru.sbercraft.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercraft.service.dto.structure.division.StructureDivisionCreateEditDto;
import ru.sbercraft.service.dto.structure.division.StructureDivisionReadDto;
import ru.sbercraft.service.entity.enums.Structure;
import ru.sbercraft.service.mapper.create.StructureDivisionCreateEditMapper;
import ru.sbercraft.service.mapper.read.StructureDivisionReadMapper;
import ru.sbercraft.service.repository.StructureDivisionRepository;
import ru.sbercraft.service.validation.StructureDivisionValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StructureDivisionService {

    private final StructureDivisionRepository repository;

    private final StructureDivisionReadMapper readStructureDivisionMapper;

    private final StructureDivisionCreateEditMapper createStructureDivisionMapper;

    public Optional<StructureDivisionReadDto> getStructureDivision(Integer id) {
        return repository.findById(id)
                .map(readStructureDivisionMapper::map);
    }

    public List<StructureDivisionReadDto> getListStructureDivision() {
        return repository.findAll().stream()
                .map(readStructureDivisionMapper::map)
                .collect(Collectors.toList());
    }

    public List<StructureDivisionReadDto> getSeparateStructure(String structure) {
        return repository.findByIdStructure(Structure.valueOf(structure.toUpperCase())).stream()
                .map(readStructureDivisionMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public StructureDivisionReadDto create(StructureDivisionCreateEditDto divisionDto) {
        return Optional.of(divisionDto)
                .map(createStructureDivisionMapper::map)
                .map(repository::save)
                .map(readStructureDivisionMapper::map)
                .orElse(null);
    }

    @Transactional
    public Optional<StructureDivisionReadDto> update(Integer id, StructureDivisionCreateEditDto divisionDto) {
        return repository.findById(id)
                .map(structure -> createStructureDivisionMapper.map(divisionDto, structure))
                .map(repository::saveAndFlush)
                .map(readStructureDivisionMapper::map);
    }

    @Transactional
    public Optional<StructureDivisionReadDto> delete(Integer id) {
        return repository.findById(id)
                .map(structure -> {
                    repository.delete(structure);
                    repository.flush();
                    return readStructureDivisionMapper.map(structure);
                });
    }
}
