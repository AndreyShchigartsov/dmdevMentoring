package ru.sbercraft.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.dao.querydsl.FilterEventDao;

public interface ExtraServiceDao extends JpaRepository<ExtraServiceDao, Integer>, FilterEventDao {

}
