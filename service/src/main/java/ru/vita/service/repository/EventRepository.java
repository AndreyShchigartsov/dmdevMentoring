package ru.vita.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vita.service.repository.querydsl.FilterEventRepository;
import ru.vita.service.entity.Event;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer>, FilterEventRepository {

    Optional<Event> findByName(String name);
}
