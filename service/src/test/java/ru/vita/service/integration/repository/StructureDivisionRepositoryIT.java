package ru.vita.service.integration.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.entity.enums.Structure;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.StructureDivisionRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class StructureDivisionRepositoryIT extends IntegrationTestBase {

    private final StructureDivisionRepository divisionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void getByName() {
        Optional<StructureDivision> structureDivision = divisionRepository.findByName("Sun province");

        assertTrue(structureDivision.isPresent());
        assertEquals(Structure.PROVINCE ,structureDivision.get().getTypeStructure());
    }

    @Test
    void getStructureHaveUser() {
        Optional<StructureDivision> structure = divisionRepository.getStructureHaveUser(1L);

        assertTrue(structure.isPresent());
        assertEquals("Vita", structure.get().getName());
    }

    @Test
    void getAllGroupByStructure() {
        List<StructureDivision> structureDivisions = divisionRepository.listFindByStructure(Structure.GROUP);

        assertEquals(11, structureDivisions.size());
    }

    @Test
    void getStructureHaveByUsername() {
        Optional<StructureDivision> structure = divisionRepository.getStructureHaveByUsername("ValeryCamper");

        entityManager.clear();

        assertTrue(structure.isPresent());
        assertEquals("12", structure.get().getName());
        assertEquals("Sun province", structure.get().getParent().getName());
        assertEquals("Vita", structure.get().getParent().getParent().getName());
    }
}