package ru.sbercraft.service.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.sbercraft.service.entity.StructureDivision;

@Repository
public class StructureDivisionDao extends BaseDao<Integer, StructureDivision> {

    private final Session session;

    public StructureDivisionDao(Session session) {
        super(StructureDivision.class, session);
        this.session = session;
    }
}
