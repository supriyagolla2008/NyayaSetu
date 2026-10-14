package com.rightsflow.controller;

import com.rightsflow.dto.AnalyticsDto;
import com.rightsflow.dto.CaseDto;
import com.rightsflow.dto.UserDto;
import com.rightsflow.entity.User;
import com.rightsflow.service.AuditLogService;
import com.rightsflow.service.CaseService;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMINISTRATOR')")
@Tag(name = "Administrator", description = "Platform management, user accounts, audit logs, and security monitoring")
public class AdminController {
    public AdminController(UserService userService, CaseService caseService, AuditLogService auditLogService) {
        this.userService = userService;
        this.caseService = caseService;
        this.auditLogService = auditLogService;
    }



    private final UserService userService;
    private final CaseService caseService;
    private final AuditLogService auditLogService;

    @GetMapping("/users")
    @Operation(summary = "Search and filter all platform users")
    public ResponseEntity<Page<UserDto>> getUsers(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(userService.searchUsers(query, status, pageable));
    }

    @PatchMapping("/users/{id}/status")
    @Operation(summary = "Activate, deactivate, or suspend a user account")
    public ResponseEntity<UserDto> updateUserStatus(
            @PathVariable Long id,
            @RequestBody UserDto.StatusUpdateRequest request,
            Authentication authentication
    ) {
        User admin = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(userService.updateUserStatus(id, request.getStatus(), admin));
    }

    @PatchMapping("/users/{id}/roles")
    @Operation(summary = "Update user roles (RBAC)")
    public ResponseEntity<UserDto> updateUserRoles(
            @PathVariable Long id,
            @RequestBody UserDto.RoleUpdateRequest request,
            Authentication authentication
    ) {
        User admin = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(userService.updateUserRoles(id, request.getRoles(), admin));
    }

    @GetMapping("/cases")
    @Operation(summary = "Monitor and search all citizen cases platform-wide")
    public ResponseEntity<Page<CaseDto.CaseResponse>> getAllCases(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(caseService.getAllCasesForAdmin(status, categoryId, query, pageable));
    }

    @GetMapping("/audit-logs")
    @Operation(summary = "View system security audit logs")
    public ResponseEntity<Page<AnalyticsDto.AuditLogItemDto>> getAuditLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(auditLogService.getAuditLogs(pageable));
    }
}
