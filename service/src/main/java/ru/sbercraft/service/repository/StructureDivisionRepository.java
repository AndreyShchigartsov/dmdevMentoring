package ru.sbercraft.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sbercraft.service.entity.StructureDivision;
import ru.sbercraft.service.entity.enums.Structure;

import java.util.List;

public interface StructureDivisionRepository extends JpaRepository<StructureDivision, Integer> {

    @Query("select sd from StructureDivision sd where sd.typeStructure = :type_structure")
    List<StructureDivision> findByIdStructure(@Param("type_structure") Structure structure);
}