package ru.sbercraft.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.repository.querydsl.FilterEventRepository;
import ru.sbercraft.service.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer>, FilterEventRepository {

}
