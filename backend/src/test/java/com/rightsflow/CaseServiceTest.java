package com.rightsflow;

import com.rightsflow.dto.CaseDto;
import com.rightsflow.entity.*;
import com.rightsflow.exception.UnauthorizedException;
import com.rightsflow.repository.*;
import com.rightsflow.service.CaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CaseServiceTest {

    @Autowired
    private CaseService caseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LegalCategoryRepository categoryRepository;

    private User citizen1;
    private User citizen2;
    private LegalCategory consumerCategory;

    @BeforeEach
    void setUp() {
        Role citizenRole = roleRepository.findByName("CITIZEN")
                .orElseGet(() -> roleRepository.save(Role.builder().name("CITIZEN").description("Citizen").build()));

        citizen1 = userRepository.findByEmail("case.user1@rightsflow.in")
                .orElseGet(() -> userRepository.save(User.builder()
                        .email("case.user1@rightsflow.in")
                        .fullName("Citizen One")
                        .passwordHash("hashed")
                        .roles(Collections.singleton(citizenRole))
                        .status("ACTIVE")
                        .build()));

        citizen2 = userRepository.findByEmail("case.user2@rightsflow.in")
                .orElseGet(() -> userRepository.save(User.builder()
                        .email("case.user2@rightsflow.in")
                        .fullName("Citizen Two")
                        .passwordHash("hashed")
                        .roles(Collections.singleton(citizenRole))
                        .status("ACTIVE")
                        .build()));

        consumerCategory = categoryRepository.findBySlug("consumer-rights")
                .orElseGet(() -> categoryRepository.save(LegalCategory.builder()
                        .name("Consumer Protection & Fair Trade")
                        .slug("consumer-rights")
                        .description("Consumer rights under Consumer Protection Act 2019")
                        .iconName("ShoppingBag")
                        .colorTheme("orange")
                        .displayOrder(3)
                        .build()));
    }

    @Test
    void testCreateCaseAndChecklistGeneration() {
        CaseDto.CaseCreateRequest request = CaseDto.CaseCreateRequest.builder()
                .title("Defective Air Conditioner Refund Refusal")
                .problemDescription("Purchased split AC online. Compressor defective on arrival. Seller refuses refund or replacement.")
                .categoryId(consumerCategory.getId())
                .authorityName("District Consumer Disputes Redressal Commission")
                .serviceName("e-Daakhil Online Filing")
                .initialActions(List.of(
                        "Collect purchase invoice and warranty card",
                        "Send formal legal notice to manufacturer and seller",
                        "File complaint online via e-Daakhil"
                ))
                .notes("Purchased via Flipkart on 15th August")
                .build();

        CaseDto.CaseResponse response = caseService.createCase(request, citizen1);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertTrue(response.getCaseNumber().startsWith("RF-"));
        assertEquals("Defective Air Conditioner Refund Refusal", response.getTitle());
        assertEquals("CREATED", response.getStatus());
        assertEquals(3, response.getActions().size());

        // Verify retrieval for citizen1
        Page<CaseDto.CaseResponse> citizenCases = caseService.getCitizenCases(citizen1.getId(), null, null, null, PageRequest.of(0, 10));
        assertFalse(citizenCases.isEmpty());
        assertTrue(citizenCases.getContent().stream().anyMatch(c -> c.getId().equals(response.getId())));
    }

    @Test
    void testCaseStatusTransitionAndActionToggle() {
        CaseDto.CaseCreateRequest request = CaseDto.CaseCreateRequest.builder()
                .title("Unpaid Overtime Wages Claim")
                .problemDescription("Employer refused 4 months mandatory overtime pay under Payment of Wages Act.")
                .initialActions(List.of("Gather punch records", "File notice with Labour Officer"))
                .build();

        CaseDto.CaseResponse created = caseService.createCase(request, citizen1);
        assertEquals("CREATED", created.getStatus());

        // Transition status to IN_PROGRESS
        CaseDto.StatusTransitionRequest transitionReq = new CaseDto.StatusTransitionRequest(
                "IN_PROGRESS",
                "Labour commissioner served notice to employer"
        );

        CaseDto.CaseResponse transitioned = caseService.transitionStatus(created.getId(), transitionReq, citizen1);
        assertEquals("IN_PROGRESS", transitioned.getStatus());
        assertTrue(transitioned.getTimeline().size() >= 2);

        // Toggle first action to COMPLETED
        Long firstActionId = transitioned.getActions().get(0).getId();
        CaseDto.CaseActionItemDto toggled = caseService.toggleActionStatus(firstActionId, "COMPLETED", citizen1);
        assertEquals("COMPLETED", toggled.getStatus());
    }

    @Test
    void testUnauthorizedCitizenCannotAccessOthersCase() {
        CaseDto.CaseCreateRequest request = CaseDto.CaseCreateRequest.builder()
                .title("Private Tenancy Dispute")
                .problemDescription("Landlord withholding 1 lakh deposit.")
                .build();

        CaseDto.CaseResponse created = caseService.createCase(request, citizen1);

        // Citizen2 should be blocked from accessing citizen1's case
        assertThrows(UnauthorizedException.class, () -> {
            caseService.getCaseDetails(created.getId(), citizen2);
        });

        // Citizen2 should be blocked from transitioning citizen1's case status
        CaseDto.StatusTransitionRequest transitionReq = new CaseDto.StatusTransitionRequest("CLOSED", null);

        assertThrows(UnauthorizedException.class, () -> {
            caseService.transitionStatus(created.getId(), transitionReq, citizen2);
        });
    }
}
