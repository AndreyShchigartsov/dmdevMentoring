package ru.sbercraft.service.dao;

import org.hibernate.SessionFactory;
import ru.sbercraft.service.entity.User;

public class UserDao extends BaseDao<Integer, User> {

    public UserDao(Class<User> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }
}
