package ru.vita.service.http.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vita.service.dto.PageResponse;
import ru.vita.service.dto.schedule.ScheduleCreateEditDto;
import ru.vita.service.dto.schedule.ScheduleReadDto;
import ru.vita.service.entity.enums.Status;
import ru.vita.service.service.EventService;
import ru.vita.service.service.ScheduleService;
import ru.vita.service.service.StructureDivisionService;
import ru.vita.service.service.UserService;

@Controller
@RequestMapping("/admin/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;
    private final StructureDivisionService structureDivisionService;
    private final EventService eventService;

    @GetMapping
    public String findAll(Model model, Pageable pageable) {
        Page<ScheduleReadDto> schedules = scheduleService.listSchedules(pageable);
        model.addAttribute("schedules", PageResponse.of(schedules));
        return "admin/schedule/schedules";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("structureDivisions", structureDivisionService.getStructureDivisions());
        model.addAttribute("events", eventService.findAll());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("createdUser", SecurityContextHolder.getContext().getAuthentication().getName());
        return "admin/schedule/create/schedule";
    }

    @PostMapping("/create")
    public String create(RedirectAttributes redirectAttributes,
                                 @Valid ScheduleCreateEditDto scheduleCreateDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/admin/schedules";
        }
        scheduleService.create(scheduleCreateDto);
        return "redirect:/admin/schedules";

    }

    @PostMapping("/{id}/update")
    public String update(RedirectAttributes redirectAttributes,
                                 @Valid ScheduleCreateEditDto scheduleCreateDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/admin/schedule";
        }
        scheduleService.create(scheduleCreateDto);
        return "redirect:/admin/schedule";

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        if(!scheduleService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/admin/schedules";
    }
}