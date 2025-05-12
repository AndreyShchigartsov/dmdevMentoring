package ru.sbercraft.service.http.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.sbercraft.service.dto.event.EventCreateEditDto;
import ru.sbercraft.service.service.EventService;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "event/events";
    }

    @GetMapping("/{id}")
    public String findAll(Model model, @PathVariable Integer id) {
        return eventService.findById(id)
                .map(event -> {
                    model.addAttribute("evens", event);
                    return "event/event";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}