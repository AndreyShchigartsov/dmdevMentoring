package ru.vita.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vita.service.repository.querydsl.FilterExtraServiceRepository;
import ru.vita.service.entity.ExtraService;

public interface ExtraServiceRepository extends JpaRepository<ExtraService, Integer>, FilterExtraServiceRepository {

}
