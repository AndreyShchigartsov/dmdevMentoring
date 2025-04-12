package ru.sbercraft.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.entity.StructureDivision;

public interface StructureDivisionDao extends JpaRepository<StructureDivision, Integer> {

}