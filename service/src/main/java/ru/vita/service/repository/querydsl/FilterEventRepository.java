package ru.vita.service.repository.querydsl;

import ru.vita.service.dto.event.EventFilter;
import ru.vita.service.entity.Event;

import java.util.List;

public interface FilterEventRepository {

    /**
     * @return list всех событий
     */
    List<Event> findAllQueryDsl();

    /**
     * @return событий по id
     */
    Event findByIdQueryDsl(Integer id);

    /**
     * @return list событий по name и category
     */
    List<Event> findAllFilterNameAndCategory(EventFilter filter);

    /**
     * @return list всех Event вместе с Schedule
     */
    List<Event> getEventWithSchedule();
}
