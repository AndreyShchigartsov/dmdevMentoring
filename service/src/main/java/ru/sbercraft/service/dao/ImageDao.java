package ru.sbercraft.service.dao;

import org.hibernate.SessionFactory;
import ru.sbercraft.service.entity.Image;

public class ImageDao extends BaseDao<Integer, Image> {

    public ImageDao(Class<Image> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }
}
