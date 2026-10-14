package com.rightsflow.service;

import com.rightsflow.dto.AuthDto;
import com.rightsflow.entity.Role;
import com.rightsflow.entity.User;
import com.rightsflow.entity.UserPreference;
import com.rightsflow.exception.BadRequestException;
import com.rightsflow.exception.UnauthorizedException;
import com.rightsflow.repository.RoleRepository;
import com.rightsflow.repository.UserPreferenceRepository;
import com.rightsflow.repository.UserRepository;
import com.rightsflow.security.JwtUtil;
import com.rightsflow.security.SecurityUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthService.class);

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, UserPreferenceRepository userPreferenceRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuditLogService auditLogService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userPreferenceRepository = userPreferenceRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.auditLogService = auditLogService;
    }



    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserPreferenceRepository userPreferenceRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuditLogService auditLogService;

    @Transactional
    public AuthDto.LoginResponse login(AuthDto.LoginRequest request) {
        log.info("Attempting authentication for email: {}", request.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUser userPrincipal = (SecurityUser) authentication.getPrincipal();

        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(r -> r.replace("ROLE_", ""))
                .collect(Collectors.toList());

        // Validate role if expectedRole was specified from the role-specific login page
        if (request.getExpectedRole() != null && !request.getExpectedRole().isBlank()) {
            String requiredRole = request.getExpectedRole().trim().toUpperCase();
            if (!roles.contains(requiredRole)) {
                throw new UnauthorizedException(String.format(
                        "Your account does not have permission to log in as %s. Verified roles: %s",
                        requiredRole, roles
                ));
            }
        }

        String jwt = jwtUtil.generateToken(authentication);

        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new BadRequestException("User profile not found"));

        auditLogService.logAction(user, "USER_LOGIN", "USER", user.getId().toString(), "User logged in successfully");

        return AuthDto.LoginResponse.builder()
                .token(jwt)
                .type("Bearer")
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .preferredLanguage(user.getPreferredLanguage())
                .stateLocation(user.getStateLocation())
                .roles(roles)
                .build();
    }

    @Transactional
    public AuthDto.LoginResponse register(AuthDto.RegisterRequest request) {
        log.info("Registering new citizen: {}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("An account with this email already exists.");
        }

        Role citizenRole = roleRepository.findByName("CITIZEN")
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .name("CITIZEN")
                        .description("Citizen user")
                        .build()));

        User user = User.builder()
                .fullName(request.getFullName().trim())
                .email(request.getEmail().trim().toLowerCase())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .preferredLanguage(request.getPreferredLanguage() != null ? request.getPreferredLanguage() : "en")
                .stateLocation(request.getStateLocation())
                .status("ACTIVE")
                .roles(new HashSet<>(Collections.singletonList(citizenRole)))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        // Save Preferences
        String interests = request.getLegalInterests() != null ? String.join(",", request.getLegalInterests()) : "General Legal Rights";
        UserPreference pref = UserPreference.builder()
                .user(savedUser)
                .legalInterests(interests)
                .emailNotifications(true)
                .caseUpdates(true)
                .legalAlerts(true)
                .voiceAssistantEnabled(true)
                .build();
        userPreferenceRepository.save(pref);

        auditLogService.logAction(savedUser, "USER_REGISTER", "USER", savedUser.getId().toString(), "New citizen account registered");

        // Authenticate immediately
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        return AuthDto.LoginResponse.builder()
                .token(jwt)
                .type("Bearer")
                .id(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .preferredLanguage(savedUser.getPreferredLanguage())
                .stateLocation(savedUser.getStateLocation())
                .roles(List.of("CITIZEN"))
                .build();
    }
}
