package com.rightsflow;

import com.rightsflow.entity.Role;
import com.rightsflow.entity.User;
import com.rightsflow.repository.RoleRepository;
import com.rightsflow.repository.UserRepository;
import com.rightsflow.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class SecurityRbacTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private String citizenToken;
    private String adminToken;
    private String contentManagerToken;

    @BeforeEach
    void setUp() {
        Role citizenRole = roleRepository.findByName("CITIZEN")
                .orElseGet(() -> roleRepository.save(Role.builder().name("CITIZEN").description("Citizen").build()));
        Role cmRole = roleRepository.findByName("CONTENT_MANAGER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("CONTENT_MANAGER").description("CM").build()));
        Role adminRole = roleRepository.findByName("ADMINISTRATOR")
                .orElseGet(() -> roleRepository.save(Role.builder().name("ADMINISTRATOR").description("Admin").build()));

        User citizen = userRepository.findByEmail("sec.citizen@rightsflow.in")
                .orElseGet(() -> userRepository.save(User.builder()
                        .email("sec.citizen@rightsflow.in")
                        .fullName("Security Citizen")
                        .passwordHash(passwordEncoder.encode("Password@123"))
                        .roles(Collections.singleton(citizenRole))
                        .status("ACTIVE")
                        .build()));

        User cm = userRepository.findByEmail("sec.cm@rightsflow.in")
                .orElseGet(() -> userRepository.save(User.builder()
                        .email("sec.cm@rightsflow.in")
                        .fullName("Security CM")
                        .passwordHash(passwordEncoder.encode("Password@123"))
                        .roles(Collections.singleton(cmRole))
                        .status("ACTIVE")
                        .build()));

        User admin = userRepository.findByEmail("sec.admin@rightsflow.in")
                .orElseGet(() -> userRepository.save(User.builder()
                        .email("sec.admin@rightsflow.in")
                        .fullName("Security Admin")
                        .passwordHash(passwordEncoder.encode("Password@123"))
                        .roles(Collections.singleton(adminRole))
                        .status("ACTIVE")
                        .build()));

        citizenToken = jwtUtil.generateTokenFromUser(citizen);
        contentManagerToken = jwtUtil.generateTokenFromUser(cm);
        adminToken = jwtUtil.generateTokenFromUser(admin);
    }

    @Test
    void testPublicEndpointsAccessibleWithoutToken() throws Exception {
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/rights"))
                .andExpect(status().isOk());
    }

    @Test
    void testProtectedCaseEndpointRejectsUnauthenticated() throws Exception {
        mockMvc.perform(get("/api/cases"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testCitizenCanAccessOwnCases() throws Exception {
        mockMvc.perform(get("/api/cases")
                        .header("Authorization", "Bearer " + citizenToken))
                .andExpect(status().isOk());
    }

    @Test
    void testCitizenForbiddenFromAdminEndpoints() throws Exception {
        mockMvc.perform(get("/api/admin/users")
                        .header("Authorization", "Bearer " + citizenToken))
                .andExpect(status().isForbidden());
    }

    @Test
    void testCitizenForbiddenFromContentManagerEndpoints() throws Exception {
        mockMvc.perform(get("/api/content/rights")
                        .header("Authorization", "Bearer " + citizenToken))
                .andExpect(status().isForbidden());
    }

    @Test
    void testAdminCanAccessAdminEndpoints() throws Exception {
        mockMvc.perform(get("/api/admin/users")
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk());
    }
}
