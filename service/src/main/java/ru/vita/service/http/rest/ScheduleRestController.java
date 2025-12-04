package ru.vita.service.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.vita.service.dto.ScheduleTest;
import ru.vita.service.service.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleRestController {

    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ScheduleTest>> find(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.integrationSchedules(id));
    }
}
