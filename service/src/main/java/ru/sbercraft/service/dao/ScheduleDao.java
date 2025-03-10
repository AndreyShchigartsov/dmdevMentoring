package ru.sbercraft.service.dao;

import org.hibernate.SessionFactory;
import ru.sbercraft.service.entity.Schedule;

public class ScheduleDao extends BaseDao<Long, Schedule> {

    public ScheduleDao(Class<Schedule> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }
}
