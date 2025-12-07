package ru.vita.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vita.service.entity.Excursion;
import ru.vita.service.repository.querydsl.FilterExcursionRepository;

public interface ExcursionRepository extends JpaRepository<Excursion, Integer>, FilterExcursionRepository {

}
