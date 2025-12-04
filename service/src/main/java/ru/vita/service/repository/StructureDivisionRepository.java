package ru.vita.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.entity.enums.Structure;

import java.util.List;
import java.util.Optional;

public interface StructureDivisionRepository extends JpaRepository<StructureDivision, Integer> {

    Optional<StructureDivision> findByName(String name);

    @Query("SELECT u.structureDivision FROM User u " +
            "WHERE u.id = :userId")
    Optional<StructureDivision> getStructureHaveUser(Long userId);

    @Query("select sd from StructureDivision sd where sd.typeStructure = :type_structure")
    List<StructureDivision> listFindByStructure(@Param("type_structure") Structure structure);

    @Query("SELECT u.structureDivision FROM User u " +
            "LEFT JOIN FETCH u.structureDivision.parent " +
            "LEFT JOIN FETCH u.structureDivision.parent.parent " +
            "WHERE u.username = :username")
    Optional<StructureDivision> getStructureHaveByUsername(String username);
}