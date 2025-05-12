package ru.sbercraft.service.http.controller.admin;

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
import ru.sbercraft.service.entity.enums.CategoryEvent;
import ru.sbercraft.service.service.EventService;

import java.util.Arrays;

@Controller
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class EventAdminController {

    private final EventService eventService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("events", eventService.findAll());
        model.addAttribute("categories", Arrays.stream(CategoryEvent.values()).toList());
        return "event/events";
    }

    @GetMapping("/{id}")
    public String findAll(Model model, @PathVariable Integer id) {
        return eventService.findById(id)
                .map(event -> {
                    model.addAttribute("event", event);
                    model.addAttribute("categories", Arrays.stream(CategoryEvent.values()).toList());
                    return "event/event";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public String create(EventCreateEditDto event) {
        eventService.create(event);
        return "redirect:/admin/event";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        if (!eventService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/admin/event";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id, EventCreateEditDto event) {
        return eventService.update(id, event)
                .map(it -> "redirect:/admin/event")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}