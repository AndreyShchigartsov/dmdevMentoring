package ru.vita.service.http.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vita.service.dto.PageResponse;
import ru.vita.service.dto.user.UserCreateEditDto;
import ru.vita.service.dto.user.UserFilter;
import ru.vita.service.dto.user.UserReadDto;
import ru.vita.service.entity.enums.Role;
import ru.vita.service.service.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserService service;

    @GetMapping("/{id}")
    public String user(Model model, @PathVariable Long id) {
        return service.getUserById(id).map(user -> {
                    model.addAttribute("user", user);
                    List<Role> roles = Arrays.stream(Role.values())
                            .filter(role -> role != user.getRole())
                            .filter(role -> !role.name().equals("ADMIN"))
                            .toList();
                    model.addAttribute("roles", roles);
                    return "admin/user/user";
                }
        ).orElse("redirect:/admin/users");
    }

//    @GetMapping
//    public String users(Model model) {
//        model.addAttribute("users", service.getUsers());
//        model.addAttribute("roles", Arrays.stream(Role.values()).map(Enum::name).toList());
//        return "admin/user/users";
//    }

    @GetMapping
    public String users(Model model, UserFilter filter, Pageable pageable) {
        Page<UserReadDto> users = service.getUsers(filter, pageable);
        model.addAttribute("users", PageResponse.of(users));
        model.addAttribute("filter", filter);
        model.addAttribute("roles", Arrays.stream(Role.values()).map(Enum::name).toList());
        return "admin/user/users";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//            return "redirect:/admin/users/" + id;
//        }
        return service.update(id, user)
                .map(it -> "redirect:/admin/users")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}