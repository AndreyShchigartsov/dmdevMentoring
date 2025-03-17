package ru.sbercraft.service.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.sbercraft.service.entity.User;

@Repository
public class UserDao extends BaseDao<Integer, User> {

    private final Session session;

    public UserDao(Session session) {
        super(User.class, session);
        this.session = session;
    }
}
