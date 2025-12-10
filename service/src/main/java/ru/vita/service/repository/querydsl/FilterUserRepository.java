package ru.vita.service.repository.querydsl;

import ru.vita.service.dto.user.UserFilter;
import ru.vita.service.entity.User;

import java.util.List;

public interface FilterUserRepository {

    /**
     * @return list всех пользователей
     */
    List<User> findAllQueryDsl();

    /**
     * @return пользователи по id
     */
    User findByIdQueryDsl(Long id);

    /**
     * @return list пользователей по username и role
     */
    List<User> findAllFilter(UserFilter filter);

    /**
     * @return list всех пользователей вместе с Schedule
     */
    List<User> getUserWithSchedule();
}
