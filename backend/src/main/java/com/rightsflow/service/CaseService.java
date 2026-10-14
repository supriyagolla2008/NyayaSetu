package com.rightsflow.service;

import com.rightsflow.dto.CaseDto;
import com.rightsflow.entity.*;
import com.rightsflow.exception.BadRequestException;
import com.rightsflow.exception.ResourceNotFoundException;
import com.rightsflow.exception.UnauthorizedException;
import com.rightsflow.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CaseService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CaseService.class);

    public CaseService(CaseRepository caseRepository, CaseStatusHistoryRepository statusHistoryRepository, CaseActionRepository actionRepository, CaseDocumentRepository caseDocumentRepository, LegalCategoryRepository categoryRepository, RightRepository rightRepository, NotificationService notificationService, AuditLogService auditLogService) {
        this.caseRepository = caseRepository;
        this.statusHistoryRepository = statusHistoryRepository;
        this.actionRepository = actionRepository;
        this.caseDocumentRepository = caseDocumentRepository;
        this.categoryRepository = categoryRepository;
        this.rightRepository = rightRepository;
        this.notificationService = notificationService;
        this.auditLogService = auditLogService;
    }



    private final CaseRepository caseRepository;
    private final CaseStatusHistoryRepository statusHistoryRepository;
    private final CaseActionRepository actionRepository;
    private final CaseDocumentRepository caseDocumentRepository;
    private final LegalCategoryRepository categoryRepository;
    private final RightRepository rightRepository;
    private final NotificationService notificationService;
    private final AuditLogService auditLogService;

    @Transactional(readOnly = true)
    public Page<CaseDto.CaseResponse> getCitizenCases(Long userId, String status, Long categoryId, String query, Pageable pageable) {
        if (query != null && !query.trim().isEmpty()) {
            return caseRepository.searchCases(userId, status, categoryId, query.trim(), pageable)
                    .map(this::mapToCaseResponse);
        }
        return caseRepository.findFiltered(userId, status, categoryId, pageable)
                .map(this::mapToCaseResponse);
    }

    @Transactional(readOnly = true)
    public Page<CaseDto.CaseResponse> getAllCasesForAdmin(String status, Long categoryId, String query, Pageable pageable) {
        if (query != null && !query.trim().isEmpty()) {
            return caseRepository.searchCases(null, status, categoryId, query.trim(), pageable)
                    .map(this::mapToCaseResponse);
        }
        return caseRepository.findFiltered(null, status, categoryId, pageable)
                .map(this::mapToCaseResponse);
    }

    @Transactional(readOnly = true)
    public CaseDto.CaseResponse getCaseDetails(Long id, User currentUser) {
        CaseEntity caseEntity = caseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Case", "id", id));

        verifyAccess(caseEntity, currentUser);
        return mapToCaseResponse(caseEntity);
    }

    @Transactional
    public CaseDto.CaseResponse createCase(CaseDto.CaseCreateRequest request, User currentUser) {
        log.info("Creating new case for user: {}", currentUser.getEmail());

        LegalCategory category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        }

        Right right = null;
        if (request.getRightId() != null) {
            right = rightRepository.findById(request.getRightId()).orElse(null);
            if (category == null && right != null) {
                category = right.getCategory();
            }
        }

        String caseNumber = "RF-" + (1000 + caseRepository.count() + 1);

        CaseEntity caseEntity = CaseEntity.builder()
                .caseNumber(caseNumber)
                .user(currentUser)
                .category(category)
                .right(right)
                .title(request.getTitle())
                .problemDescription(request.getProblemDescription())
                .relatedLaws(request.getRelatedLaws() != null ? request.getRelatedLaws() : (right != null && !right.getLaws().isEmpty() ? right.getLaws().iterator().next().getTitle() : ""))
                .relatedProvisions(request.getRelatedProvisions())
                .authorityName(request.getAuthorityName() != null ? request.getAuthorityName() : (right != null ? right.getRelevantAuthority() : ""))
                .serviceName(request.getServiceName() != null ? request.getServiceName() : (right != null ? right.getRelevantService() : ""))
                .status("CREATED")
                .notes(request.getNotes())
                .build();

        CaseEntity saved = caseRepository.save(caseEntity);

        // Initial Status History entry
        CaseStatusHistory history = CaseStatusHistory.builder()
                .caseEntity(saved)
                .fromStatus(null)
                .toStatus("CREATED")
                .changedBy(currentUser)
                .remarks("Case initiated from RightsFlow problem solver")
                .build();
        statusHistoryRepository.save(history);

        // Populate initial actions from right or request
        List<String> actionTitles = request.getInitialActions();
        if ((actionTitles == null || actionTitles.isEmpty()) && right != null) {
            actionTitles = Arrays.asList(
                    "Collect salary slips and employment documents",
                    "Send a written request to your employer",
                    "Approach the Labour Department / SAMADHAN Portal",
                    "Track your complaint and attend conciliation"
            );
        }

        if (actionTitles != null) {
            int idx = 1;
            for (String title : actionTitles) {
                CaseAction action = CaseAction.builder()
                        .caseEntity(saved)
                        .title(title)
                        .actionType(idx == 1 ? "DOCUMENT" : idx == 2 ? "CONTACT" : "SUBMIT")
                        .status("PENDING")
                        .dueDate(LocalDate.now().plusDays(idx * 5L))
                        .orderIndex(idx++)
                        .build();
                actionRepository.save(action);
            }
        }

        notificationService.createNotification(
                currentUser,
                saved,
                "Case Created: " + saved.getCaseNumber(),
                "Your case '" + saved.getTitle() + "' has been successfully registered in RightsFlow.",
                "CASE_UPDATE",
                "/cases/" + saved.getId()
        );

        auditLogService.logAction(currentUser, "CASE_CREATED", "CASE", saved.getId().toString(), "Case " + saved.getCaseNumber() + " created");
        return mapToCaseResponse(saved);
    }

    @Transactional
    public CaseDto.CaseResponse transitionStatus(Long id, CaseDto.StatusTransitionRequest request, User currentUser) {
        CaseEntity caseEntity = caseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Case", "id", id));

        verifyAccess(caseEntity, currentUser);

        String fromStatus = caseEntity.getStatus();
        String toStatus = request.getToStatus().toUpperCase();

        caseEntity.setStatus(toStatus);
        CaseEntity updated = caseRepository.save(caseEntity);

        CaseStatusHistory history = CaseStatusHistory.builder()
                .caseEntity(updated)
                .fromStatus(fromStatus)
                .toStatus(toStatus)
                .changedBy(currentUser)
                .remarks(request.getRemarks() != null ? request.getRemarks() : "Status updated to " + toStatus)
                .build();
        statusHistoryRepository.save(history);

        notificationService.createNotification(
                caseEntity.getUser(),
                updated,
                "Case Status Update: " + updated.getCaseNumber(),
                "Case status transitioned from " + fromStatus + " to " + toStatus + ".",
                "CASE_UPDATE",
                "/cases/" + updated.getId()
        );

        auditLogService.logAction(currentUser, "CASE_STATUS_TRANSITION", "CASE", id.toString(), fromStatus + " -> " + toStatus);
        return mapToCaseResponse(updated);
    }

    @Transactional
    public CaseDto.CaseActionItemDto toggleActionStatus(Long actionId, String status, User currentUser) {
        CaseAction action = actionRepository.findById(actionId)
                .orElseThrow(() -> new ResourceNotFoundException("CaseAction", "id", actionId));

        verifyAccess(action.getCaseEntity(), currentUser);

        action.setStatus(status.toUpperCase());
        CaseAction saved = actionRepository.save(action);

        auditLogService.logAction(currentUser, "ACTION_STATUS_TOGGLED", "ACTION", actionId.toString(), "Status: " + status);
        return mapActionToDto(saved);
    }

    private void verifyAccess(CaseEntity caseEntity, User currentUser) {
        boolean isAdmin = currentUser.getRoles().stream().anyMatch(r -> r.getName().equals("ADMINISTRATOR"));
        boolean isOwner = caseEntity.getUser().getId().equals(currentUser.getId());
        if (!isAdmin && !isOwner) {
            throw new UnauthorizedException("You do not have permission to access or modify this case.");
        }
    }

    private CaseDto.CaseResponse mapToCaseResponse(CaseEntity c) {
        List<CaseStatusHistory> history = statusHistoryRepository.findByCaseEntityIdOrderByCreatedAtAsc(c.getId());
        List<CaseAction> actions = actionRepository.findByCaseEntityIdOrderByOrderIndexAsc(c.getId());
        List<CaseDocument> documents = caseDocumentRepository.findByCaseEntityId(c.getId());

        int completed = (int) actions.stream().filter(a -> "COMPLETED".equalsIgnoreCase(a.getStatus())).count();
        int completionPercentage = actions.isEmpty() ? 0 : (int) ((completed / (double) actions.size()) * 100);

        List<CaseDto.CaseStatusHistoryDto> timeline = history.stream().map(h -> CaseDto.CaseStatusHistoryDto.builder()
                .id(h.getId())
                .fromStatus(h.getFromStatus())
                .toStatus(h.getToStatus())
                .changedByName(h.getChangedBy() != null ? h.getChangedBy().getFullName() : "System")
                .remarks(h.getRemarks())
                .createdAt(h.getCreatedAt())
                .build()).collect(Collectors.toList());

        List<CaseDto.CaseActionItemDto> actionDtos = actions.stream().map(this::mapActionToDto).collect(Collectors.toList());

        List<CaseDto.CaseDocumentItemDto> docDtos = documents.stream().map(d -> CaseDto.CaseDocumentItemDto.builder()
                .id(d.getId())
                .documentId(d.getDocument().getId())
                .fileName(d.getDocument().getFileName())
                .originalName(d.getDocument().getOriginalName())
                .fileType(d.getDocument().getFileType())
                .fileSize(d.getDocument().getFileSize())
                .documentCategory(d.getDocumentCategory())
                .isVerified(d.getIsVerified())
                .uploadedAt(d.getUploadedAt())
                .build()).collect(Collectors.toList());

        return CaseDto.CaseResponse.builder()
                .id(c.getId())
                .caseNumber(c.getCaseNumber())
                .userId(c.getUser().getId())
                .userName(c.getUser().getFullName())
                .categoryId(c.getCategory() != null ? c.getCategory().getId() : null)
                .categoryName(c.getCategory() != null ? c.getCategory().getName() : "")
                .categoryColor(c.getCategory() != null ? c.getCategory().getColorTheme() : "blue")
                .rightId(c.getRight() != null ? c.getRight().getId() : null)
                .rightTitle(c.getRight() != null ? c.getRight().getTitle() : "")
                .rightSlug(c.getRight() != null ? c.getRight().getSlug() : "")
                .title(c.getTitle())
                .problemDescription(c.getProblemDescription())
                .relatedLaws(c.getRelatedLaws())
                .relatedProvisions(c.getRelatedProvisions())
                .authorityName(c.getAuthorityName())
                .serviceName(c.getServiceName())
                .status(c.getStatus())
                .notes(c.getNotes())
                .completionPercentage(completionPercentage)
                .timeline(timeline)
                .actions(actionDtos)
                .documents(docDtos)
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .build();
    }

    private CaseDto.CaseActionItemDto mapActionToDto(CaseAction a) {
        return CaseDto.CaseActionItemDto.builder()
                .id(a.getId())
                .title(a.getTitle())
                .description(a.getDescription())
                .actionType(a.getActionType())
                .status(a.getStatus())
                .dueDate(a.getDueDate())
                .orderIndex(a.getOrderIndex())
                .build();
    }
}
