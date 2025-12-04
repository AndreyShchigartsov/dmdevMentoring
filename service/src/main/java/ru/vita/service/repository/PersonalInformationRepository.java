package ru.vita.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vita.service.entity.PersonalInformation;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Integer> {

}