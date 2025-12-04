package ru.vita.service.http.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.event.EventCreateEditDto;
import ru.vita.service.dto.event.EventReadDto;
import ru.vita.service.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventRestController {

    private final EventService eventService;

    @GetMapping
    public List<EventReadDto> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventReadDto findById(@PathVariable Integer id) {
        return eventService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public EventReadDto create(@RequestBody EventCreateEditDto event) {
        return eventService.create(event);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        if (!eventService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/event";
    }

    @PutMapping("/{id}")
    public EventReadDto update(@PathVariable Integer id, @RequestBody @Valid EventCreateEditDto event) {
        return eventService.update(id, event)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
