package com.rightsflow.controller;

import com.rightsflow.dto.UpdateAndSearchDto;
import com.rightsflow.service.LegalUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/legal-updates")
@Tag(name = "Legal Updates", description = "Verified legal amendments and official notices")
public class LegalUpdateController {
    public LegalUpdateController(LegalUpdateService updateService) {
        this.updateService = updateService;
    }



    private final LegalUpdateService updateService;

    @GetMapping
    @Operation(summary = "Search and list verified legal updates")
    public ResponseEntity<Page<UpdateAndSearchDto.LegalUpdateItemDto>> getUpdates(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("effectiveDate").descending());
        return ResponseEntity.ok(updateService.searchUpdates(query, categoryId, "PUBLISHED", pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specific legal update detail")
    public ResponseEntity<UpdateAndSearchDto.LegalUpdateItemDto> getUpdateById(@PathVariable Long id) {
        return ResponseEntity.ok(updateService.getUpdateById(id));
    }
}
