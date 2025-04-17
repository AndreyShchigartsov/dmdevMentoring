package ru.sbercraft.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}