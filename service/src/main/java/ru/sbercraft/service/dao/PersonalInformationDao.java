package ru.sbercraft.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.entity.PersonalInformation;

public interface PersonalInformationDao extends JpaRepository<PersonalInformation, Integer> {

}