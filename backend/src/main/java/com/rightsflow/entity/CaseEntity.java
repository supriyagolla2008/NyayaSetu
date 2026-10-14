package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cases")
public class CaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "case_number", nullable = false, unique = true, length = 50)
    private String caseNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private LegalCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id")
    private Right right;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "problem_description", nullable = false, columnDefinition = "TEXT")
    private String problemDescription;

    @Column(name = "related_laws", columnDefinition = "TEXT")
    private String relatedLaws;

    @Column(name = "related_provisions", columnDefinition = "TEXT")
    private String relatedProvisions;

    @Column(name = "authority_name", length = 255)
    private String authorityName;

    @Column(name = "service_name", length = 255)
    private String serviceName;

    @Column(nullable = false, length = 50)
    private String status = "CREATED";

    @Column(columnDefinition = "TEXT")
    private String notes;

    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CaseStatusHistory> statusHistory = new ArrayList<>();

    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CaseAction> actions = new ArrayList<>();

    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CaseDocument> documents = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public CaseEntity() {}

    public CaseEntity(Long id, String caseNumber, User user, LegalCategory category, Right right, String title, String problemDescription, String relatedLaws, String relatedProvisions, String authorityName, String serviceName, String status, String notes, List<CaseStatusHistory> statusHistory, List<CaseAction> actions, List<CaseDocument> documents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.caseNumber = caseNumber;
        this.user = user;
        this.category = category;
        this.right = right;
        this.title = title;
        this.problemDescription = problemDescription;
        this.relatedLaws = relatedLaws;
        this.relatedProvisions = relatedProvisions;
        this.authorityName = authorityName;
        this.serviceName = serviceName;
        this.status = status != null ? status : "CREATED";
        this.notes = notes;
        this.statusHistory = statusHistory != null ? statusHistory : new ArrayList<>();
        this.actions = actions != null ? actions : new ArrayList<>();
        this.documents = documents != null ? documents : new ArrayList<>();
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String caseNumber;
        private User user;
        private LegalCategory category;
        private Right right;
        private String title;
        private String problemDescription;
        private String relatedLaws;
        private String relatedProvisions;
        private String authorityName;
        private String serviceName;
        private String status = "CREATED";
        private String notes;
        private List<CaseStatusHistory> statusHistory = new ArrayList<>();
        private List<CaseAction> actions = new ArrayList<>();
        private List<CaseDocument> documents = new ArrayList<>();
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder caseNumber(String caseNumber) { this.caseNumber = caseNumber; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder category(LegalCategory category) { this.category = category; return this; }
        public Builder right(Right right) { this.right = right; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder problemDescription(String problemDescription) { this.problemDescription = problemDescription; return this; }
        public Builder relatedLaws(String relatedLaws) { this.relatedLaws = relatedLaws; return this; }
        public Builder relatedProvisions(String relatedProvisions) { this.relatedProvisions = relatedProvisions; return this; }
        public Builder authorityName(String authorityName) { this.authorityName = authorityName; return this; }
        public Builder serviceName(String serviceName) { this.serviceName = serviceName; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder notes(String notes) { this.notes = notes; return this; }
        public Builder statusHistory(List<CaseStatusHistory> statusHistory) { this.statusHistory = statusHistory; return this; }
        public Builder actions(List<CaseAction> actions) { this.actions = actions; return this; }
        public Builder documents(List<CaseDocument> documents) { this.documents = documents; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public CaseEntity build() {
            return new CaseEntity(id, caseNumber, user, category, right, title, problemDescription, relatedLaws, relatedProvisions, authorityName, serviceName, status, notes, statusHistory, actions, documents, createdAt, updatedAt);
        }
    }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LegalCategory getCategory() { return category; }
    public void setCategory(LegalCategory category) { this.category = category; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getProblemDescription() { return problemDescription; }
    public void setProblemDescription(String problemDescription) { this.problemDescription = problemDescription; }
    public String getRelatedLaws() { return relatedLaws; }
    public void setRelatedLaws(String relatedLaws) { this.relatedLaws = relatedLaws; }
    public String getRelatedProvisions() { return relatedProvisions; }
    public void setRelatedProvisions(String relatedProvisions) { this.relatedProvisions = relatedProvisions; }
    public String getAuthorityName() { return authorityName; }
    public void setAuthorityName(String authorityName) { this.authorityName = authorityName; }
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public List<CaseStatusHistory> getStatusHistory() { return statusHistory; }
    public void setStatusHistory(List<CaseStatusHistory> statusHistory) { this.statusHistory = statusHistory; }
    public List<CaseAction> getActions() { return actions; }
    public void setActions(List<CaseAction> actions) { this.actions = actions; }
    public List<CaseDocument> getDocuments() { return documents; }
    public void setDocuments(List<CaseDocument> documents) { this.documents = documents; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
