package ru.vita.service.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vita.service.dto.structure.division.StructureDivisionReadDto;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.service.StructureDivisionService;

import java.util.Optional;

@RequiredArgsConstructor
class StructureDivisionServiceIT extends IntegrationTestBase {

    private final StructureDivisionService structureService;

    @Test
    void checkThatGetServiceWhichHaveUsername() {
        Optional<StructureDivisionReadDto> structure = structureService.getStructureHaveByUsername("Andrey");

        Assertions.assertTrue(structure.isPresent());
        Assertions.assertEquals( "Vita", structure.get().getName());
    }
}