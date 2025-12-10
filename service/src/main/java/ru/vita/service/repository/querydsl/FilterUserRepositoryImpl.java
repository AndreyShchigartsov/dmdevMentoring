package ru.vita.service.repository.querydsl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;
import ru.vita.service.dto.user.UserFilter;
import ru.vita.service.entity.User;
import ru.vita.service.repository.QPredicate;

import java.util.List;

import static ru.vita.service.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    /**
     * @return list всех пользователей
     */
    public List<User> findAllQueryDsl() {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .fetch();
    }

    /**
     * @return пользователи по id
     */
    public User findByIdQueryDsl(Long id) {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(user.id.eq(id))
                .fetchOne();
    }

    /**
     * @return list пользователей по username и role
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
     * @return list всех пользователей вместе с Schedule
     */
    public List<User> getUserWithSchedule() {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .setHint(GraphSemantic.FETCH.getJakartaHintName(), entityManager.getEntityGraph("UserSchedules"))
                .fetch();
    }
}
