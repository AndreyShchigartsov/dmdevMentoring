package ru.sbercraft.service.repository.querydsl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;
import ru.sbercraft.service.dto.event.EventFilter;
import ru.sbercraft.service.dto.user.UserFilter;
import ru.sbercraft.service.entity.Event;
import ru.sbercraft.service.entity.User;
import ru.sbercraft.service.repository.QPredicate;

import java.util.List;

import static ru.sbercraft.service.entity.QEvent.event;
import static ru.sbercraft.service.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    /**
     * @return list всех событий
     */
    public List<User> findAllQueryDsl() {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .fetch();
    }

    /**
     * @return событий по id
     */
    public User findByIdQueryDsl(Long id) {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(user.id.eq(id))
                .fetchOne();
    }

    /**
     * @return list событий по name и category
     */
    public List<User> findAllFilter(UserFilter filter) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getUsername(), user.username::containsIgnoreCase)
                .add(filter.getRole(), user.role::eq)
                .buildAnd();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }

    /**
     * @return list всех Event вместе с Schedule
     */
    public List<User> getEventWithSchedule() {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .setHint(GraphSemantic.FETCH.getJakartaHintName(), entityManager.getEntityGraph("EventSchedules"))
                .fetch();
    }
}
