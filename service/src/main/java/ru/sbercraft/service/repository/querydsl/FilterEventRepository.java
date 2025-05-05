package ru.sbercraft.service.repository.querydsl;

import ru.sbercraft.service.dto.event.EventFilter;
import ru.sbercraft.service.entity.Event;

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
    List<Event> findAllFilter(EventFilter filter);

    /**
     * @return list всех Event вместе с Schedule
     */
    List<Event> getEventWithSchedule();
}
