package com.rightsflow.controller;

import com.rightsflow.dto.AnalyticsDto;
import com.rightsflow.entity.User;
import com.rightsflow.service.AnalyticsService;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
@Tag(name = "Analytics", description = "Real-time database aggregated metrics for Citizen, Content Manager, and Admin dashboards")
public class AnalyticsController {
    public AnalyticsController(AnalyticsService analyticsService, UserService userService) {
        this.analyticsService = analyticsService;
        this.userService = userService;
    }



    private final AnalyticsService analyticsService;
    private final UserService userService;

    @GetMapping("/citizen")
    @Operation(summary = "Get dynamic analytics and metrics for authenticated citizen")
    public ResponseEntity<AnalyticsDto.CitizenDashboardStats> getCitizenStats(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(analyticsService.getCitizenDashboardStats(user));
    }

    @GetMapping("/content-manager")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'ADMINISTRATOR')")
    @Operation(summary = "Get content verification and publishing metrics for Content Manager")
    public ResponseEntity<AnalyticsDto.ContentManagerDashboardStats> getContentManagerStats() {
        return ResponseEntity.ok(analyticsService.getContentManagerDashboardStats());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @Operation(summary = "Get platform-wide user growth, case distribution, and AI usage metrics for Administrator")
    public ResponseEntity<AnalyticsDto.AdministratorDashboardStats> getAdminStats() {
        return ResponseEntity.ok(analyticsService.getAdministratorDashboardStats());
    }
}
