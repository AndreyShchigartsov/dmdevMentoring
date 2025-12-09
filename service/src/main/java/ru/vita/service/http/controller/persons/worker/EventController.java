package ru.vita.service.http.controller.persons.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.service.EventService;

@Controller
@RequestMapping("/worker/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "persons/worker/event/events";
    }

    @GetMapping("/{id}")
    public String findAll(Model model, @PathVariable Integer id) {
        return eventService.findById(id)
                .map(event -> {
                    model.addAttribute("event", event);
                    return "persons/worker/event/event";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}