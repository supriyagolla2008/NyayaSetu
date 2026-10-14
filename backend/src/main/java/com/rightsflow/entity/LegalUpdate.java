package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "legal_updates")
public class LegalUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private LegalCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id")
    private Right right;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;

    @Column(name = "what_changed", nullable = false, columnDefinition = "TEXT")
    private String whatChanged;

    @Column(name = "who_is_affected", columnDefinition = "TEXT")
    private String whoIsAffected;

    @Column(name = "effective_date", nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "published_date")
    private LocalDate publishedDate = LocalDate.now();

    @Column(name = "source_name", length = 200)
    private String sourceName;

    @Column(name = "source_authority", length = 200)
    private String sourceAuthority;

    @Column(name = "official_url", length = 500)
    private String officialUrl;

    @Column(name = "last_verified")
    private LocalDate lastVerified = LocalDate.now();

    private Integer version = 1;

    @Column(length = 30)
    private String status = "PUBLISHED";

    @Column(name = "view_count")
    private Long viewCount = 0L;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public LegalUpdate() {}

    public LegalUpdate(Long id, LegalCategory category, Right right, String title, String summary, String whatChanged, String whoIsAffected, LocalDate effectiveDate, LocalDate publishedDate, String sourceName, String sourceAuthority, String officialUrl, LocalDate lastVerified, Integer version, String status, Long viewCount, LocalDateTime createdAt) {
        this.id = id;
        this.category = category;
        this.right = right;
        this.title = title;
        this.summary = summary;
        this.whatChanged = whatChanged;
        this.whoIsAffected = whoIsAffected;
        this.effectiveDate = effectiveDate;
        this.publishedDate = publishedDate != null ? publishedDate : LocalDate.now();
        this.sourceName = sourceName;
        this.sourceAuthority = sourceAuthority;
        this.officialUrl = officialUrl;
        this.lastVerified = lastVerified != null ? lastVerified : LocalDate.now();
        this.version = version != null ? version : 1;
        this.status = status != null ? status : "PUBLISHED";
        this.viewCount = viewCount != null ? viewCount : 0L;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private LegalCategory category;
        private Right right;
        private String title;
        private String summary;
        private String whatChanged;
        private String whoIsAffected;
        private LocalDate effectiveDate;
        private LocalDate publishedDate = LocalDate.now();
        private String sourceName;
        private String sourceAuthority;
        private String officialUrl;
        private LocalDate lastVerified = LocalDate.now();
        private Integer version = 1;
        private String status = "PUBLISHED";
        private Long viewCount = 0L;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder category(LegalCategory category) { this.category = category; return this; }
        public Builder right(Right right) { this.right = right; return this; }
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
        public LegalUpdate build() {
            return new LegalUpdate(id, category, right, title, summary, whatChanged, whoIsAffected, effectiveDate, publishedDate, sourceName, sourceAuthority, officialUrl, lastVerified, version, status, viewCount, createdAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LegalCategory getCategory() { return category; }
    public void setCategory(LegalCategory category) { this.category = category; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
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
