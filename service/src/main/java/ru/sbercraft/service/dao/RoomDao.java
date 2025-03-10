package ru.sbercraft.service.dao;

import org.hibernate.SessionFactory;
import ru.sbercraft.service.entity.Room;

public class RoomDao extends BaseDao<Integer, Room> {

    public RoomDao(SessionFactory sessionFactory) {
        super(Room.class, sessionFactory);
    }
}
