package ru.vita.service.http.controller.persons.camper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vita.service.entity.enums.PaymentMethod;
import ru.vita.service.service.ExcursionService;

@Controller
@RequestMapping("/camper/excursions")
@RequiredArgsConstructor
public class ExcursionsController {

    private final ExcursionService excursionService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("excursions", excursionService.findAll());
        return "persons/camper/excursion/excursions";
    }

    @GetMapping("/{id}")
    public String getExcursion(Model model, @PathVariable Integer id) {
        model.addAttribute("excursion", excursionService.findById(id));
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "persons/camper/excursion/excursion";
    }
}
