package com.rightsflow;

import com.rightsflow.ai.ProblemClassificationService;
import com.rightsflow.dto.AuthDto;
import com.rightsflow.entity.Role;
import com.rightsflow.entity.User;
import com.rightsflow.repository.RoleRepository;
import com.rightsflow.repository.UserRepository;
import com.rightsflow.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProblemClassificationService classificationService;

    @BeforeEach
    void setUp() {
        if (roleRepository.findByName("CITIZEN").isEmpty()) {
            roleRepository.save(Role.builder().name("CITIZEN").description("Citizen").build());
        }
        if (roleRepository.findByName("ADMINISTRATOR").isEmpty()) {
            roleRepository.save(Role.builder().name("ADMINISTRATOR").description("Admin").build());
        }
    }

    @Test
    void testCitizenRegistrationAndLogin() {
        AuthDto.RegisterRequest registerRequest = AuthDto.RegisterRequest.builder()
                .fullName("Test Citizen")
                .email("test.citizen@rightsflow.in")
                .password("Password@123")
                .phone("+91 99999 88888")
                .preferredLanguage("en")
                .legalInterests(List.of("Consumer Rights", "Employment & Labour"))
                .build();

        AuthDto.LoginResponse regResponse = authService.register(registerRequest);
        assertNotNull(regResponse);
        assertNotNull(regResponse.getToken());
        assertEquals("test.citizen@rightsflow.in", regResponse.getEmail());
        assertTrue(regResponse.getRoles().contains("CITIZEN"));

        // Test Login
        AuthDto.LoginRequest loginRequest = AuthDto.LoginRequest.builder()
                .email("test.citizen@rightsflow.in")
                .password("Password@123")
                .expectedRole("CITIZEN")
                .build();

        AuthDto.LoginResponse loginResponse = authService.login(loginRequest);
        assertNotNull(loginResponse);
        assertNotNull(loginResponse.getToken());
        assertEquals("test.citizen@rightsflow.in", loginResponse.getEmail());
    }

    @Test
    void testProblemClassification() {
        String query = "My employer has not paid my salary for two months";
        String category = classificationService.classifyCategory(query, null);
        assertEquals("Employment & Labour", category);

        String consumerQuery = "The washing machine I bought online is broken and seller refuses refund";
        String consumerCat = classificationService.classifyCategory(consumerQuery, null);
        assertEquals("Consumer Rights", consumerCat);
    }
}
