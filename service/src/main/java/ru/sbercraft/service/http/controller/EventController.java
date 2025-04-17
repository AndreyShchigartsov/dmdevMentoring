package ru.sbercraft.service.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.sbercraft.service.dto.EventCreateEditDto;
import ru.sbercraft.service.service.EventService;

@Controller
@RequestMapping("/event")
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

    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
    public String create(EventCreateEditDto event) {
        return "redirect:/event/" + eventService.create(event).getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        if (!eventService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/event";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id, EventCreateEditDto event) {
        return eventService.update(id, event)
                .map(it -> "redirect:/event/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}