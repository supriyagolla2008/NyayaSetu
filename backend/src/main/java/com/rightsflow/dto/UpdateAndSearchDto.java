package com.rightsflow.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UpdateAndSearchDto {

    public static class LegalUpdateItemDto {
        private Long id;
        private Long categoryId;
        private String categoryName;
        private String categoryColor;
        private Long rightId;
        private String title;
        private String summary;
        private String whatChanged;
        private String whoIsAffected;
        private LocalDate effectiveDate;
        private LocalDate publishedDate;
        private String sourceName;
        private String sourceAuthority;
        private String officialUrl;
        private LocalDate lastVerified;
        private Integer version;
        private String status;
        private Long viewCount;
        private LocalDateTime createdAt;

        public LegalUpdateItemDto() {}
        public LegalUpdateItemDto(Long id, Long categoryId, String categoryName, String categoryColor, Long rightId, String title, String summary, String whatChanged, String whoIsAffected, LocalDate effectiveDate, LocalDate publishedDate, String sourceName, String sourceAuthority, String officialUrl, LocalDate lastVerified, Integer version, String status, Long viewCount, LocalDateTime createdAt) {
            this.id = id;
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.categoryColor = categoryColor;
            this.rightId = rightId;
            this.title = title;
            this.summary = summary;
            this.whatChanged = whatChanged;
            this.whoIsAffected = whoIsAffected;
            this.effectiveDate = effectiveDate;
            this.publishedDate = publishedDate;
            this.sourceName = sourceName;
            this.sourceAuthority = sourceAuthority;
            this.officialUrl = officialUrl;
            this.lastVerified = lastVerified;
            this.version = version;
            this.status = status;
            this.viewCount = viewCount;
            this.createdAt = createdAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Long categoryId;
            private String categoryName;
            private String categoryColor;
            private Long rightId;
            private String title;
            private String summary;
            private String whatChanged;
            private String whoIsAffected;
            private LocalDate effectiveDate;
            private LocalDate publishedDate;
            private String sourceName;
            private String sourceAuthority;
            private String officialUrl;
            private LocalDate lastVerified;
            private Integer version;
            private String status;
            private Long viewCount;
            private LocalDateTime createdAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder categoryId(Long categoryId) { this.categoryId = categoryId; return this; }
            public Builder categoryName(String categoryName) { this.categoryName = categoryName; return this; }
            public Builder categoryColor(String categoryColor) { this.categoryColor = categoryColor; return this; }
            public Builder rightId(Long rightId) { this.rightId = rightId; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder summary(String summary) { this.summary = summary; return this; }
            public Builder whatChanged(String whatChanged) { this.whatChanged = whatChanged; return this; }
            public Builder whoIsAffected(String whoIsAffected) { this.whoIsAffected = whoIsAffected; return this; }
            public Builder effectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; return this; }
            public Builder publishedDate(LocalDate publishedDate) { this.publishedDate = publishedDate; return this; }
            public Builder sourceName(String sourceName) { this.sourceName = sourceName; return this; }
            public Builder sourceAuthority(String sourceAuthority) { this.sourceAuthority = sourceAuthority; return this; }
            public Builder officialUrl(String officialUrl) { this.officialUrl = officialUrl; return this; }
            public Builder lastVerified(LocalDate lastVerified) { this.lastVerified = lastVerified; return this; }
            public Builder version(Integer version) { this.version = version; return this; }
            public Builder status(String status) { this.status = status; return this; }
            public Builder viewCount(Long viewCount) { this.viewCount = viewCount; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

            public LegalUpdateItemDto build() {
                return new LegalUpdateItemDto(id, categoryId, categoryName, categoryColor, rightId, title, summary, whatChanged, whoIsAffected, effectiveDate, publishedDate, sourceName, sourceAuthority, officialUrl, lastVerified, version, status, viewCount, createdAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getCategoryId() { return categoryId; }
        public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
        public String getCategoryName() { return categoryName; }
        public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
        public String getCategoryColor() { return categoryColor; }
        public void setCategoryColor(String categoryColor) { this.categoryColor = categoryColor; }
        public Long getRightId() { return rightId; }
        public void setRightId(Long rightId) { this.rightId = rightId; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSummary() { return summary; }
        public void setSummary(String summary) { this.summary = summary; }
        public String getWhatChanged() { return whatChanged; }
        public void setWhatChanged(String whatChanged) { this.whatChanged = whatChanged; }
        public String getWhoIsAffected() { return whoIsAffected; }
        public void setWhoIsAffected(String whoIsAffected) { this.whoIsAffected = whoIsAffected; }
        public LocalDate getEffectiveDate() { return effectiveDate; }
        public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }
        public LocalDate getPublishedDate() { return publishedDate; }
        public void setPublishedDate(LocalDate publishedDate) { this.publishedDate = publishedDate; }
        public String getSourceName() { return sourceName; }
        public void setSourceName(String sourceName) { this.sourceName = sourceName; }
        public String getSourceAuthority() { return sourceAuthority; }
        public void setSourceAuthority(String sourceAuthority) { this.sourceAuthority = sourceAuthority; }
        public String getOfficialUrl() { return officialUrl; }
        public void setOfficialUrl(String officialUrl) { this.officialUrl = officialUrl; }
        public LocalDate getLastVerified() { return lastVerified; }
        public void setLastVerified(LocalDate lastVerified) { this.lastVerified = lastVerified; }
        public Integer getVersion() { return version; }
        public void setVersion(Integer version) { this.version = version; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public Long getViewCount() { return viewCount; }
        public void setViewCount(Long viewCount) { this.viewCount = viewCount; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }

    public static class LegalUpdateCreateRequest {
        private Long categoryId;
        private Long rightId;
        private String title;
        private String summary;
        private String whatChanged;
        private String whoIsAffected;
        private LocalDate effectiveDate;
        private String sourceName;
        private String sourceAuthority;
        private String officialUrl;
        private String status;

        public LegalUpdateCreateRequest() {}
        public LegalUpdateCreateRequest(Long categoryId, Long rightId, String title, String summary, String whatChanged, String whoIsAffected, LocalDate effectiveDate, String sourceName, String sourceAuthority, String officialUrl, String status) {
            this.categoryId = categoryId;
            this.rightId = rightId;
            this.title = title;
            this.summary = summary;
            this.whatChanged = whatChanged;
            this.whoIsAffected = whoIsAffected;
            this.effectiveDate = effectiveDate;
            this.sourceName = sourceName;
            this.sourceAuthority = sourceAuthority;
            this.officialUrl = officialUrl;
            this.status = status;
        }

        public Long getCategoryId() { return categoryId; }
        public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
        public Long getRightId() { return rightId; }
        public void setRightId(Long rightId) { this.rightId = rightId; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSummary() { return summary; }
        public void setSummary(String summary) { this.summary = summary; }
        public String getWhatChanged() { return whatChanged; }
        public void setWhatChanged(String whatChanged) { this.whatChanged = whatChanged; }
        public String getWhoIsAffected() { return whoIsAffected; }
        public void setWhoIsAffected(String whoIsAffected) { this.whoIsAffected = whoIsAffected; }
        public LocalDate getEffectiveDate() { return effectiveDate; }
        public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }
        public String getSourceName() { return sourceName; }
        public void setSourceName(String sourceName) { this.sourceName = sourceName; }
        public String getSourceAuthority() { return sourceAuthority; }
        public void setSourceAuthority(String sourceAuthority) { this.sourceAuthority = sourceAuthority; }
        public String getOfficialUrl() { return officialUrl; }
        public void setOfficialUrl(String officialUrl) { this.officialUrl = officialUrl; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    public static class GlobalSearchRequest {
        private String query;
        private Long categoryId;
        private String type;
        private Integer page;
        private Integer size;

        public GlobalSearchRequest() {}
        public GlobalSearchRequest(String query, Long categoryId, String type, Integer page, Integer size) {
            this.query = query;
            this.categoryId = categoryId;
            this.type = type;
            this.page = page;
            this.size = size;
        }

        public String getQuery() { return query; }
        public void setQuery(String query) { this.query = query; }
        public Long getCategoryId() { return categoryId; }
        public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public Integer getPage() { return page; }
        public void setPage(Integer page) { this.page = page; }
        public Integer getSize() { return size; }
        public void setSize(Integer size) { this.size = size; }
    }

    public static class SearchResultItem {
        private String id;
        private String type;
        private String title;
        private String subtitle;
        private String snippet;
        private String category;
        private String categoryColor;
        private String url;
        private String officialSource;
        private String lastVerified;
        private Double relevanceScore;

        public SearchResultItem() {}
        public SearchResultItem(String id, String type, String title, String subtitle, String snippet, String category, String categoryColor, String url, String officialSource, String lastVerified, Double relevanceScore) {
            this.id = id;
            this.type = type;
            this.title = title;
            this.subtitle = subtitle;
            this.snippet = snippet;
            this.category = category;
            this.categoryColor = categoryColor;
            this.url = url;
            this.officialSource = officialSource;
            this.lastVerified = lastVerified;
            this.relevanceScore = relevanceScore;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String id;
            private String type;
            private String title;
            private String subtitle;
            private String snippet;
            private String category;
            private String categoryColor;
            private String url;
            private String officialSource;
            private String lastVerified;
            private Double relevanceScore;

            public Builder id(String id) { this.id = id; return this; }
            public Builder type(String type) { this.type = type; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder subtitle(String subtitle) { this.subtitle = subtitle; return this; }
            public Builder snippet(String snippet) { this.snippet = snippet; return this; }
            public Builder category(String category) { this.category = category; return this; }
            public Builder categoryColor(String categoryColor) { this.categoryColor = categoryColor; return this; }
            public Builder url(String url) { this.url = url; return this; }
            public Builder officialSource(String officialSource) { this.officialSource = officialSource; return this; }
            public Builder lastVerified(String lastVerified) { this.lastVerified = lastVerified; return this; }
            public Builder relevanceScore(Double relevanceScore) { this.relevanceScore = relevanceScore; return this; }

            public SearchResultItem build() {
                return new SearchResultItem(id, type, title, subtitle, snippet, category, categoryColor, url, officialSource, lastVerified, relevanceScore);
            }
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSubtitle() { return subtitle; }
        public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
        public String getSnippet() { return snippet; }
        public void setSnippet(String snippet) { this.snippet = snippet; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public String getCategoryColor() { return categoryColor; }
        public void setCategoryColor(String categoryColor) { this.categoryColor = categoryColor; }
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public String getOfficialSource() { return officialSource; }
        public void setOfficialSource(String officialSource) { this.officialSource = officialSource; }
        public String getLastVerified() { return lastVerified; }
        public void setLastVerified(String lastVerified) { this.lastVerified = lastVerified; }
        public Double getRelevanceScore() { return relevanceScore; }
        public void setRelevanceScore(Double relevanceScore) { this.relevanceScore = relevanceScore; }
    }

    public static class GlobalSearchResponse {
        private String query;
        private Long totalResults;
        private List<SearchResultItem> results;
        private List<String> suggestions;
        private Boolean isSemanticMatch;

        public GlobalSearchResponse() {}
        public GlobalSearchResponse(String query, Long totalResults, List<SearchResultItem> results, List<String> suggestions, Boolean isSemanticMatch) {
            this.query = query;
            this.totalResults = totalResults;
            this.results = results;
            this.suggestions = suggestions;
            this.isSemanticMatch = isSemanticMatch;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String query;
            private Long totalResults;
            private List<SearchResultItem> results;
            private List<String> suggestions;
            private Boolean isSemanticMatch;

            public Builder query(String query) { this.query = query; return this; }
            public Builder totalResults(Long totalResults) { this.totalResults = totalResults; return this; }
            public Builder results(List<SearchResultItem> results) { this.results = results; return this; }
            public Builder suggestions(List<String> suggestions) { this.suggestions = suggestions; return this; }
            public Builder isSemanticMatch(Boolean isSemanticMatch) { this.isSemanticMatch = isSemanticMatch; return this; }

            public GlobalSearchResponse build() {
                return new GlobalSearchResponse(query, totalResults, results, suggestions, isSemanticMatch);
            }
        }

        public String getQuery() { return query; }
        public void setQuery(String query) { this.query = query; }
        public Long getTotalResults() { return totalResults; }
        public void setTotalResults(Long totalResults) { this.totalResults = totalResults; }
        public List<SearchResultItem> getResults() { return results; }
        public void setResults(List<SearchResultItem> results) { this.results = results; }
        public List<String> getSuggestions() { return suggestions; }
        public void setSuggestions(List<String> suggestions) { this.suggestions = suggestions; }
        public Boolean getIsSemanticMatch() { return isSemanticMatch; }
        public void setIsSemanticMatch(Boolean isSemanticMatch) { this.isSemanticMatch = isSemanticMatch; }
    }

    public static class NotificationItemDto {
        private Long id;
        private String title;
        private String message;
        private String type;
        private Boolean read;
        private String actionUrl;
        private LocalDateTime createdAt;

        public NotificationItemDto() {}
        public NotificationItemDto(Long id, String title, String message, String type, Boolean read, String actionUrl, LocalDateTime createdAt) {
            this.id = id;
            this.title = title;
            this.message = message;
            this.type = type;
            this.read = read;
            this.actionUrl = actionUrl;
            this.createdAt = createdAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String title;
            private String message;
            private String type;
            private Boolean read;
            private String actionUrl;
            private LocalDateTime createdAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder message(String message) { this.message = message; return this; }
            public Builder type(String type) { this.type = type; return this; }
            public Builder userId(Long userId) { return this; }
            public Builder caseId(Long caseId) { return this; }
            public Builder read(Boolean read) { this.read = read; return this; }
            public Builder isRead(Boolean isRead) { this.read = isRead; return this; }
            public Builder actionUrl(String actionUrl) { this.actionUrl = actionUrl; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

            public NotificationItemDto build() {
                return new NotificationItemDto(id, title, message, type, read, actionUrl, createdAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public Boolean getRead() { return read; }
        public void setRead(Boolean read) { this.read = read; }
        public String getActionUrl() { return actionUrl; }
        public void setActionUrl(String actionUrl) { this.actionUrl = actionUrl; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }
}
