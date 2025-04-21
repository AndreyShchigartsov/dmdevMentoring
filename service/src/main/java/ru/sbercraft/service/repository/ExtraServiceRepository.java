package ru.sbercraft.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.repository.querydsl.FilterExtraServiceRepository;
import ru.sbercraft.service.entity.ExtraService;

public interface ExtraServiceRepository extends JpaRepository<ExtraService, Integer>, FilterExtraServiceRepository {

}
