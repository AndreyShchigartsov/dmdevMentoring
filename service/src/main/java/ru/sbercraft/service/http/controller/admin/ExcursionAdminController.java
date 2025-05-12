package ru.sbercraft.service.http.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import ru.sbercraft.service.dto.extra.services.ExcursionCreateEditDto;
import ru.sbercraft.service.entity.enums.Structure;
import ru.sbercraft.service.service.ExcursionService;
import ru.sbercraft.service.service.StructureDivisionService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/excursions")
@RequiredArgsConstructor
public class ExcursionAdminController {

    private final ExcursionService excursionService;
    private final StructureDivisionService structureDivisionService;

    @GetMapping
    public String getExcursions(Model model) {
        model.addAttribute("excursions", excursionService.getExcursions());
        model.addAttribute("structures", structureDivisionService.getSeparateStructure(Structure.ORGANIZATIONAL.name()));
        return "admin/excursion/excursions";
    }

    @GetMapping("/create")
    public String getExcursion(Model model) {
        model.addAttribute("parentStructures", structureDivisionService.getListStructureDivision());
        return "admin/excursion/excursion";
    }

    @PostMapping("/create")
    public String create(RedirectAttributes redirectAttributes,
                         @Validated ExcursionCreateEditDto excursion,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/admin/excursions/create";
        }

        return Optional.of(excursionService.create(excursion))
                .map(user -> "redirect:/admin/excursions")
                .orElseThrow();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        if (!excursionService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/admin/excursions";
    }
}
