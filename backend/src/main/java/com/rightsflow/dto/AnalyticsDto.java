package com.rightsflow.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AnalyticsDto {

    public static class CitizenDashboardStats {
        private Long activeCases;
        private Long resolvedCases;
        private Long pendingActions;
        private Long pendingDocuments;
        private Long savedRights;
        private List<LegalDto.RightSummaryDto> recentlyViewed;
        private List<CaseDto.CaseResponse> recentCases;
        private List<UpdateAndSearchDto.NotificationItemDto> recentNotifications;
        private List<UpdateAndSearchDto.LegalUpdateItemDto> relevantUpdates;

        public CitizenDashboardStats() {}
        public CitizenDashboardStats(Long activeCases, Long resolvedCases, Long pendingActions, Long pendingDocuments, Long savedRights, List<LegalDto.RightSummaryDto> recentlyViewed, List<CaseDto.CaseResponse> recentCases, List<UpdateAndSearchDto.NotificationItemDto> recentNotifications, List<UpdateAndSearchDto.LegalUpdateItemDto> relevantUpdates) {
            this.activeCases = activeCases;
            this.resolvedCases = resolvedCases;
            this.pendingActions = pendingActions;
            this.pendingDocuments = pendingDocuments;
            this.savedRights = savedRights;
            this.recentlyViewed = recentlyViewed;
            this.recentCases = recentCases;
            this.recentNotifications = recentNotifications;
            this.relevantUpdates = relevantUpdates;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long activeCases;
            private Long resolvedCases;
            private Long pendingActions;
            private Long pendingDocuments;
            private Long savedRights;
            private List<LegalDto.RightSummaryDto> recentlyViewed;
            private List<CaseDto.CaseResponse> recentCases;
            private List<UpdateAndSearchDto.NotificationItemDto> recentNotifications;
            private List<UpdateAndSearchDto.LegalUpdateItemDto> relevantUpdates;

            public Builder activeCases(Long activeCases) { this.activeCases = activeCases; return this; }
            public Builder resolvedCases(Long resolvedCases) { this.resolvedCases = resolvedCases; return this; }
            public Builder pendingActions(Long pendingActions) { this.pendingActions = pendingActions; return this; }
            public Builder pendingDocuments(Long pendingDocuments) { this.pendingDocuments = pendingDocuments; return this; }
            public Builder savedRights(Long savedRights) { this.savedRights = savedRights; return this; }
            public Builder recentlyViewed(List<LegalDto.RightSummaryDto> recentlyViewed) { this.recentlyViewed = recentlyViewed; return this; }
            public Builder recentCases(List<CaseDto.CaseResponse> recentCases) { this.recentCases = recentCases; return this; }
            public Builder recentNotifications(List<UpdateAndSearchDto.NotificationItemDto> recentNotifications) { this.recentNotifications = recentNotifications; return this; }
            public Builder relevantUpdates(List<UpdateAndSearchDto.LegalUpdateItemDto> relevantUpdates) { this.relevantUpdates = relevantUpdates; return this; }

            public CitizenDashboardStats build() {
                return new CitizenDashboardStats(activeCases, resolvedCases, pendingActions, pendingDocuments, savedRights, recentlyViewed, recentCases, recentNotifications, relevantUpdates);
            }
        }

        public Long getActiveCases() { return activeCases; }
        public void setActiveCases(Long activeCases) { this.activeCases = activeCases; }
        public Long getResolvedCases() { return resolvedCases; }
        public void setResolvedCases(Long resolvedCases) { this.resolvedCases = resolvedCases; }
        public Long getPendingActions() { return pendingActions; }
        public void setPendingActions(Long pendingActions) { this.pendingActions = pendingActions; }
        public Long getPendingDocuments() { return pendingDocuments; }
        public void setPendingDocuments(Long pendingDocuments) { this.pendingDocuments = pendingDocuments; }
        public Long getSavedRights() { return savedRights; }
        public void setSavedRights(Long savedRights) { this.savedRights = savedRights; }
        public List<LegalDto.RightSummaryDto> getRecentlyViewed() { return recentlyViewed; }
        public void setRecentlyViewed(List<LegalDto.RightSummaryDto> recentlyViewed) { this.recentlyViewed = recentlyViewed; }
        public List<CaseDto.CaseResponse> getRecentCases() { return recentCases; }
        public void setRecentCases(List<CaseDto.CaseResponse> recentCases) { this.recentCases = recentCases; }
        public List<UpdateAndSearchDto.NotificationItemDto> getRecentNotifications() { return recentNotifications; }
        public void setRecentNotifications(List<UpdateAndSearchDto.NotificationItemDto> recentNotifications) { this.recentNotifications = recentNotifications; }
        public List<UpdateAndSearchDto.LegalUpdateItemDto> getRelevantUpdates() { return relevantUpdates; }
        public void setRelevantUpdates(List<UpdateAndSearchDto.LegalUpdateItemDto> relevantUpdates) { this.relevantUpdates = relevantUpdates; }
    }

    public static class ContentManagerDashboardStats {
        private Long totalRights;
        private Long publishedRights;
        private Long underReviewRights;
        private Long draftRights;
        private Long outdatedRights;
        private Long totalLegalUpdates;
        private Long pendingReports;
        private Long totalKnowledgeChunks;
        private Long embeddedChunks;
        private Long pendingEmbeddings;
        private List<LegalDto.RightSummaryDto> recentContent;
        private Map<String, Long> categoryDistribution;

        public ContentManagerDashboardStats() {}
        public ContentManagerDashboardStats(Long totalRights, Long publishedRights, Long underReviewRights, Long draftRights, Long outdatedRights, Long totalLegalUpdates, Long pendingReports, Long totalKnowledgeChunks, Long embeddedChunks, Long pendingEmbeddings, List<LegalDto.RightSummaryDto> recentContent, Map<String, Long> categoryDistribution) {
            this.totalRights = totalRights;
            this.publishedRights = publishedRights;
            this.underReviewRights = underReviewRights;
            this.draftRights = draftRights;
            this.outdatedRights = outdatedRights;
            this.totalLegalUpdates = totalLegalUpdates;
            this.pendingReports = pendingReports;
            this.totalKnowledgeChunks = totalKnowledgeChunks;
            this.embeddedChunks = embeddedChunks;
            this.pendingEmbeddings = pendingEmbeddings;
            this.recentContent = recentContent;
            this.categoryDistribution = categoryDistribution;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long totalRights;
            private Long publishedRights;
            private Long underReviewRights;
            private Long draftRights;
            private Long outdatedRights;
            private Long totalLegalUpdates;
            private Long pendingReports;
            private Long totalKnowledgeChunks;
            private Long embeddedChunks;
            private Long pendingEmbeddings;
            private List<LegalDto.RightSummaryDto> recentContent;
            private Map<String, Long> categoryDistribution;

            public Builder totalRights(Long totalRights) { this.totalRights = totalRights; return this; }
            public Builder publishedRights(Long publishedRights) { this.publishedRights = publishedRights; return this; }
            public Builder underReviewRights(Long underReviewRights) { this.underReviewRights = underReviewRights; return this; }
            public Builder draftRights(Long draftRights) { this.draftRights = draftRights; return this; }
            public Builder outdatedRights(Long outdatedRights) { this.outdatedRights = outdatedRights; return this; }
            public Builder totalLegalUpdates(Long totalLegalUpdates) { this.totalLegalUpdates = totalLegalUpdates; return this; }
            public Builder pendingReports(Long pendingReports) { this.pendingReports = pendingReports; return this; }
            public Builder totalKnowledgeChunks(Long totalKnowledgeChunks) { this.totalKnowledgeChunks = totalKnowledgeChunks; return this; }
            public Builder embeddedChunks(Long embeddedChunks) { this.embeddedChunks = embeddedChunks; return this; }
            public Builder pendingEmbeddings(Long pendingEmbeddings) { this.pendingEmbeddings = pendingEmbeddings; return this; }
            public Builder recentContent(List<LegalDto.RightSummaryDto> recentContent) { this.recentContent = recentContent; return this; }
            public Builder categoryDistribution(Map<String, Long> categoryDistribution) { this.categoryDistribution = categoryDistribution; return this; }

            public ContentManagerDashboardStats build() {
                return new ContentManagerDashboardStats(totalRights, publishedRights, underReviewRights, draftRights, outdatedRights, totalLegalUpdates, pendingReports, totalKnowledgeChunks, embeddedChunks, pendingEmbeddings, recentContent, categoryDistribution);
            }
        }

        public Long getTotalRights() { return totalRights; }
        public void setTotalRights(Long totalRights) { this.totalRights = totalRights; }
        public Long getPublishedRights() { return publishedRights; }
        public void setPublishedRights(Long publishedRights) { this.publishedRights = publishedRights; }
        public Long getUnderReviewRights() { return underReviewRights; }
        public void setUnderReviewRights(Long underReviewRights) { this.underReviewRights = underReviewRights; }
        public Long getDraftRights() { return draftRights; }
        public void setDraftRights(Long draftRights) { this.draftRights = draftRights; }
        public Long getOutdatedRights() { return outdatedRights; }
        public void setOutdatedRights(Long outdatedRights) { this.outdatedRights = outdatedRights; }
        public Long getTotalLegalUpdates() { return totalLegalUpdates; }
        public void setTotalLegalUpdates(Long totalLegalUpdates) { this.totalLegalUpdates = totalLegalUpdates; }
        public Long getPendingReports() { return pendingReports; }
        public void setPendingReports(Long pendingReports) { this.pendingReports = pendingReports; }
        public Long getTotalKnowledgeChunks() { return totalKnowledgeChunks; }
        public void setTotalKnowledgeChunks(Long totalKnowledgeChunks) { this.totalKnowledgeChunks = totalKnowledgeChunks; }
        public Long getEmbeddedChunks() { return embeddedChunks; }
        public void setEmbeddedChunks(Long embeddedChunks) { this.embeddedChunks = embeddedChunks; }
        public Long getPendingEmbeddings() { return pendingEmbeddings; }
        public void setPendingEmbeddings(Long pendingEmbeddings) { this.pendingEmbeddings = pendingEmbeddings; }
        public List<LegalDto.RightSummaryDto> getRecentContent() { return recentContent; }
        public void setRecentContent(List<LegalDto.RightSummaryDto> recentContent) { this.recentContent = recentContent; }
        public Map<String, Long> getCategoryDistribution() { return categoryDistribution; }
        public void setCategoryDistribution(Map<String, Long> categoryDistribution) { this.categoryDistribution = categoryDistribution; }
    }

    public static class AdministratorDashboardStats {
        private Long totalUsers;
        private Long activeUsers;
        private Long inactiveUsers;
        private Long totalCases;
        private Long openCases;
        private Long underReviewCases;
        private Long resolvedCases;
        private Long closedCases;
        private Long totalSearches;
        private Long aiQueriesCount;
        private Double averageFeedbackRating;
        private List<MonthlyTrend> userGrowth;
        private List<StatusCount> casesByStatus;
        private List<CategoryCount> popularCategories;
        private List<AiUsagePoint> aiUsageTrend;

        public AdministratorDashboardStats() {}
        public AdministratorDashboardStats(Long totalUsers, Long activeUsers, Long inactiveUsers, Long totalCases, Long openCases, Long underReviewCases, Long resolvedCases, Long closedCases, Long totalSearches, Long aiQueriesCount, Double averageFeedbackRating, List<MonthlyTrend> userGrowth, List<StatusCount> casesByStatus, List<CategoryCount> popularCategories, List<AiUsagePoint> aiUsageTrend) {
            this.totalUsers = totalUsers;
            this.activeUsers = activeUsers;
            this.inactiveUsers = inactiveUsers;
            this.totalCases = totalCases;
            this.openCases = openCases;
            this.underReviewCases = underReviewCases;
            this.resolvedCases = resolvedCases;
            this.closedCases = closedCases;
            this.totalSearches = totalSearches;
            this.aiQueriesCount = aiQueriesCount;
            this.averageFeedbackRating = averageFeedbackRating;
            this.userGrowth = userGrowth;
            this.casesByStatus = casesByStatus;
            this.popularCategories = popularCategories;
            this.aiUsageTrend = aiUsageTrend;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long totalUsers;
            private Long activeUsers;
            private Long inactiveUsers;
            private Long totalCases;
            private Long openCases;
            private Long underReviewCases;
            private Long resolvedCases;
            private Long closedCases;
            private Long totalSearches;
            private Long aiQueriesCount;
            private Double averageFeedbackRating;
            private List<MonthlyTrend> userGrowth;
            private List<StatusCount> casesByStatus;
            private List<CategoryCount> popularCategories;
            private List<AiUsagePoint> aiUsageTrend;

            public Builder totalUsers(Long totalUsers) { this.totalUsers = totalUsers; return this; }
            public Builder activeUsers(Long activeUsers) { this.activeUsers = activeUsers; return this; }
            public Builder inactiveUsers(Long inactiveUsers) { this.inactiveUsers = inactiveUsers; return this; }
            public Builder totalCases(Long totalCases) { this.totalCases = totalCases; return this; }
            public Builder openCases(Long openCases) { this.openCases = openCases; return this; }
            public Builder underReviewCases(Long underReviewCases) { this.underReviewCases = underReviewCases; return this; }
            public Builder resolvedCases(Long resolvedCases) { this.resolvedCases = resolvedCases; return this; }
            public Builder closedCases(Long closedCases) { this.closedCases = closedCases; return this; }
            public Builder totalSearches(Long totalSearches) { this.totalSearches = totalSearches; return this; }
            public Builder aiQueriesCount(Long aiQueriesCount) { this.aiQueriesCount = aiQueriesCount; return this; }
            public Builder averageFeedbackRating(Double averageFeedbackRating) { this.averageFeedbackRating = averageFeedbackRating; return this; }
            public Builder userGrowth(List<MonthlyTrend> userGrowth) { this.userGrowth = userGrowth; return this; }
            public Builder casesByStatus(List<StatusCount> casesByStatus) { this.casesByStatus = casesByStatus; return this; }
            public Builder popularCategories(List<CategoryCount> popularCategories) { this.popularCategories = popularCategories; return this; }
            public Builder aiUsageTrend(List<AiUsagePoint> aiUsageTrend) { this.aiUsageTrend = aiUsageTrend; return this; }

            public AdministratorDashboardStats build() {
                return new AdministratorDashboardStats(totalUsers, activeUsers, inactiveUsers, totalCases, openCases, underReviewCases, resolvedCases, closedCases, totalSearches, aiQueriesCount, averageFeedbackRating, userGrowth, casesByStatus, popularCategories, aiUsageTrend);
            }
        }

        public Long getTotalUsers() { return totalUsers; }
        public void setTotalUsers(Long totalUsers) { this.totalUsers = totalUsers; }
        public Long getActiveUsers() { return activeUsers; }
        public void setActiveUsers(Long activeUsers) { this.activeUsers = activeUsers; }
        public Long getInactiveUsers() { return inactiveUsers; }
        public void setInactiveUsers(Long inactiveUsers) { this.inactiveUsers = inactiveUsers; }
        public Long getTotalCases() { return totalCases; }
        public void setTotalCases(Long totalCases) { this.totalCases = totalCases; }
        public Long getOpenCases() { return openCases; }
        public void setOpenCases(Long openCases) { this.openCases = openCases; }
        public Long getUnderReviewCases() { return underReviewCases; }
        public void setUnderReviewCases(Long underReviewCases) { this.underReviewCases = underReviewCases; }
        public Long getResolvedCases() { return resolvedCases; }
        public void setResolvedCases(Long resolvedCases) { this.resolvedCases = resolvedCases; }
        public Long getClosedCases() { return closedCases; }
        public void setClosedCases(Long closedCases) { this.closedCases = closedCases; }
        public Long getTotalSearches() { return totalSearches; }
        public void setTotalSearches(Long totalSearches) { this.totalSearches = totalSearches; }
        public Long getAiQueriesCount() { return aiQueriesCount; }
        public void setAiQueriesCount(Long aiQueriesCount) { this.aiQueriesCount = aiQueriesCount; }
        public Double getAverageFeedbackRating() { return averageFeedbackRating; }
        public void setAverageFeedbackRating(Double averageFeedbackRating) { this.averageFeedbackRating = averageFeedbackRating; }
        public List<MonthlyTrend> getUserGrowth() { return userGrowth; }
        public void setUserGrowth(List<MonthlyTrend> userGrowth) { this.userGrowth = userGrowth; }
        public List<StatusCount> getCasesByStatus() { return casesByStatus; }
        public void setCasesByStatus(List<StatusCount> casesByStatus) { this.casesByStatus = casesByStatus; }
        public List<CategoryCount> getPopularCategories() { return popularCategories; }
        public void setPopularCategories(List<CategoryCount> popularCategories) { this.popularCategories = popularCategories; }
        public List<AiUsagePoint> getAiUsageTrend() { return aiUsageTrend; }
        public void setAiUsageTrend(List<AiUsagePoint> aiUsageTrend) { this.aiUsageTrend = aiUsageTrend; }
    }

    public static class MonthlyTrend {
        private String month;
        private Long count;

        public MonthlyTrend() {}
        public MonthlyTrend(String month, Long count) {
            this.month = month;
            this.count = count;
        }

        public String getMonth() { return month; }
        public void setMonth(String month) { this.month = month; }
        public Long getCount() { return count; }
        public void setCount(Long count) { this.count = count; }
    }

    public static class StatusCount {
        private String status;
        private Long count;

        public StatusCount() {}
        public StatusCount(String status, Long count) {
            this.status = status;
            this.count = count;
        }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public Long getCount() { return count; }
        public void setCount(Long count) { this.count = count; }
    }

    public static class CategoryCount {
        private String category;
        private Long count;

        public CategoryCount() {}
        public CategoryCount(String category, Long count) {
            this.category = category;
            this.count = count;
        }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public Long getCount() { return count; }
        public void setCount(Long count) { this.count = count; }
    }

    public static class AiUsagePoint {
        private String date;
        private Long count;

        public AiUsagePoint() {}
        public AiUsagePoint(String date, Long count) {
            this.date = date;
            this.count = count;
        }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public Long getCount() { return count; }
        public void setCount(Long count) { this.count = count; }
    }

    public static class FeedbackItemDto {
        private Long id;
        private Long userId;
        private String userName;
        private Integer rating;
        private String feedbackType;
        private String comment;
        private String featureArea;
        private LocalDateTime createdAt;

        public FeedbackItemDto() {}
        public FeedbackItemDto(Long id, Long userId, String userName, Integer rating, String feedbackType, String comment, String featureArea, LocalDateTime createdAt) {
            this.id = id;
            this.userId = userId;
            this.userName = userName;
            this.rating = rating;
            this.feedbackType = feedbackType;
            this.comment = comment;
            this.featureArea = featureArea;
            this.createdAt = createdAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Long userId;
            private String userName;
            private Integer rating;
            private String feedbackType;
            private String comment;
            private String featureArea;
            private LocalDateTime createdAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder userId(Long userId) { this.userId = userId; return this; }
            public Builder userName(String userName) { this.userName = userName; return this; }
            public Builder rating(Integer rating) { this.rating = rating; return this; }
            public Builder feedbackType(String feedbackType) { this.feedbackType = feedbackType; return this; }
            public Builder comment(String comment) { this.comment = comment; return this; }
            public Builder featureArea(String featureArea) { this.featureArea = featureArea; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

            public FeedbackItemDto build() {
                return new FeedbackItemDto(id, userId, userName, rating, feedbackType, comment, featureArea, createdAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        public Integer getRating() { return rating; }
        public void setRating(Integer rating) { this.rating = rating; }
        public String getFeedbackType() { return feedbackType; }
        public void setFeedbackType(String feedbackType) { this.feedbackType = feedbackType; }
        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }
        public String getFeatureArea() { return featureArea; }
        public void setFeatureArea(String featureArea) { this.featureArea = featureArea; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }

    public static class AuditLogItemDto {
        private Long id;
        private Long userId;
        private String userName;
        private String userRole;
        private String action;
        private String entityType;
        private Long entityId;
        private String details;
        private String ipAddress;
        private LocalDateTime createdAt;

        public AuditLogItemDto() {}
        public AuditLogItemDto(Long id, Long userId, String userName, String userRole, String action, String entityType, Long entityId, String details, String ipAddress, LocalDateTime createdAt) {
            this.id = id;
            this.userId = userId;
            this.userName = userName;
            this.userRole = userRole;
            this.action = action;
            this.entityType = entityType;
            this.entityId = entityId;
            this.details = details;
            this.ipAddress = ipAddress;
            this.createdAt = createdAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Long userId;
            private String userName;
            private String userRole;
            private String action;
            private String entityType;
            private Long entityId;
            private String details;
            private String ipAddress;
            private LocalDateTime createdAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder actorEmail(String actorEmail) { this.userName = actorEmail; return this; }
            public Builder entityName(String entityName) { return this; }
            public Builder userId(Long userId) { this.userId = userId; return this; }
            public Builder userName(String userName) { this.userName = userName; return this; }
            public Builder userRole(String userRole) { this.userRole = userRole; return this; }
            public Builder action(String action) { this.action = action; return this; }
            public Builder entityType(String entityType) { this.entityType = entityType; return this; }
            public Builder entityId(Long entityId) { this.entityId = entityId; return this; }
            public Builder entityId(String entityId) {
                if (entityId != null && entityId.matches("\\d+")) {
                    this.entityId = Long.parseLong(entityId);
                }
                return this;
            }
            public Builder details(String details) { this.details = details; return this; }
            public Builder ipAddress(String ipAddress) { this.ipAddress = ipAddress; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

            public AuditLogItemDto build() {
                return new AuditLogItemDto(id, userId, userName, userRole, action, entityType, entityId, details, ipAddress, createdAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        public String getUserRole() { return userRole; }
        public void setUserRole(String userRole) { this.userRole = userRole; }
        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }
        public String getEntityType() { return entityType; }
        public void setEntityType(String entityType) { this.entityType = entityType; }
        public Long getEntityId() { return entityId; }
        public void setEntityId(Long entityId) { this.entityId = entityId; }
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
        public String getIpAddress() { return ipAddress; }
        public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }

    public static class FeedbackRequest {
        private String feedbackType;
        private Integer rating;
        private String comments;
        private String featureArea;

        public FeedbackRequest() {}
        public FeedbackRequest(String feedbackType, Integer rating, String comments, String featureArea) {
            this.feedbackType = feedbackType;
            this.rating = rating;
            this.comments = comments;
            this.featureArea = featureArea;
        }

        public String getFeedbackType() { return feedbackType; }
        public void setFeedbackType(String feedbackType) { this.feedbackType = feedbackType; }
        public Integer getRating() { return rating; }
        public void setRating(Integer rating) { this.rating = rating; }
        public String getComments() { return comments; }
        public void setComments(String comments) { this.comments = comments; }
        public String getFeatureArea() { return featureArea; }
        public void setFeatureArea(String featureArea) { this.featureArea = featureArea; }
    }

    public static class FeedbackResponse {
        private Long id;
        private String userEmail;
        private String feedbackType;
        private Integer rating;
        private String comments;
        private LocalDateTime createdAt;

        public FeedbackResponse() {}
        public FeedbackResponse(Long id, String userEmail, String feedbackType, Integer rating, String comments, LocalDateTime createdAt) {
            this.id = id;
            this.userEmail = userEmail;
            this.feedbackType = feedbackType;
            this.rating = rating;
            this.comments = comments;
            this.createdAt = createdAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String userEmail;
            private String feedbackType;
            private Integer rating;
            private String comments;
            private LocalDateTime createdAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder userEmail(String userEmail) { this.userEmail = userEmail; return this; }
            public Builder feedbackType(String feedbackType) { this.feedbackType = feedbackType; return this; }
            public Builder rating(Integer rating) { this.rating = rating; return this; }
            public Builder comments(String comments) { this.comments = comments; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

            public FeedbackResponse build() {
                return new FeedbackResponse(id, userEmail, feedbackType, rating, comments, createdAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getUserEmail() { return userEmail; }
        public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
        public String getFeedbackType() { return feedbackType; }
        public void setFeedbackType(String feedbackType) { this.feedbackType = feedbackType; }
        public Integer getRating() { return rating; }
        public void setRating(Integer rating) { this.rating = rating; }
        public String getComments() { return comments; }
        public void setComments(String comments) { this.comments = comments; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }

    public static class EmbeddingItemDto {
        private Long id;
        private Long rightId;
        private String rightTitle;
        private String sourceType;
        private String contentChunk;
        private String metadataJson;
        private String status;
        private Integer dimension;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public EmbeddingItemDto() {}
        public EmbeddingItemDto(Long id, Long rightId, String rightTitle, String sourceType, String contentChunk, String metadataJson, String status, Integer dimension, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.id = id;
            this.rightId = rightId;
            this.rightTitle = rightTitle;
            this.sourceType = sourceType;
            this.contentChunk = contentChunk;
            this.metadataJson = metadataJson;
            this.status = status;
            this.dimension = dimension;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Long rightId;
            private String rightTitle;
            private String sourceType;
            private String contentChunk;
            private String metadataJson;
            private String status;
            private Integer dimension;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder rightId(Long rightId) { this.rightId = rightId; return this; }
            public Builder rightTitle(String rightTitle) { this.rightTitle = rightTitle; return this; }
            public Builder sourceType(String sourceType) { this.sourceType = sourceType; return this; }
            public Builder contentChunk(String contentChunk) { this.contentChunk = contentChunk; return this; }
            public Builder metadataJson(String metadataJson) { this.metadataJson = metadataJson; return this; }
            public Builder status(String status) { this.status = status; return this; }
            public Builder dimension(Integer dimension) { this.dimension = dimension; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
            public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

            public EmbeddingItemDto build() {
                return new EmbeddingItemDto(id, rightId, rightTitle, sourceType, contentChunk, metadataJson, status, dimension, createdAt, updatedAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getRightId() { return rightId; }
        public void setRightId(Long rightId) { this.rightId = rightId; }
        public String getRightTitle() { return rightTitle; }
        public void setRightTitle(String rightTitle) { this.rightTitle = rightTitle; }
        public String getSourceType() { return sourceType; }
        public void setSourceType(String sourceType) { this.sourceType = sourceType; }
        public String getContentChunk() { return contentChunk; }
        public void setContentChunk(String contentChunk) { this.contentChunk = contentChunk; }
        public String getMetadataJson() { return metadataJson; }
        public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public Integer getDimension() { return dimension; }
        public void setDimension(Integer dimension) { this.dimension = dimension; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        public LocalDateTime getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    }

    public static class ContentReportResponse {
        private Long id;
        private Long rightId;
        private String rightTitle;
        private String reporterEmail;
        private String reason;
        private String details;
        private String status;
        private LocalDateTime createdAt;

        public ContentReportResponse() {}
        public ContentReportResponse(Long id, Long rightId, String rightTitle, String reporterEmail, String reason, String details, String status, LocalDateTime createdAt) {
            this.id = id;
            this.rightId = rightId;
            this.rightTitle = rightTitle;
            this.reporterEmail = reporterEmail;
            this.reason = reason;
            this.details = details;
            this.status = status;
            this.createdAt = createdAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Long rightId;
            private String rightTitle;
            private String reporterEmail;
            private String reason;
            private String details;
            private String status;
            private LocalDateTime createdAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder rightId(Long rightId) { this.rightId = rightId; return this; }
            public Builder rightTitle(String rightTitle) { this.rightTitle = rightTitle; return this; }
            public Builder reporterEmail(String reporterEmail) { this.reporterEmail = reporterEmail; return this; }
            public Builder reason(String reason) { this.reason = reason; return this; }
            public Builder details(String details) { this.details = details; return this; }
            public Builder status(String status) { this.status = status; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

            public ContentReportResponse build() {
                return new ContentReportResponse(id, rightId, rightTitle, reporterEmail, reason, details, status, createdAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getRightId() { return rightId; }
        public void setRightId(Long rightId) { this.rightId = rightId; }
        public String getRightTitle() { return rightTitle; }
        public void setRightTitle(String rightTitle) { this.rightTitle = rightTitle; }
        public String getReporterEmail() { return reporterEmail; }
        public void setReporterEmail(String reporterEmail) { this.reporterEmail = reporterEmail; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }
}
