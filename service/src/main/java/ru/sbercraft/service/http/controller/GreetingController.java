package ru.sbercraft.service.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.sbercraft.service.dto.EventCreateEditDto;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"event"})
public class GreetingController {

    @GetMapping("/hello")
    public String hello(Model model, @ModelAttribute("dto") EventCreateEditDto dto) {
        model.addAttribute("event", dto);
        return "greeting/hello";
    }

    @GetMapping("/bye")
    public String bye(Model model) {
        return "greeting/bye";
    }
}
