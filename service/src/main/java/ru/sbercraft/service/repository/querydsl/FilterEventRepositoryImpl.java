package ru.sbercraft.service.repository.querydsl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ru.sbercraft.service.repository.QPredicate;
import ru.sbercraft.service.dto.event.EventFilter;
import ru.sbercraft.service.entity.Event;

import java.util.Collections;
import java.util.List;

import static ru.sbercraft.service.entity.QEvent.event;


@RequiredArgsConstructor
public class FilterEventRepositoryImpl implements FilterEventRepository {

    private final EntityManager entityManager;

    /**
     * @return list всех событий
     */
    public List<Event> findAllQueryDsl() {
        return new JPAQuery<Event>()
                .select(event)
                .from(event)
                .fetch();
    }

    /**
     * @return событий по id
     */
    public Event findByIdQueryDsl(Integer id) {
        return new JPAQuery<Event>()
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

        return new JPAQuery<Event>()
                .select(event)
                .from(event)
                .where(predicate)
                .fetch();
    }

    /**
     * @return list всех Event вместе с Schedule
     */
    public List<Event> getEventWithSchedule() {
//        return new JPAQuery<Event>(entityManager)
//                .select(event)
//                .from(event)
//                .setHint(GraphSemantic.FETCH.getJakartaHintName(), entityManager.getEntityGraph("EventSchedules"))
//                .fetch();
        return Collections.emptyList();
    }
}
