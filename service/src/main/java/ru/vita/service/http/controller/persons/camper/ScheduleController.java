package ru.vita.service.http.controller.persons.camper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vita.service.dto.schedule.ScheduleCreateEditDto;
import ru.vita.service.dto.schedule.ScheduleFilter;
import ru.vita.service.dto.schedule.ScheduleReadDto;
import ru.vita.service.dto.user.UserReadDto;
import ru.vita.service.entity.enums.Status;
import ru.vita.service.service.ScheduleService;
import ru.vita.service.service.StructureDivisionService;
import ru.vita.service.service.UserService;

import java.util.List;

@Controller("CamperScheduleController")
@RequestMapping("/camper/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final StructureDivisionService structureDivisionService;
    private final UserService userService;

    @GetMapping
    public String findFilter(Model model, ScheduleFilter filter) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<ScheduleReadDto> schedules = scheduleService.findByFilter(filter, username);

        if (!schedules.isEmpty()) {
            model.addAttribute("schedules", schedules);
        }
        model.addAttribute("statuses", Status.values());

        return "persons/camper/schedule/schedules";
    }
}
