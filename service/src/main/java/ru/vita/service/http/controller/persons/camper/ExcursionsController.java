package ru.vita.service.http.controller.persons.camper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vita.service.service.ExcursionService;

@Controller
@RequestMapping("/worker/excursions")
@RequiredArgsConstructor
public class ExcursionsController {

    private final ExcursionService excursionService;
}
