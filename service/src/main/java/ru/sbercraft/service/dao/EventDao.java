package ru.sbercraft.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.dao.querydsl.FilterEventDao;
import ru.sbercraft.service.entity.Event;

public interface EventDao extends JpaRepository<Event, Integer>, FilterEventDao {

}
