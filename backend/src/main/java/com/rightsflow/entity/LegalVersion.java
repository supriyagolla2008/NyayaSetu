package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "legal_versions")
public class LegalVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id", nullable = false)
    private Right right;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "simple_explanation", columnDefinition = "TEXT")
    private String simpleExplanation;

    @Column(name = "detailed_explanation", columnDefinition = "TEXT")
    private String detailedExplanation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by")
    private User changedBy;

    @Column(name = "change_summary", length = 500)
    private String changeSummary;

    @Column(name = "verification_status", length = 30)
    private String verificationStatus = "VERIFIED";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public LegalVersion() {}

    public LegalVersion(Long id, Right right, Integer version, String title, String simpleExplanation, String detailedExplanation, User changedBy, String changeSummary, String verificationStatus, LocalDateTime createdAt) {
        this.id = id;
        this.right = right;
        this.version = version;
        this.title = title;
        this.simpleExplanation = simpleExplanation;
        this.detailedExplanation = detailedExplanation;
        this.changedBy = changedBy;
        this.changeSummary = changeSummary;
        this.verificationStatus = verificationStatus;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Right right;
        private Integer version;
        private String title;
        private String simpleExplanation;
        private String detailedExplanation;
        private User changedBy;
        private String changeSummary;
        private String verificationStatus = "VERIFIED";
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder right(Right right) { this.right = right; return this; }
        public Builder version(Integer version) { this.version = version; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder simpleExplanation(String simpleExplanation) { this.simpleExplanation = simpleExplanation; return this; }
        public Builder detailedExplanation(String detailedExplanation) { this.detailedExplanation = detailedExplanation; return this; }
        public Builder changedBy(User changedBy) { this.changedBy = changedBy; return this; }
        public Builder changeSummary(String changeSummary) { this.changeSummary = changeSummary; return this; }
        public Builder verificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public LegalVersion build() {
            return new LegalVersion(id, right, version, title, simpleExplanation, detailedExplanation, changedBy, changeSummary, verificationStatus, createdAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSimpleExplanation() { return simpleExplanation; }
    public void setSimpleExplanation(String simpleExplanation) { this.simpleExplanation = simpleExplanation; }
    public String getDetailedExplanation() { return detailedExplanation; }
    public void setDetailedExplanation(String detailedExplanation) { this.detailedExplanation = detailedExplanation; }
    public User getChangedBy() { return changedBy; }
    public void setChangedBy(User changedBy) { this.changedBy = changedBy; }
    public String getChangeSummary() { return changeSummary; }
    public void setChangeSummary(String changeSummary) { this.changeSummary = changeSummary; }
    public String getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
