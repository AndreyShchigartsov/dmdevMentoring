package ru.vita.service.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.structure.division.StructureDivisionReadDto;
import ru.vita.service.service.StructureDivisionService;

import java.util.List;

@RequestMapping("/api/v1/structure")
@RequiredArgsConstructor
public class StructureDivisionRestController {

    private final StructureDivisionService service;

    @GetMapping
    public ResponseEntity<StructureDivisionReadDto> getStructureDivision(Integer id) {
        return service.getStructureDivision(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<StructureDivisionReadDto>> getListStructureDivision() {
        return ResponseEntity.ok(service.getStructureDivisions());
    }
}
