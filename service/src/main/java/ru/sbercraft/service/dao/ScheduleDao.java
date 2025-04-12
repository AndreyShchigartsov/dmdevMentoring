package ru.sbercraft.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.entity.Schedule;

public interface ScheduleDao extends JpaRepository<Schedule, Long> {

}