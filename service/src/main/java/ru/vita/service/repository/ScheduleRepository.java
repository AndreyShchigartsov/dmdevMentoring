package ru.vita.service.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.vita.service.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, QuerydslPredicateExecutor<Schedule> {

    @Override
    @EntityGraph(attributePaths = {"user", "createdUser", "structureDivision"})
    List<Schedule> findAll(Predicate predicate);
}