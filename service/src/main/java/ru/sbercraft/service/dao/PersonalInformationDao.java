package ru.sbercraft.service.dao;

import org.hibernate.SessionFactory;
import ru.sbercraft.service.entity.PersonalInformation;

public class PersonalInformationDao extends BaseDao<Integer, PersonalInformation> {

    public PersonalInformationDao(Class<PersonalInformation> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }
}
