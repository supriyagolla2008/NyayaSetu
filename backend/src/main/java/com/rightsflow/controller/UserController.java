package com.rightsflow.controller;

import com.rightsflow.dto.AuthDto;
import com.rightsflow.dto.UserDto;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Profile", description = "Citizen profile and preference management")
public class UserController {
    public UserController(UserService userService) {
        this.userService = userService;
    }



    private final UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Get current authenticated user profile")
    public ResponseEntity<UserDto> getCurrentProfile(Authentication authentication) {
        return ResponseEntity.ok(userService.getCurrentUserProfile(authentication.getName()));
    }

    @PutMapping("/profile")
    @Operation(summary = "Update current user profile and preferences")
    public ResponseEntity<UserDto> updateProfile(
            Authentication authentication,
            @RequestBody UserDto.ProfileUpdateRequest request
    ) {
        return ResponseEntity.ok(userService.updateProfile(authentication.getName(), request));
    }

    @PostMapping("/change-password")
    @Operation(summary = "Change current user password")
    public ResponseEntity<Void> changePassword(
            Authentication authentication,
            @Valid @RequestBody AuthDto.PasswordUpdateRequest request
    ) {
        userService.changePassword(authentication.getName(), request);
        return ResponseEntity.ok().build();
    }
}
