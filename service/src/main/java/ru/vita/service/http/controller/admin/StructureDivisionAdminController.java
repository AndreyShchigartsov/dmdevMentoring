package ru.vita.service.http.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vita.service.dto.structure.division.StructureDivisionCreateEditDto;
import ru.vita.service.dto.structure.division.StructureDivisionReadDto;
import ru.vita.service.entity.enums.Structure;
import ru.vita.service.service.StructureDivisionService;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.*;

@Controller
@RequestMapping("/admin/structures")
@RequiredArgsConstructor
public class StructureDivisionAdminController {

    private final StructureDivisionService service;

    @GetMapping("/{structure}")
    public String getOrganizations(Model model, @PathVariable String structure) {
        model.addAttribute("nameStructure", structure);
        model.addAttribute("parentStructures", getParentStructure(structure));
        model.addAttribute("structures", service.getStructureDivisions(Structure.valueOf(structure.toUpperCase())));
        return "admin/structure/structure";
    }

    @GetMapping
    public String getStructures(Model model) {
        model.addAttribute("structures", Arrays.stream(Structure.values()).map(structure -> structure.name().toLowerCase()).toList());
        return "admin/structure/structures";
    }

    @PostMapping
    public String create(@Valid StructureDivisionCreateEditDto structure,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/admin/structures/" + structure.getTypeStructure().name().toLowerCase();
        }
        try {
            service.create(structure);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errors", new ObjectError("Ошибка", "Ошибка сохранения!"));
        }
        return "redirect:/admin/structures/" + structure.getTypeStructure().name().toLowerCase();
    }

    @PostMapping("/{id}/update")
    public String update(Integer id, StructureDivisionCreateEditDto structure) {
        service.update(id, structure);
        return "redirect:/admin/structures/" + structure.getTypeStructure().name().toLowerCase();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        return service.delete(id).map(
                structure -> "redirect:/admin/structures/" + structure.getTypeStructure().name().toLowerCase()
        ).orElse(null);
    }

    //стоит ли это вынести на уровень сервиса или в контроллере тоже можно подобное делать?
    private List<StructureDivisionReadDto> getParentStructure(String structure) {
        List<String> structures = Arrays.stream(Structure.values()).map(str -> str.name().toLowerCase()).toList();
        int index = structures.indexOf(structure);
        index--;
        if (index < 0) {
            return emptyList();
        } else {
            return service.getStructureDivisions(Structure.valueOf(structures.get(index).toUpperCase()));
        }
    }
}
