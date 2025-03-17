package ru.sbercraft.service.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.sbercraft.service.entity.Room;

@Repository
public class RoomDao extends BaseDao<Integer, Room> {

    private final Session session;

    public RoomDao(Session session) {
        super(Room.class, session);
        this.session = session;
    }
}
