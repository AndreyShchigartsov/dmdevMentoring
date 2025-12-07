package ru.vita.service.http.controller.persons.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vita.service.dto.PageResponse;
import ru.vita.service.dto.user.UserFilter;
import ru.vita.service.entity.enums.Role;
import ru.vita.service.entity.enums.Structure;
import ru.vita.service.exception.StructureDivisionNotFoundException;
import ru.vita.service.exception.UserNotFoundException;
import ru.vita.service.service.StructureDivisionService;
import ru.vita.service.service.UserService;

@Controller("WorkerStructureController")
@RequestMapping("/worker/structures")
@RequiredArgsConstructor
public class StructureController {

    private final UserService userService;
    private final StructureDivisionService structureService;

    @GetMapping("/users")
    public String getUsers(Model model, UserFilter filter, Pageable pageable) {
        filter.setRole(Role.CAMPER);
        model.addAttribute("users", PageResponse.of(userService.findAll(filter, pageable)));
        model.addAttribute("filter", filter);
        return "persons/worker/structure/users";
    }

    @GetMapping("/{userId}")
    public String getStructureHaveUser(Model model, @PathVariable Long userId) {
        model.addAttribute("structures", structureService.getStructureDivisions(Structure.GROUP));
        model.addAttribute("userId", userId);
        model.addAttribute("userHaveStructure", structureService.getStructureHaveUser(userId));
        return "persons/worker/structure/addUser";
    }

    @PostMapping("/{userId}/{structureId}")
    public String addUserToStructure(@PathVariable Long userId, @PathVariable Integer structureId, RedirectAttributes attributes) {
        try {
            userService.addUserInStructureDivision(userId, structureId);
        } catch (UserNotFoundException | StructureDivisionNotFoundException e) {
            attributes.addAttribute("errors", new ObjectError("Error", e.getMessage()));
        }
        return "redirect:/worker/structures/" + userId;
    }
}

