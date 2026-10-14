package com.rightsflow.controller;

import com.rightsflow.dto.LegalDto;
import com.rightsflow.service.AuthorityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Authorities & Services", description = "Official Government Authorities, Schemes, and Service steps")
public class AuthorityController {
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }



    private final AuthorityService authorityService;

    @GetMapping("/authorities")
    @Operation(summary = "Search and list official public authorities")
    public ResponseEntity<Page<LegalDto.AuthorityDto>> searchAuthorities(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(authorityService.searchAuthorities(query, pageable));
    }

    @GetMapping("/authorities/{id}")
    @Operation(summary = "Get authority details, verified contacts, address, and offered services")
    public ResponseEntity<LegalDto.AuthorityDto> getAuthorityById(@PathVariable Long id) {
        return ResponseEntity.ok(authorityService.getAuthorityById(id));
    }

    @GetMapping("/services")
    @Operation(summary = "Search citizen legal services and public schemes")
    public ResponseEntity<Page<LegalDto.ServiceDto>> searchServices(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(authorityService.searchServices(query, categoryId, pageable));
    }

    @GetMapping("/services/{id}")
    @Operation(summary = "Get service procedure steps and mandatory document requirements")
    public ResponseEntity<LegalDto.ServiceDto> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(authorityService.getServiceById(id));
    }
}
