package ru.vita.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vita.service.dto.structure.division.StructureDivisionCreateEditDto;
import ru.vita.service.dto.structure.division.StructureDivisionReadDto;
import ru.vita.service.entity.enums.Structure;
import ru.vita.service.mapper.create.StructureDivisionCreateEditMapper;
import ru.vita.service.mapper.read.StructureDivisionReadMapper;
import ru.vita.service.repository.StructureDivisionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StructureDivisionService {

    private final StructureDivisionRepository repository;

    private final StructureDivisionCreateEditMapper createMapper;

    private final StructureDivisionReadMapper readMapper;

    public Optional<StructureDivisionReadDto> getStructureDivision(Integer id) {
        return repository.findById(id)
                .map(readMapper::map);
    }

    public List<StructureDivisionReadDto> getStructureDivisions() {
        return repository.findAll().stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }

    public List<StructureDivisionReadDto> getStructureDivisions(Structure type) {
        return repository.listFindByStructure(type).stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }

    public List<StructureDivisionReadDto> getSeparateStructure(String structure) {
        return repository.listFindByStructure(Structure.valueOf(structure.toUpperCase())).stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<StructureDivisionReadDto> getStructureHaveUser(Long userId) {
        return repository.getStructureHaveUser(userId)
                .map(readMapper::map);
    }

    public Optional<StructureDivisionReadDto> getStructureHaveByUsername(String username) {
        return repository.getStructureHaveByUsername(username)
                .map(readMapper::map);
    }

    @Transactional
    public StructureDivisionReadDto create(StructureDivisionCreateEditDto divisionDto) {
        return Optional.of(divisionDto)
                .map(createMapper::map)
                .map(repository::save)
                .map(readMapper::map)
                .orElseThrow(() -> new RuntimeException("Failed to create structure division"));
    }

    @Transactional
    public Optional<StructureDivisionReadDto> update(Integer id, StructureDivisionCreateEditDto divisionDto) {
        return repository.findById(id)
                .map(structure -> createMapper.map(divisionDto, structure))
                .map(repository::saveAndFlush)
                .map(readMapper::map);
    }

    @Transactional
    public Optional<StructureDivisionReadDto> delete(Integer id) {
        return repository.findById(id)
                .map(structure -> {
                    repository.delete(structure);
                    repository.flush();
                    return readMapper.map(structure);
                });
    }
}
