package com.rightsflow.controller;

import com.rightsflow.dto.AnalyticsDto;
import com.rightsflow.entity.User;
import com.rightsflow.service.FeedbackService;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@Tag(name = "Feedback", description = "Citizen feedback collection and review")
public class FeedbackController {
    public FeedbackController(FeedbackService feedbackService, UserService userService) {
        this.feedbackService = feedbackService;
        this.userService = userService;
    }



    private final FeedbackService feedbackService;
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Submit platform feedback and satisfaction rating")
    public ResponseEntity<AnalyticsDto.FeedbackResponse> submitFeedback(
            @RequestBody AnalyticsDto.FeedbackRequest request,
            Authentication authentication
    ) {
        User user = authentication != null ? userService.findByEmail(authentication.getName()) : null;
        return ResponseEntity.ok(feedbackService.submitFeedback(request, user));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @Operation(summary = "Get all submitted citizen feedback (Admin only)")
    public ResponseEntity<Page<AnalyticsDto.FeedbackResponse>> getAllFeedback(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(feedbackService.getAllFeedback(pageable));
    }
}
