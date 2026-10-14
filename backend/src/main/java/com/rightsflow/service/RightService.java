package com.rightsflow.service;

import com.rightsflow.ai.EmbeddingService;
import com.rightsflow.dto.LegalDto;
import com.rightsflow.entity.*;
import com.rightsflow.exception.BadRequestException;
import com.rightsflow.exception.ResourceNotFoundException;
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
public class RightService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RightService.class);

    public RightService(RightRepository rightRepository, LegalCategoryRepository categoryRepository, LawRepository lawRepository, LegalVersionRepository legalVersionRepository, BookmarkRepository bookmarkRepository, RecentlyViewedRepository recentlyViewedRepository, EmbeddingService embeddingService, AuditLogService auditLogService) {
        this.rightRepository = rightRepository;
        this.categoryRepository = categoryRepository;
        this.lawRepository = lawRepository;
        this.legalVersionRepository = legalVersionRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.recentlyViewedRepository = recentlyViewedRepository;
        this.embeddingService = embeddingService;
        this.auditLogService = auditLogService;
    }



    private final RightRepository rightRepository;
    private final LegalCategoryRepository categoryRepository;
    private final LawRepository lawRepository;
    private final LegalVersionRepository legalVersionRepository;
    private final BookmarkRepository bookmarkRepository;
    private final RecentlyViewedRepository recentlyViewedRepository;
    private final EmbeddingService embeddingService;
    private final AuditLogService auditLogService;

    @Transactional(readOnly = true)
    public Page<LegalDto.RightSummaryDto> searchRights(
            String query,
            Long categoryId,
            String contentStatus,
            String verificationStatus,
            Pageable pageable
    ) {
        if (query != null && !query.trim().isEmpty()) {
            return rightRepository.searchRights(query.trim(), categoryId, contentStatus, verificationStatus, pageable)
                    .map(this::mapToSummaryDto);
        }
        return rightRepository.findFiltered(categoryId, contentStatus, verificationStatus, pageable)
                .map(this::mapToSummaryDto);
    }

    @Transactional
    public LegalDto.RightDetailDto getRightBySlugOrId(String identifier, User currentUser) {
        Right right;
        if (identifier.matches("\\d+")) {
            right = rightRepository.findById(Long.parseLong(identifier))
                    .orElseThrow(() -> new ResourceNotFoundException("Right", "id", identifier));
        } else {
            right = rightRepository.findBySlug(identifier)
                    .orElseThrow(() -> new ResourceNotFoundException("Right", "slug", identifier));
        }

        // Increment view count
        right.setViewCount(right.getViewCount() + 1);
        rightRepository.save(right);

        // Record in Recently Viewed for authenticated user
        if (currentUser != null) {
            RecentlyViewed rv = recentlyViewedRepository.findByUserIdAndRightId(currentUser.getId(), right.getId())
                    .orElseGet(() -> RecentlyViewed.builder().user(currentUser).right(right).build());
            rv.setViewedAt(LocalDateTime.now());
            recentlyViewedRepository.save(rv);
        }

        boolean isBookmarked = currentUser != null && bookmarkRepository.existsByUserIdAndRightId(currentUser.getId(), right.getId());
        return mapToDetailDto(right, isBookmarked);
    }

    @Transactional
    public LegalDto.RightDetailDto createRight(LegalDto.RightCreateUpdateRequest request, User contentManager) {
        log.info("Content Manager creating new legal right: {}", request.getTitle());

        LegalCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));

        String slug = request.getSlug();
        if (slug == null || slug.isBlank()) {
            slug = request.getTitle().toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
        }

        Set<Law> laws = new HashSet<>();
        if (request.getLawIds() != null) {
            laws.addAll(lawRepository.findAllById(request.getLawIds()));
        }

        Right right = Right.builder()
                .category(category)
                .title(request.getTitle().trim())
                .slug(slug)
                .shortDescription(request.getShortDescription())
                .simpleExplanation(request.getSimpleExplanation())
                .detailedExplanation(request.getDetailedExplanation())
                .whoItAppliesTo(request.getWhoItAppliesTo())
                .whyItApplies(request.getWhyItApplies())
                .realWorldExample(request.getRealWorldExample())
                .recommendedActions(request.getRecommendedActions())
                .requiredDocuments(request.getRequiredDocuments())
                .relevantAuthority(request.getRelevantAuthority())
                .relevantService(request.getRelevantService())
                .procedureSummary(request.getProcedureSummary())
                .officialSourceUrl(request.getOfficialSourceUrl())
                .sourceAuthority(request.getSourceAuthority())
                .lastVerified(LocalDate.now())
                .effectiveFrom(request.getEffectiveFrom() != null ? request.getEffectiveFrom() : LocalDate.now())
                .version(1)
                .verificationStatus(request.getVerificationStatus() != null ? request.getVerificationStatus() : "VERIFIED")
                .contentStatus(request.getContentStatus() != null ? request.getContentStatus() : "PUBLISHED")
                .laws(laws)
                .build();

        Right saved = rightRepository.save(right);

        // Record Initial Version History
        LegalVersion version = LegalVersion.builder()
                .right(saved)
                .version(1)
                .title(saved.getTitle())
                .simpleExplanation(saved.getSimpleExplanation())
                .detailedExplanation(saved.getDetailedExplanation())
                .changedBy(contentManager)
                .changeSummary("Initial Creation & Verification")
                .verificationStatus(saved.getVerificationStatus())
                .build();
        legalVersionRepository.save(version);

        // Generate RAG embeddings
        embeddingService.generateOrUpdateEmbedding(saved);

        auditLogService.logAction(contentManager, "RIGHT_CREATED", "RIGHT", saved.getId().toString(), "Created: " + saved.getTitle());
        return mapToDetailDto(saved, false);
    }

    @Transactional
    public LegalDto.RightDetailDto updateRight(Long id, LegalDto.RightCreateUpdateRequest request, User contentManager) {
        Right right = rightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Right", "id", id));

        if (request.getCategoryId() != null) {
            LegalCategory category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));
            right.setCategory(category);
        }

        right.setTitle(request.getTitle());
        right.setShortDescription(request.getShortDescription());
        right.setSimpleExplanation(request.getSimpleExplanation());
        right.setDetailedExplanation(request.getDetailedExplanation());
        right.setWhoItAppliesTo(request.getWhoItAppliesTo());
        right.setWhyItApplies(request.getWhyItApplies());
        right.setRealWorldExample(request.getRealWorldExample());
        right.setRecommendedActions(request.getRecommendedActions());
        right.setRequiredDocuments(request.getRequiredDocuments());
        right.setRelevantAuthority(request.getRelevantAuthority());
        right.setRelevantService(request.getRelevantService());
        right.setProcedureSummary(request.getProcedureSummary());
        right.setOfficialSourceUrl(request.getOfficialSourceUrl());
        right.setSourceAuthority(request.getSourceAuthority());
        right.setLastVerified(LocalDate.now());

        if (request.getVerificationStatus() != null) {
            right.setVerificationStatus(request.getVerificationStatus());
        }
        if (request.getContentStatus() != null) {
            right.setContentStatus(request.getContentStatus());
        }

        if (request.getLawIds() != null) {
            Set<Law> laws = new HashSet<>(lawRepository.findAllById(request.getLawIds()));
            right.setLaws(laws);
        }

        right.setVersion(right.getVersion() + 1);
        Right saved = rightRepository.save(right);

        // Record version snapshot
        LegalVersion version = LegalVersion.builder()
                .right(saved)
                .version(saved.getVersion())
                .title(saved.getTitle())
                .simpleExplanation(saved.getSimpleExplanation())
                .detailedExplanation(saved.getDetailedExplanation())
                .changedBy(contentManager)
                .changeSummary("Content updated to Version " + saved.getVersion())
                .verificationStatus(saved.getVerificationStatus())
                .build();
        legalVersionRepository.save(version);

        // Update RAG embeddings
        embeddingService.generateOrUpdateEmbedding(saved);

        auditLogService.logAction(contentManager, "RIGHT_UPDATED", "RIGHT", saved.getId().toString(), "Updated: " + saved.getTitle());
        return mapToDetailDto(saved, false);
    }

    @Transactional
    public void deleteOrArchiveRight(Long id, User contentManager) {
        Right right = rightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Right", "id", id));
        right.setContentStatus("ARCHIVED");
        rightRepository.save(right);
        auditLogService.logAction(contentManager, "RIGHT_ARCHIVED", "RIGHT", id.toString(), "Archived right: " + right.getTitle());
    }

    public LegalDto.RightSummaryDto mapToSummaryDto(Right r) {
        String law = r.getLaws() != null && !r.getLaws().isEmpty() ? r.getLaws().iterator().next().getTitle() : "Statutory Law";
        String prov = "Applicable Sections";
        if (r.getLaws() != null && !r.getLaws().isEmpty() && !r.getLaws().iterator().next().getProvisions().isEmpty()) {
            prov = r.getLaws().iterator().next().getProvisions().get(0).getSectionNumber();
        }

        return LegalDto.RightSummaryDto.builder()
                .id(r.getId())
                .title(r.getTitle())
                .slug(r.getSlug())
                .categoryId(r.getCategory() != null ? r.getCategory().getId() : null)
                .categoryName(r.getCategory() != null ? r.getCategory().getName() : "")
                .colorTheme(r.getCategory() != null ? r.getCategory().getColorTheme() : "blue")
                .shortDescription(r.getShortDescription())
                .simpleExplanation(r.getSimpleExplanation())
                .applicableLaw(law)
                .relevantProvision(prov)
                .relevantAuthority(r.getRelevantAuthority())
                .relevantService(r.getRelevantService())
                .verificationStatus(r.getVerificationStatus())
                .contentStatus(r.getContentStatus())
                .viewCount(r.getViewCount())
                .lastVerified(r.getLastVerified())
                .build();
    }

    public LegalDto.RightDetailDto mapToDetailDto(Right r, boolean isBookmarked) {
        List<String> actions = parseDelimited(r.getRecommendedActions());
        List<String> docs = parseDelimited(r.getRequiredDocuments());

        List<LegalDto.LawDto> laws = r.getLaws() != null ? r.getLaws().stream()
                .map(l -> LegalDto.LawDto.builder()
                        .id(l.getId())
                        .title(l.getTitle())
                        .shortCode(l.getShortCode())
                        .year(l.getYear())
                        .officialLink(l.getOfficialLink())
                        .provisions(l.getProvisions() != null ? l.getProvisions().stream()
                                .map(p -> LegalDto.ProvisionDto.builder()
                                        .id(p.getId())
                                        .sectionNumber(p.getSectionNumber())
                                        .title(p.getTitle())
                                        .simpleSummary(p.getSimpleSummary())
                                        .penaltyOrRemedy(p.getPenaltyOrRemedy())
                                        .build()).collect(Collectors.toList()) : Collections.emptyList())
                        .build()).collect(Collectors.toList()) : Collections.emptyList();

        List<LegalDto.FaqDto> faqs = r.getFaqs() != null ? r.getFaqs().stream()
                .map(f -> LegalDto.FaqDto.builder()
                        .id(f.getId())
                        .question(f.getQuestion())
                        .answer(f.getAnswer())
                        .build()).collect(Collectors.toList()) : Collections.emptyList();

        List<LegalDto.ExampleDto> examples = r.getExamples() != null ? r.getExamples().stream()
                .map(e -> LegalDto.ExampleDto.builder()
                        .id(e.getId())
                        .scenarioTitle(e.getScenarioTitle())
                        .scenarioDescription(e.getScenarioDescription())
                        .solutionOutcome(e.getSolutionOutcome())
                        .build()).collect(Collectors.toList()) : Collections.emptyList();

        List<LegalDto.VersionDto> versions = r.getVersions() != null ? r.getVersions().stream()
                .map(v -> LegalDto.VersionDto.builder()
                        .id(v.getId())
                        .version(v.getVersion())
                        .title(v.getTitle())
                        .changeSummary(v.getChangeSummary())
                        .changedByEmail(v.getChangedBy() != null ? v.getChangedBy().getEmail() : "System")
                        .createdAt(v.getCreatedAt())
                        .build()).collect(Collectors.toList()) : Collections.emptyList();

        return LegalDto.RightDetailDto.builder()
                .id(r.getId())
                .categoryId(r.getCategory() != null ? r.getCategory().getId() : null)
                .categoryName(r.getCategory() != null ? r.getCategory().getName() : "")
                .categoryColor(r.getCategory() != null ? r.getCategory().getColorTheme() : "blue")
                .title(r.getTitle())
                .slug(r.getSlug())
                .shortDescription(r.getShortDescription())
                .simpleExplanation(r.getSimpleExplanation())
                .detailedExplanation(r.getDetailedExplanation())
                .whoItAppliesTo(r.getWhoItAppliesTo())
                .whyItApplies(r.getWhyItApplies())
                .realWorldExample(r.getRealWorldExample())
                .recommendedActions(actions)
                .requiredDocuments(docs)
                .relevantAuthority(r.getRelevantAuthority())
                .relevantService(r.getRelevantService())
                .procedureSummary(r.getProcedureSummary())
                .officialSourceUrl(r.getOfficialSourceUrl())
                .sourceAuthority(r.getSourceAuthority())
                .lastVerified(r.getLastVerified())
                .effectiveFrom(r.getEffectiveFrom())
                .effectiveTo(r.getEffectiveTo())
                .version(r.getVersion())
                .verificationStatus(r.getVerificationStatus())
                .contentStatus(r.getContentStatus())
                .viewCount(r.getViewCount())
                .isBookmarked(isBookmarked)
                .laws(laws)
                .faqs(faqs)
                .examples(examples)
                .versions(versions)
                .build();
    }

    private List<String> parseDelimited(String raw) {
        if (raw == null || raw.isBlank()) return Collections.emptyList();
        String[] lines = raw.split("\n|,\\s*(?=[0-9]\\.)|;");
        List<String> list = new ArrayList<>();
        for (String l : lines) {
            String clean = l.replaceAll("^[0-9]+\\.\\s*", "").trim();
            if (!clean.isEmpty()) list.add(clean);
        }
        return list;
    }
}
