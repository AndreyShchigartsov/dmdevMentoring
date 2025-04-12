package ru.sbercraft.service.service;

import org.springframework.stereotype.Component;
import ru.sbercraft.service.dao.querydsl.FilterEventDaoImpl;
import ru.sbercraft.service.entity.Event;

import java.util.Optional;

@Component
public class TestService {

    private final FilterEventDaoImpl eventDao;

    public TestService(FilterEventDaoImpl eventDao) {
        this.eventDao = eventDao;
    }

    public Optional<Event> getEvent(Integer id) {
        return eventDao.findById(id);
    }
}
