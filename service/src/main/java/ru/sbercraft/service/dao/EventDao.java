package ru.sbercraft.service.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import ru.sbercraft.service.dto.EventFilter;
import ru.sbercraft.service.entity.Event;

import java.util.List;

import static ru.sbercraft.service.entity.QEvent.*;

public class EventDao extends BaseDao<Integer, Event> {

    public EventDao(Class<Event> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }

    /**
     * @return list всех событий
     */
    public List<Event> findAll(Session session) {
        return new JPAQuery<Event>(session)
                .select(event)
                .from(event)
                .fetch();
    }

    /**
     * @return событий по id
     */
    public Event findById(Session session, Integer id) {
        return new JPAQuery<Event>(session)
                .select(event)
                .from(event)
                .where(event.id.eq(id))
                .fetchOne();
    }

    /**
     * @return list событий по name и category
     */
    public List<Event> findAllFilter(Session session, EventFilter filter) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getName(), event.name::eq)
                .add(filter.getCategoryEvent(), event.category::eq)
                .buildAnd();

        return new JPAQuery<Event>(session)
                .select(event)
                .from(event)
                .where(predicate)
                .fetch();
    }

    /**
     * @return list всех Event вместе с Schedule
     */
    public List<Event> getEventWithSchedule(Session session) {
        return new JPAQuery<Event>(session)
                .select(event)
                .from(event)
                .setHint(GraphSemantic.FETCH.getJakartaHintName(), session.getEntityGraph("EventSchedules"))
                .fetch();
    }
}
