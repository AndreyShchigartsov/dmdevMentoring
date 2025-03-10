package ru.sbercraft.service.dao;

import org.hibernate.SessionFactory;
import ru.sbercraft.service.entity.StructureDivision;

public class StructureDivisionDao extends BaseDao<Integer, StructureDivision> {

    public StructureDivisionDao(Class<StructureDivision> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }
}
