package ru.sbercraft.service.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.sbercraft.service.entity.PersonalInformation;

@Repository
public class PersonalInformationDao extends BaseDao<Integer, PersonalInformation> {

    private final Session session;

    public PersonalInformationDao(Session session) {
        super(PersonalInformation.class, session);
        this.session = session;
    }
}
