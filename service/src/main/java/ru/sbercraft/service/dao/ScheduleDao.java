package ru.sbercraft.service.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.sbercraft.service.entity.Schedule;

@Repository
public class ScheduleDao extends BaseDao<Long, Schedule> {

    private final Session session;

    public ScheduleDao(Session session) {
        super(Schedule.class, session);
        this.session = session;
    }
}
