package ru.vita.service.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vita.service.dto.structure.division.StructureDivisionReadDto;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.entity.enums.Structure;
import ru.vita.service.mapper.create.StructureDivisionCreateEditMapper;
import ru.vita.service.mapper.read.StructureDivisionReadMapper;
import ru.vita.service.repository.StructureDivisionRepository;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class StructureDivisionServiceTest {



    @Mock
    private StructureDivisionRepository structureRepository;

    @Mock
    private StructureDivisionCreateEditMapper createMapper;

    @Mock
    private StructureDivisionReadMapper readMapper;

    @InjectMocks
    private StructureDivisionService structureService;

    @Test
    void checkThatGetServiceWhichHaveUsername() {
        StructureDivision entity = StructureDivision.builder()
                .name("Vita")
                .typeStructure(Structure.ORGANIZATIONAL)
                .build();
        doReturn(Optional.of(entity)).when(structureRepository).getStructureHaveByUsername("Andrey");
        doReturn(StructureDivisionReadDto.builder().name("Vita").typeStructure(Structure.ORGANIZATIONAL).build()).when(readMapper).map(entity);

        Optional<StructureDivisionReadDto> structure = structureService.getStructureHaveByUsername("Andrey");

        Assertions.assertTrue(structure.isPresent());
        Assertions.assertEquals( "Vita", structure.get().getName());
    }
}