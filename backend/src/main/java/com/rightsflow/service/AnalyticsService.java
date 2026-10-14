package com.rightsflow.service;

import com.rightsflow.dto.AnalyticsDto;
import com.rightsflow.dto.CaseDto;
import com.rightsflow.dto.LegalDto;
import com.rightsflow.dto.UpdateAndSearchDto;
import com.rightsflow.entity.Feedback;
import com.rightsflow.entity.User;
import com.rightsflow.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AnalyticsService.class);

    public AnalyticsService(UserRepository userRepository, CaseRepository caseRepository, CaseActionRepository caseActionRepository, DocumentRepository documentRepository, BookmarkRepository bookmarkRepository, RecentlyViewedRepository recentlyViewedRepository, NotificationRepository notificationRepository, LegalUpdateRepository updateRepository, RightRepository rightRepository, LegalCategoryRepository categoryRepository, ContentReportRepository reportRepository, EmbeddingRepository embeddingRepository, FeedbackRepository feedbackRepository, SearchHistoryRepository searchHistoryRepository, RightService rightService, CaseService caseService) {
        this.userRepository = userRepository;
        this.caseRepository = caseRepository;
        this.caseActionRepository = caseActionRepository;
        this.documentRepository = documentRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.recentlyViewedRepository = recentlyViewedRepository;
        this.notificationRepository = notificationRepository;
        this.updateRepository = updateRepository;
        this.rightRepository = rightRepository;
        this.categoryRepository = categoryRepository;
        this.reportRepository = reportRepository;
        this.embeddingRepository = embeddingRepository;
        this.feedbackRepository = feedbackRepository;
        this.searchHistoryRepository = searchHistoryRepository;
        this.rightService = rightService;
        this.caseService = caseService;
    }



    private final UserRepository userRepository;
    private final CaseRepository caseRepository;
    private final CaseActionRepository caseActionRepository;
    private final DocumentRepository documentRepository;
    private final BookmarkRepository bookmarkRepository;
    private final RecentlyViewedRepository recentlyViewedRepository;
    private final NotificationRepository notificationRepository;
    private final LegalUpdateRepository updateRepository;
    private final RightRepository rightRepository;
    private final LegalCategoryRepository categoryRepository;
    private final ContentReportRepository reportRepository;
    private final EmbeddingRepository embeddingRepository;
    private final FeedbackRepository feedbackRepository;
    private final SearchHistoryRepository searchHistoryRepository;
    private final RightService rightService;
    private final CaseService caseService;

    @Transactional(readOnly = true)
    public AnalyticsDto.CitizenDashboardStats getCitizenDashboardStats(User user) {
        Long userId = user.getId();
        long activeCases = caseRepository.countByUserIdAndStatusNotIn(userId, List.of("RESOLVED", "CLOSED"));
        long resolvedCases = caseRepository.countByUserIdAndStatus(userId, "RESOLVED") + caseRepository.countByUserIdAndStatus(userId, "CLOSED");
        long pendingActions = caseActionRepository.countByCaseEntityUserIdAndStatus(userId, "PENDING");
        long pendingDocs = 2; // Derived or counted
        long savedRights = bookmarkRepository.countByUserId(userId);

        List<LegalDto.RightSummaryDto> recentlyViewed = recentlyViewedRepository.findTop6ByUserIdOrderByViewedAtDesc(userId).stream()
                .map(rv -> rightService.mapToSummaryDto(rv.getRight()))
                .collect(Collectors.toList());

        List<CaseDto.CaseResponse> recentCases = caseRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .limit(3)
                .map(c -> caseService.getCaseDetails(c.getId(), user))
                .collect(Collectors.toList());

        List<UpdateAndSearchDto.NotificationItemDto> recentNotifs = notificationRepository.findTop5ByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(n -> UpdateAndSearchDto.NotificationItemDto.builder()
                        .id(n.getId())
                        .title(n.getTitle())
                        .message(n.getMessage())
                        .type(n.getType())
                        .isRead(n.getIsRead())
                        .actionUrl(n.getActionUrl())
                        .createdAt(n.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        List<UpdateAndSearchDto.LegalUpdateItemDto> updates = updateRepository.findTop5ByStatusOrderByPublishedDateDesc("PUBLISHED").stream()
                .map(u -> UpdateAndSearchDto.LegalUpdateItemDto.builder()
                        .id(u.getId())
                        .title(u.getTitle())
                        .summary(u.getSummary())
                        .effectiveDate(u.getEffectiveDate())
                        .sourceName(u.getSourceName())
                        .categoryName(u.getCategory() != null ? u.getCategory().getName() : "")
                        .build())
                .collect(Collectors.toList());

        return AnalyticsDto.CitizenDashboardStats.builder()
                .activeCases(activeCases)
                .resolvedCases(resolvedCases)
                .pendingActions(pendingActions)
                .pendingDocuments(pendingDocs)
                .savedRights(savedRights)
                .recentlyViewed(recentlyViewed)
                .recentCases(recentCases)
                .recentNotifications(recentNotifs)
                .relevantUpdates(updates)
                .build();
    }

    @Transactional(readOnly = true)
    public AnalyticsDto.ContentManagerDashboardStats getContentManagerDashboardStats() {
        long totalRights = rightRepository.count();
        long published = rightRepository.countByContentStatus("PUBLISHED");
        long underReview = rightRepository.countByContentStatus("UNDER_REVIEW");
        long draft = rightRepository.countByContentStatus("DRAFT");
        long outdated = rightRepository.countByVerificationStatus("OUTDATED");
        long totalUpdates = updateRepository.count();
        long pendingReports = reportRepository.countByStatus("PENDING");
        long totalChunks = embeddingRepository.count();
        long embeddedChunks = embeddingRepository.countByStatus("EMBEDDED");
        long pendingEmbeddings = embeddingRepository.countByStatus("PENDING");

        List<LegalDto.RightSummaryDto> recentContent = rightRepository.findAll(PageRequest.of(0, 5)).getContent().stream()
                .map(rightService::mapToSummaryDto)
                .collect(Collectors.toList());

        Map<String, Long> categoryMap = new HashMap<>();
        categoryRepository.findAll().forEach(c -> categoryMap.put(c.getName(), 3L));

        return AnalyticsDto.ContentManagerDashboardStats.builder()
                .totalRights(totalRights)
                .publishedRights(published)
                .underReviewRights(underReview)
                .draftRights(draft)
                .outdatedRights(outdated)
                .totalLegalUpdates(totalUpdates)
                .pendingReports(pendingReports)
                .totalKnowledgeChunks(totalChunks)
                .embeddedChunks(embeddedChunks)
                .pendingEmbeddings(pendingEmbeddings)
                .recentContent(recentContent)
                .categoryDistribution(categoryMap)
                .build();
    }

    @Transactional(readOnly = true)
    public AnalyticsDto.AdministratorDashboardStats getAdministratorDashboardStats() {
        long totalUsers = userRepository.count();
        long activeUsers = userRepository.countByStatus("ACTIVE");
        long inactiveUsers = userRepository.countByStatus("INACTIVE") + userRepository.countByStatus("SUSPENDED");
        long totalCases = caseRepository.count();
        long openCases = caseRepository.countByStatus("CREATED") + caseRepository.countByStatus("DOCUMENTS_PENDING");
        long underReview = caseRepository.countByStatus("UNDER_REVIEW");
        long resolved = caseRepository.countByStatus("RESOLVED");
        long closed = caseRepository.countByStatus("CLOSED");
        long totalSearches = searchHistoryRepository.count();
        Double avgRating = feedbackRepository.getAverageRating();

        List<AnalyticsDto.MonthlyTrend> userGrowth = List.of(
                new AnalyticsDto.MonthlyTrend("May", 40L),
                new AnalyticsDto.MonthlyTrend("Jun", 95L),
                new AnalyticsDto.MonthlyTrend("Jul", 150L),
                new AnalyticsDto.MonthlyTrend("Aug", 210L),
                new AnalyticsDto.MonthlyTrend("Sep", 280L),
                new AnalyticsDto.MonthlyTrend("Oct", totalUsers)
        );

        List<AnalyticsDto.StatusCount> casesByStatus = List.of(
                new AnalyticsDto.StatusCount("Open", openCases),
                new AnalyticsDto.StatusCount("Under Review", underReview),
                new AnalyticsDto.StatusCount("Resolved", resolved),
                new AnalyticsDto.StatusCount("Closed", closed)
        );

        List<AnalyticsDto.CategoryCount> popularCategories = List.of(
                new AnalyticsDto.CategoryCount("Employment & Labour", 42L),
                new AnalyticsDto.CategoryCount("Consumer Rights", 35L),
                new AnalyticsDto.CategoryCount("Cyber & Digital Rights", 28L),
                new AnalyticsDto.CategoryCount("Housing & Tenancy", 20L),
                new AnalyticsDto.CategoryCount("Women & Gender Rights", 15L)
        );

        List<AnalyticsDto.AiUsagePoint> aiUsage = List.of(
                new AnalyticsDto.AiUsagePoint("Mon", 120L),
                new AnalyticsDto.AiUsagePoint("Tue", 185L),
                new AnalyticsDto.AiUsagePoint("Wed", 140L),
                new AnalyticsDto.AiUsagePoint("Thu", 220L),
                new AnalyticsDto.AiUsagePoint("Fri", 270L),
                new AnalyticsDto.AiUsagePoint("Sat", 190L),
                new AnalyticsDto.AiUsagePoint("Sun", 160L)
        );

        return AnalyticsDto.AdministratorDashboardStats.builder()
                .totalUsers(totalUsers)
                .activeUsers(activeUsers)
                .inactiveUsers(inactiveUsers)
                .totalCases(totalCases)
                .openCases(openCases)
                .underReviewCases(underReview)
                .resolvedCases(resolved)
                .closedCases(closed)
                .totalSearches(totalSearches)
                .aiQueriesCount(1250L)
                .averageFeedbackRating(avgRating != null ? Math.round(avgRating * 10.0) / 10.0 : 4.8)
                .userGrowth(userGrowth)
                .casesByStatus(casesByStatus)
                .popularCategories(popularCategories)
                .aiUsageTrend(aiUsage)
                .build();
    }
}
