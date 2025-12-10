package ru.vita.service.http.controller.persons.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vita.service.service.UserExcursionService;

@Controller("WorkerUserExcursionController")
@RequestMapping("/worker/user-excursions")
@RequiredArgsConstructor
public class UserExcursionController {

    private final UserExcursionService userExcursionService;

    @GetMapping
    public String getAllNotPaidExcursion(Model model) {
        model.addAttribute("notPaidUserExcursions", userExcursionService.findAllNotPaidExcursion());
        return "persons/worker/userExcursion/user-excursion-not-paid";
    }

    @PostMapping("/payment/{id}")
    public String confirmPayment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (userExcursionService.payExcursionOptimisticLockAndAtomicUpdate(id)) {
            redirectAttributes.addFlashAttribute("payment", "Подтверждение оплаты пройшло успешно!");
        } else {
            redirectAttributes.addFlashAttribute("payment", "Подтверждение оплаты не успешно, перепроверьте статус в ручную");
        }
        return "redirect:/worker/user-excursions";
    }

}
