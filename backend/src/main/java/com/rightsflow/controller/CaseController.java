package com.rightsflow.controller;

import com.rightsflow.dto.CaseDto;
import com.rightsflow.entity.User;
import com.rightsflow.service.CaseService;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cases")
@Tag(name = "Case Management", description = "Citizen case tracking, timeline progression, and action plan management")
public class CaseController {
    public CaseController(CaseService caseService, UserService userService) {
        this.caseService = caseService;
        this.userService = userService;
    }



    private final CaseService caseService;
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all cases for the authenticated citizen with pagination and status filter")
    public ResponseEntity<Page<CaseDto.CaseResponse>> getCitizenCases(
            Authentication authentication,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        User user = userService.findByEmail(authentication.getName());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(caseService.getCitizenCases(user.getId(), status, categoryId, query, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get full case details, history timeline, actions checklist, and linked documents")
    public ResponseEntity<CaseDto.CaseResponse> getCaseDetails(
            @PathVariable Long id,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(caseService.getCaseDetails(id, user));
    }

    @PostMapping
    @Operation(summary = "Create a new legal case in PostgreSQL")
    public ResponseEntity<CaseDto.CaseResponse> createCase(
            @RequestBody CaseDto.CaseCreateRequest request,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(caseService.createCase(request, user));
    }

    @PostMapping("/{id}/status")
    @Operation(summary = "Transition case status (e.g. DOCUMENTS_PENDING -> ACTION_TAKEN -> RESOLVED)")
    public ResponseEntity<CaseDto.CaseResponse> transitionStatus(
            @PathVariable Long id,
            @RequestBody CaseDto.StatusTransitionRequest request,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(caseService.transitionStatus(id, request, user));
    }

    @PatchMapping("/actions/{actionId}/status")
    @Operation(summary = "Mark action item as COMPLETED or PENDING in PostgreSQL")
    public ResponseEntity<CaseDto.CaseActionItemDto> toggleAction(
            @PathVariable Long actionId,
            @RequestParam String status,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(caseService.toggleActionStatus(actionId, status, user));
    }
}
