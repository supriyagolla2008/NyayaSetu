package com.rightsflow.service;

import com.rightsflow.dto.AuthDto;
import com.rightsflow.dto.UserDto;
import com.rightsflow.entity.Role;
import com.rightsflow.entity.User;
import com.rightsflow.entity.UserPreference;
import com.rightsflow.exception.BadRequestException;
import com.rightsflow.exception.ResourceNotFoundException;
import com.rightsflow.repository.RoleRepository;
import com.rightsflow.repository.UserPreferenceRepository;
import com.rightsflow.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserPreferenceRepository preferenceRepository, PasswordEncoder passwordEncoder, AuditLogService auditLogService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.preferenceRepository = preferenceRepository;
        this.passwordEncoder = passwordEncoder;
        this.auditLogService = auditLogService;
    }



    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserPreferenceRepository preferenceRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditLogService auditLogService;

    @Transactional(readOnly = true)
    public UserDto getCurrentUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return mapToUserDto(user);
    }

    @Transactional
    public UserDto updateProfile(String email, UserDto.ProfileUpdateRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        if (request.getFullName() != null && !request.getFullName().isBlank()) {
            user.setFullName(request.getFullName().trim());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getPreferredLanguage() != null) {
            user.setPreferredLanguage(request.getPreferredLanguage());
        }
        if (request.getStateLocation() != null) {
            user.setStateLocation(request.getStateLocation());
        }

        User savedUser = userRepository.save(user);

        UserPreference pref = preferenceRepository.findByUserId(user.getId())
                .orElseGet(() -> UserPreference.builder().user(savedUser).build());

        if (request.getLegalInterests() != null) {
            pref.setLegalInterests(String.join(",", request.getLegalInterests()));
        }
        if (request.getEmailNotifications() != null) {
            pref.setEmailNotifications(request.getEmailNotifications());
        }
        if (request.getCaseUpdates() != null) {
            pref.setCaseUpdates(request.getCaseUpdates());
        }
        if (request.getLegalAlerts() != null) {
            pref.setLegalAlerts(request.getLegalAlerts());
        }
        preferenceRepository.save(pref);

        auditLogService.logAction(savedUser, "PROFILE_UPDATED", "USER", savedUser.getId().toString(), "User updated profile details");
        return mapToUserDto(savedUser);
    }

    @Transactional
    public void changePassword(String email, AuthDto.PasswordUpdateRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Current password does not match.");
        }

        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        auditLogService.logAction(user, "PASSWORD_CHANGED", "USER", user.getId().toString(), "User changed account password");
    }

    // Admin: List & Search Users
    @Transactional(readOnly = true)
    public Page<UserDto> searchUsers(String query, String status, Pageable pageable) {
        if (query != null && !query.trim().isEmpty()) {
            return userRepository.searchUsers(query.trim(), status, pageable).map(this::mapToUserDto);
        }
        return userRepository.findFiltered(status, pageable).map(this::mapToUserDto);
    }

    // Admin: Update Status (Active / Inactive)
    @Transactional
    public UserDto updateUserStatus(Long userId, String newStatus, User adminUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setStatus(newStatus.toUpperCase());
        User updated = userRepository.save(user);
        auditLogService.logAction(adminUser, "USER_STATUS_UPDATED", "USER", userId.toString(), "Status changed to " + newStatus);
        return mapToUserDto(updated);
    }

    // Admin: Update User Roles
    @Transactional
    public UserDto updateUserRoles(Long userId, List<String> roleNames, User adminUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName.toUpperCase())
                    .orElseThrow(() -> new BadRequestException("Role not found: " + roleName));
            roles.add(role);
        }

        user.setRoles(roles);
        User updated = userRepository.save(user);
        auditLogService.logAction(adminUser, "USER_ROLES_UPDATED", "USER", userId.toString(), "Roles updated to " + roleNames);
        return mapToUserDto(updated);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    private UserDto mapToUserDto(User user) {
        List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        UserPreference pref = preferenceRepository.findByUserId(user.getId()).orElse(null);

        List<String> interests = new ArrayList<>();
        if (pref != null && pref.getLegalInterests() != null) {
            interests = Arrays.asList(pref.getLegalInterests().split(","));
        }

        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .preferredLanguage(user.getPreferredLanguage())
                .stateLocation(user.getStateLocation())
                .status(user.getStatus())
                .roles(roles)
                .legalInterests(interests)
                .emailNotifications(pref != null ? pref.getEmailNotifications() : true)
                .caseUpdates(pref != null ? pref.getCaseUpdates() : true)
                .legalAlerts(pref != null ? pref.getLegalAlerts() : true)
                .createdAt(user.getCreatedAt())
                .build();
    }
}
