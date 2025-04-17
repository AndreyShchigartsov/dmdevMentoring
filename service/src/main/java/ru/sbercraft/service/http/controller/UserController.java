package ru.sbercraft.service.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("user", "halo");
        return "user/users";
    }
}
