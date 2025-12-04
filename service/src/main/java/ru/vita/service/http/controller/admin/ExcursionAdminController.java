package ru.vita.service.http.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vita.service.dto.extra.services.ExcursionCreateEditDto;
import ru.vita.service.entity.enums.Structure;
import ru.vita.service.service.ExcursionService;
import ru.vita.service.service.StructureDivisionService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/excursions")
@RequiredArgsConstructor
public class ExcursionAdminController {

    private final ExcursionService excursionService;

    private final StructureDivisionService structureDivisionService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("excursions", excursionService.findAll());
        model.addAttribute("structures", structureDivisionService.getSeparateStructure(Structure.ORGANIZATIONAL.name()));
        return "admin/excursion/excursions";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("parentStructures", structureDivisionService.getStructureDivisions());
        return "admin/excursion/create/excursion";
    }

    @PostMapping("/create")
    public String create(RedirectAttributes redirectAttributes,
                         @Valid ExcursionCreateEditDto excursion,
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
    public String delete(RedirectAttributes redirectAttributes,
                         @PathVariable Integer id) {
        if (!excursionService.delete(id)) {
            redirectAttributes.addFlashAttribute("errors", new ObjectError("NOT_FOUNT_EXCURSION", "Значение не существует"));
            return "redirect:/admin/excursions";
        }
        return "redirect:/admin/excursions";
    }
}
