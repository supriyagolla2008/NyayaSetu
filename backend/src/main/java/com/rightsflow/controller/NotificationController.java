package com.rightsflow.controller;

import com.rightsflow.dto.UpdateAndSearchDto;
import com.rightsflow.entity.User;
import com.rightsflow.service.NotificationService;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notifications", description = "Smart citizen notifications and case reminders")
public class NotificationController {
    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }



    private final NotificationService notificationService;
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get current user notifications")
    public ResponseEntity<List<UpdateAndSearchDto.NotificationItemDto>> getNotifications(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(notificationService.getUserNotifications(user.getId()));
    }

    @GetMapping("/paged")
    @Operation(summary = "Get paginated user notifications")
    public ResponseEntity<Page<UpdateAndSearchDto.NotificationItemDto>> getNotificationsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(notificationService.getUserNotificationsPaged(user.getId(), pageable));
    }

    @GetMapping("/unread-count")
    @Operation(summary = "Get count of unread notifications")
    public ResponseEntity<Map<String, Long>> getUnreadCount(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(Map.of("unreadCount", notificationService.getUnreadCount(user.getId())));
    }

    @PatchMapping("/{id}/read")
    @Operation(summary = "Mark specific notification as read in PostgreSQL")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        notificationService.markAsRead(id, user.getId());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/read-all")
    @Operation(summary = "Mark all notifications as read")
    public ResponseEntity<Void> markAllAsRead(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        notificationService.markAllAsRead(user.getId());
        return ResponseEntity.ok().build();
    }
}
