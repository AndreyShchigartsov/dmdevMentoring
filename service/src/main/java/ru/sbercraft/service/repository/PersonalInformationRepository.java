package ru.sbercraft.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.entity.PersonalInformation;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Integer> {

}