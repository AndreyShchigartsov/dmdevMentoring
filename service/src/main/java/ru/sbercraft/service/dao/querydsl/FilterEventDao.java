package ru.sbercraft.service.dao.querydsl;

import ru.sbercraft.service.dto.EventFilter;
import ru.sbercraft.service.entity.Event;

import java.util.List;

public interface FilterEventDao {

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
