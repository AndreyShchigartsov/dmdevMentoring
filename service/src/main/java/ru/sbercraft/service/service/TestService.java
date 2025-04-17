package ru.sbercraft.service.service;

import org.springframework.stereotype.Component;
import ru.sbercraft.service.repository.EventRepository;
import ru.sbercraft.service.entity.Event;

import java.util.Optional;

@Component
public class TestService {

    private final EventRepository eventDao;

    public TestService(EventRepository eventDao) {
        this.eventDao = eventDao;
    }

    public Optional<Event> getEvent(Integer id) {
        return eventDao.findById(id);
    }
}
