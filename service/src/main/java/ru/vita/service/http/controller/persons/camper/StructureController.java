package ru.vita.service.http.controller.persons.camper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vita.service.service.StructureDivisionService;

@Controller("CamperStructureController")
@RequestMapping("camper/structure")
@RequiredArgsConstructor
public class StructureController {

    private final StructureDivisionService divisionService;

    @GetMapping
    public String getStructure(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        divisionService.getStructureHaveByUsername(userDetails.getUsername())
                        .ifPresent(struture -> model.addAttribute("structure", struture));
        return "persons/camper/structure/haveStructure";
    }
}
