package com.rightsflow.service;

import com.rightsflow.dto.UpdateAndSearchDto;
import com.rightsflow.entity.LegalCategory;
import com.rightsflow.entity.LegalUpdate;
import com.rightsflow.entity.Right;
import com.rightsflow.entity.User;
import com.rightsflow.exception.ResourceNotFoundException;
import com.rightsflow.repository.LegalCategoryRepository;
import com.rightsflow.repository.LegalUpdateRepository;
import com.rightsflow.repository.RightRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class LegalUpdateService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LegalUpdateService.class);

    public LegalUpdateService(LegalUpdateRepository updateRepository, LegalCategoryRepository categoryRepository, RightRepository rightRepository, AuditLogService auditLogService) {
        this.updateRepository = updateRepository;
        this.categoryRepository = categoryRepository;
        this.rightRepository = rightRepository;
        this.auditLogService = auditLogService;
    }



    private final LegalUpdateRepository updateRepository;
    private final LegalCategoryRepository categoryRepository;
    private final RightRepository rightRepository;
    private final AuditLogService auditLogService;

    @Transactional(readOnly = true)
    public Page<UpdateAndSearchDto.LegalUpdateItemDto> searchUpdates(String query, Long categoryId, String status, Pageable pageable) {
        if (query != null && !query.trim().isEmpty()) {
            return updateRepository.searchLegalUpdates(query.trim(), categoryId, status, pageable).map(this::mapToDto);
        }
        return updateRepository.findFiltered(categoryId, status, pageable).map(this::mapToDto);
    }

    @Transactional
    public UpdateAndSearchDto.LegalUpdateItemDto getUpdateById(Long id) {
        LegalUpdate u = updateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LegalUpdate", "id", id));
        u.setViewCount(u.getViewCount() + 1);
        return mapToDto(updateRepository.save(u));
    }

    @Transactional
    public UpdateAndSearchDto.LegalUpdateItemDto createLegalUpdate(UpdateAndSearchDto.LegalUpdateCreateRequest request, User contentManager) {
        LegalCategory cat = null;
        if (request.getCategoryId() != null) {
            cat = categoryRepository.findById(request.getCategoryId()).orElse(null);
        }

        Right right = null;
        if (request.getRightId() != null) {
            right = rightRepository.findById(request.getRightId()).orElse(null);
        }

        LegalUpdate update = LegalUpdate.builder()
                .category(cat)
                .right(right)
                .title(request.getTitle())
                .summary(request.getSummary())
                .whatChanged(request.getWhatChanged())
                .whoIsAffected(request.getWhoIsAffected())
                .effectiveDate(request.getEffectiveDate() != null ? request.getEffectiveDate() : LocalDate.now())
                .publishedDate(LocalDate.now())
                .sourceName(request.getSourceName() != null ? request.getSourceName() : "Official Gazette of India")
                .sourceAuthority(request.getSourceAuthority())
                .officialUrl(request.getOfficialUrl())
                .lastVerified(LocalDate.now())
                .version(1)
                .status(request.getStatus() != null ? request.getStatus() : "PUBLISHED")
                .viewCount(0L)
                .createdAt(LocalDateTime.now())
                .build();

        LegalUpdate saved = updateRepository.save(update);
        auditLogService.logAction(contentManager, "LEGAL_UPDATE_CREATED", "LEGAL_UPDATE", saved.getId().toString(), "Published: " + saved.getTitle());
        return mapToDto(saved);
    }

    private UpdateAndSearchDto.LegalUpdateItemDto mapToDto(LegalUpdate u) {
        return UpdateAndSearchDto.LegalUpdateItemDto.builder()
                .id(u.getId())
                .categoryId(u.getCategory() != null ? u.getCategory().getId() : null)
                .categoryName(u.getCategory() != null ? u.getCategory().getName() : "")
                .categoryColor(u.getCategory() != null ? u.getCategory().getColorTheme() : "blue")
                .rightId(u.getRight() != null ? u.getRight().getId() : null)
                .title(u.getTitle())
                .summary(u.getSummary())
                .whatChanged(u.getWhatChanged())
                .whoIsAffected(u.getWhoIsAffected())
                .effectiveDate(u.getEffectiveDate())
                .publishedDate(u.getPublishedDate())
                .sourceName(u.getSourceName())
                .sourceAuthority(u.getSourceAuthority())
                .officialUrl(u.getOfficialUrl())
                .lastVerified(u.getLastVerified())
                .version(u.getVersion())
                .status(u.getStatus())
                .viewCount(u.getViewCount())
                .createdAt(u.getCreatedAt())
                .build();
    }
}
