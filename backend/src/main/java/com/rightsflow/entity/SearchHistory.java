package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "search_history")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "query_text", nullable = false, length = 500)
    private String queryText;

    @Column(name = "category_filter", length = 100)
    private String categoryFilter;

    @Column(name = "results_count")
    private Integer resultsCount = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public SearchHistory() {}

    public SearchHistory(Long id, User user, String queryText, String categoryFilter, Integer resultsCount, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.queryText = queryText;
        this.categoryFilter = categoryFilter;
        this.resultsCount = resultsCount != null ? resultsCount : 0;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private String queryText;
        private String categoryFilter;
        private Integer resultsCount = 0;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder queryText(String queryText) { this.queryText = queryText; return this; }
        public Builder categoryFilter(String categoryFilter) { this.categoryFilter = categoryFilter; return this; }
        public Builder resultsCount(Integer resultsCount) { this.resultsCount = resultsCount; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public SearchHistory build() { return new SearchHistory(id, user, queryText, categoryFilter, resultsCount, createdAt); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getQueryText() { return queryText; }
    public void setQueryText(String queryText) { this.queryText = queryText; }
    public String getCategoryFilter() { return categoryFilter; }
    public void setCategoryFilter(String categoryFilter) { this.categoryFilter = categoryFilter; }
    public Integer getResultsCount() { return resultsCount; }
    public void setResultsCount(Integer resultsCount) { this.resultsCount = resultsCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
