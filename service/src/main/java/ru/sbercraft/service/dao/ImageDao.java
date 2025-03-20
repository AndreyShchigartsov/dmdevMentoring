package ru.sbercraft.service.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.sbercraft.service.entity.Image;

@Repository
public class ImageDao extends BaseDao<Integer, Image> {

    Session session;

    public ImageDao(Session session) {
        super(Image.class, session);
        this.session = session;
    }
}
