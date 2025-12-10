package ru.vita.service.http.controller.persons.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vita.service.dto.PageResponse;
import ru.vita.service.dto.user.UserFilter;
import ru.vita.service.dto.user.UserReadDto;
import ru.vita.service.entity.enums.Role;
import ru.vita.service.service.UserService;

@Controller
@RequestMapping("/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final UserService service;

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("user", service.getUserByUsername(userDetails.getUsername()));
        return "persons/common/home";
    }

    @GetMapping("/campers")
    public String getCampers(Model model, UserFilter filter, Pageable pageable) {
        filter.setRole(Role.CAMPER);
        Page<UserReadDto> users = service.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(users));
        model.addAttribute("filter", filter);
        return "persons/worker/campers";
    }
}
