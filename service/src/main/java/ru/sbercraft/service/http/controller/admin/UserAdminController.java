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
import ru.sbercraft.service.dto.user.UserCreateEditDto;
import ru.sbercraft.service.entity.enums.Role;
import ru.sbercraft.service.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserService service;

    @GetMapping("/{id}")
    public String user(Model model, @PathVariable Integer id) {
        return service.getUserById(id).map(user -> {
                model.addAttribute("user", user);
                List<String> roles = new ArrayList<>(List.of(Role.CAMPER.name(), Role.WORKER.name(), Role.USER.name()));
                roles.remove(user.getRole().name());
                model.addAttribute("roles", roles);
                return "admin/user/user";
            }
        ).orElse("redirect:/admin/users");
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", service.getUsers());
        return "admin/user/users";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id, UserCreateEditDto user) {
        return service.update(id, user)
                .map(it -> "redirect:/admin/users")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
