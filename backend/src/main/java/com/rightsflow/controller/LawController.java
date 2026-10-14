package com.rightsflow.controller;

import com.rightsflow.dto.LegalDto;
import com.rightsflow.service.LawService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/laws")
@Tag(name = "Statutory Laws", description = "Acts of Parliament, State Laws, and Provisions")
public class LawController {
    public LawController(LawService lawService) {
        this.lawService = lawService;
    }



    private final LawService lawService;

    @GetMapping
    @Operation(summary = "Search laws and statutory acts")
    public ResponseEntity<Page<LegalDto.LawDto>> searchLaws(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(lawService.searchLaws(query, "ACTIVE", pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specific statutory law by ID with all sections/provisions")
    public ResponseEntity<LegalDto.LawDto> getLawById(@PathVariable Long id) {
        return ResponseEntity.ok(lawService.getLawById(id));
    }
}
