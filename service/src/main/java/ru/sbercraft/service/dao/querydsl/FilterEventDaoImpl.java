package ru.sbercraft.service.dao.querydsl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;
import ru.sbercraft.service.dao.QPredicate;
import ru.sbercraft.service.dto.EventFilter;
import ru.sbercraft.service.entity.Event;

import java.util.List;

import static ru.sbercraft.service.entity.QEvent.*;

@RequiredArgsConstructor
public class FilterEventDaoImpl implements FilterEventDao {

    private final EntityManager entityManager;

    /**
     * @return list всех событий
     */
    public List<Event> findAllQueryDsl() {
        return new JPAQuery<Event>(entityManager)
                .select(event)
                .from(event)
                .fetch();
    }

    /**
     * @return событий по id
     */
    public Event findByIdQueryDsl(Integer id) {
        return new JPAQuery<Event>(entityManager)
                .select(event)
                .from(event)
                .where(event.id.eq(id))
                .fetchOne();
    }

    /**
     * @return list событий по name и category
     */
    public List<Event> findAllFilter(EventFilter filter) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getName(), event.name::eq)
                .add(filter.getCategoryEvent(), event.category::eq)
                .buildAnd();

        return new JPAQuery<Event>(entityManager)
                .select(event)
                .from(event)
                .where(predicate)
                .fetch();
    }

    /**
     * @return list всех Event вместе с Schedule
     */
    public List<Event> getEventWithSchedule() {
        return new JPAQuery<Event>(entityManager)
                .select(event)
                .from(event)
                .setHint(GraphSemantic.FETCH.getJakartaHintName(), entityManager.getEntityGraph("EventSchedules"))
                .fetch();
    }
}
