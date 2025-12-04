package ru.vita.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.vita.service.dto.ScheduleTest;

@Slf4j
@Service
@RequiredArgsConstructor
public class IntegrationService {

    private final WebClient webClient;

    // GET запрос - получение данных
    public Mono<ScheduleTest> getDate(Long id) {
        return webClient.get()
                .uri("/schedules/{id}", id)
                .retrieve()
                .bodyToMono(ScheduleTest.class)
                .doOnSuccess(user -> log.info("Retrieved user: {}", user))
                .doOnError(error -> log.error("Failed to get user: {}", error.getMessage()));
    }
}
