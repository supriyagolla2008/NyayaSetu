import os

BASE_DIR = "/Users/gollasupriyashri/.gemini/antigravity/scratch/rightsflow/backend/src/main/java/com/rightsflow"

def write_file(rel_path, content):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        f.write(content.strip() + "\n")
    print(f"Wrote {rel_path}")

# ==========================================
# 1. ENTITY: LegalVersion.java
# ==========================================
write_file("entity/LegalVersion.java", """
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
""")

# ==========================================
# 2. ENTITY: CaseEntity.java
# ==========================================
write_file("entity/CaseEntity.java", """
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
""")

# ==========================================
# 3. ENTITY: CaseAction.java
# ==========================================
write_file("entity/CaseAction.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "case_actions")
public class CaseAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    private CaseEntity caseEntity;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "action_type", length = 50)
    private String actionType = "ACTION";

    @Column(nullable = false, length = 50)
    private String status = "PENDING";

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "order_index")
    private Integer orderIndex = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public CaseAction() {}

    public CaseAction(Long id, CaseEntity caseEntity, String title, String description, String actionType, String status, LocalDate dueDate, Integer orderIndex, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.caseEntity = caseEntity;
        this.title = title;
        this.description = description;
        this.actionType = actionType != null ? actionType : "ACTION";
        this.status = status != null ? status : "PENDING";
        this.dueDate = dueDate;
        this.orderIndex = orderIndex != null ? orderIndex : 0;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private CaseEntity caseEntity;
        private String title;
        private String description;
        private String actionType = "ACTION";
        private String status = "PENDING";
        private LocalDate dueDate;
        private Integer orderIndex = 0;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder caseEntity(CaseEntity caseEntity) { this.caseEntity = caseEntity; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder actionType(String actionType) { this.actionType = actionType; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder dueDate(LocalDate dueDate) { this.dueDate = dueDate; return this; }
        public Builder orderIndex(Integer orderIndex) { this.orderIndex = orderIndex; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public CaseAction build() {
            return new CaseAction(id, caseEntity, title, description, actionType, status, dueDate, orderIndex, createdAt, updatedAt);
        }
    }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public CaseEntity getCaseEntity() { return caseEntity; }
    public void setCaseEntity(CaseEntity caseEntity) { this.caseEntity = caseEntity; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
""")

# ==========================================
# 4. ENTITY: CaseStatusHistory.java
# ==========================================
write_file("entity/CaseStatusHistory.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "case_status_history")
public class CaseStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    private CaseEntity caseEntity;

    @Column(name = "from_status", length = 50)
    private String fromStatus;

    @Column(name = "to_status", nullable = false, length = 50)
    private String toStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by")
    private User changedBy;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public CaseStatusHistory() {}

    public CaseStatusHistory(Long id, CaseEntity caseEntity, String fromStatus, String toStatus, User changedBy, String remarks, LocalDateTime createdAt) {
        this.id = id;
        this.caseEntity = caseEntity;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.changedBy = changedBy;
        this.remarks = remarks;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private CaseEntity caseEntity;
        private String fromStatus;
        private String toStatus;
        private User changedBy;
        private String remarks;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder caseEntity(CaseEntity caseEntity) { this.caseEntity = caseEntity; return this; }
        public Builder fromStatus(String fromStatus) { this.fromStatus = fromStatus; return this; }
        public Builder toStatus(String toStatus) { this.toStatus = toStatus; return this; }
        public Builder changedBy(User changedBy) { this.changedBy = changedBy; return this; }
        public Builder remarks(String remarks) { this.remarks = remarks; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public CaseStatusHistory build() {
            return new CaseStatusHistory(id, caseEntity, fromStatus, toStatus, changedBy, remarks, createdAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public CaseEntity getCaseEntity() { return caseEntity; }
    public void setCaseEntity(CaseEntity caseEntity) { this.caseEntity = caseEntity; }
    public String getFromStatus() { return fromStatus; }
    public void setFromStatus(String fromStatus) { this.fromStatus = fromStatus; }
    public String getToStatus() { return toStatus; }
    public void setToStatus(String toStatus) { this.toStatus = toStatus; }
    public User getChangedBy() { return changedBy; }
    public void setChangedBy(User changedBy) { this.changedBy = changedBy; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
""")

# ==========================================
# 5. ENTITY: DocumentEntity.java & CaseDocument.java
# ==========================================
write_file("entity/DocumentEntity.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;

    @Column(name = "original_name", nullable = false, length = 255)
    private String originalName;

    @Column(name = "file_type", nullable = false, length = 100)
    private String fileType;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "file_path", nullable = false, length = 500)
    private String filePath;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public DocumentEntity() {}

    public DocumentEntity(Long id, User user, String fileName, String originalName, String fileType, Long fileSize, String filePath, String description, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.fileName = fileName;
        this.originalName = originalName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.description = description;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private String fileName;
        private String originalName;
        private String fileType;
        private Long fileSize;
        private String filePath;
        private String description;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder fileName(String fileName) { this.fileName = fileName; return this; }
        public Builder originalName(String originalName) { this.originalName = originalName; return this; }
        public Builder fileType(String fileType) { this.fileType = fileType; return this; }
        public Builder fileSize(Long fileSize) { this.fileSize = fileSize; return this; }
        public Builder filePath(String filePath) { this.filePath = filePath; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public DocumentEntity build() {
            return new DocumentEntity(id, user, fileName, originalName, fileType, fileSize, filePath, description, createdAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getOriginalName() { return originalName; }
    public void setOriginalName(String originalName) { this.originalName = originalName; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
""")

write_file("entity/CaseDocument.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "case_documents")
public class CaseDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    private CaseEntity caseEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private DocumentEntity document;

    @Column(name = "document_category", length = 100)
    private String documentCategory = "SUPPORTING_EVIDENCE";

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "uploaded_at", updatable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    public CaseDocument() {}

    public CaseDocument(Long id, CaseEntity caseEntity, DocumentEntity document, String documentCategory, Boolean isVerified, LocalDateTime uploadedAt) {
        this.id = id;
        this.caseEntity = caseEntity;
        this.document = document;
        this.documentCategory = documentCategory != null ? documentCategory : "SUPPORTING_EVIDENCE";
        this.isVerified = isVerified != null ? isVerified : false;
        this.uploadedAt = uploadedAt != null ? uploadedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private CaseEntity caseEntity;
        private DocumentEntity document;
        private String documentCategory = "SUPPORTING_EVIDENCE";
        private Boolean isVerified = false;
        private LocalDateTime uploadedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder caseEntity(CaseEntity caseEntity) { this.caseEntity = caseEntity; return this; }
        public Builder document(DocumentEntity document) { this.document = document; return this; }
        public Builder documentCategory(String documentCategory) { this.documentCategory = documentCategory; return this; }
        public Builder isVerified(Boolean isVerified) { this.isVerified = isVerified; return this; }
        public Builder uploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; return this; }
        public CaseDocument build() {
            return new CaseDocument(id, caseEntity, document, documentCategory, isVerified, uploadedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public CaseEntity getCaseEntity() { return caseEntity; }
    public void setCaseEntity(CaseEntity caseEntity) { this.caseEntity = caseEntity; }
    public DocumentEntity getDocument() { return document; }
    public void setDocument(DocumentEntity document) { this.document = document; }
    public String getDocumentCategory() { return documentCategory; }
    public void setDocumentCategory(String documentCategory) { this.documentCategory = documentCategory; }
    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}
""")

# ==========================================
# 6. ENTITY: Embedding.java & Notification.java & LegalUpdate.java
# ==========================================
write_file("entity/Embedding.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "embeddings")
public class Embedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id")
    private Right right;

    @Column(name = "source_type", length = 50)
    private String sourceType = "RIGHT";

    @Column(name = "content_chunk", nullable = false, columnDefinition = "TEXT")
    private String contentChunk;

    @Column(name = "metadata_json", columnDefinition = "TEXT")
    private String metadataJson;

    @Column(name = "embedding_vector", columnDefinition = "TEXT")
    private String embeddingVector;

    private Integer dimension = 768;

    @Column(length = 30)
    private String status = "EMBEDDED";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Embedding() {}

    public Embedding(Long id, Right right, String sourceType, String contentChunk, String metadataJson, String embeddingVector, Integer dimension, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.right = right;
        this.sourceType = sourceType != null ? sourceType : "RIGHT";
        this.contentChunk = contentChunk;
        this.metadataJson = metadataJson;
        this.embeddingVector = embeddingVector;
        this.dimension = dimension != null ? dimension : 768;
        this.status = status != null ? status : "EMBEDDED";
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Right right;
        private String sourceType = "RIGHT";
        private String contentChunk;
        private String metadataJson;
        private String embeddingVector;
        private Integer dimension = 768;
        private String status = "EMBEDDED";
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder right(Right right) { this.right = right; return this; }
        public Builder sourceType(String sourceType) { this.sourceType = sourceType; return this; }
        public Builder contentChunk(String contentChunk) { this.contentChunk = contentChunk; return this; }
        public Builder metadataJson(String metadataJson) { this.metadataJson = metadataJson; return this; }
        public Builder embeddingVector(String embeddingVector) { this.embeddingVector = embeddingVector; return this; }
        public Builder dimension(Integer dimension) { this.dimension = dimension; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public Embedding build() {
            return new Embedding(id, right, sourceType, contentChunk, metadataJson, embeddingVector, dimension, status, createdAt, updatedAt);
        }
    }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }
    public String getContentChunk() { return contentChunk; }
    public void setContentChunk(String contentChunk) { this.contentChunk = contentChunk; }
    public String getMetadataJson() { return metadataJson; }
    public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }
    public String getEmbeddingVector() { return embeddingVector; }
    public void setEmbeddingVector(String embeddingVector) { this.embeddingVector = embeddingVector; }
    public Integer getDimension() { return dimension; }
    public void setDimension(Integer dimension) { this.dimension = dimension; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
""")

write_file("entity/Notification.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    private CaseEntity caseEntity;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(length = 50)
    private String type = "INFO";

    @Column(name = "is_read")
    private Boolean isRead = false;

    @Column(name = "action_url", length = 255)
    private String actionUrl;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Notification() {}

    public Notification(Long id, User user, CaseEntity caseEntity, String title, String message, String type, Boolean isRead, String actionUrl, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.caseEntity = caseEntity;
        this.title = title;
        this.message = message;
        this.type = type != null ? type : "INFO";
        this.isRead = isRead != null ? isRead : false;
        this.actionUrl = actionUrl;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private CaseEntity caseEntity;
        private String title;
        private String message;
        private String type = "INFO";
        private Boolean isRead = false;
        private String actionUrl;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder caseEntity(CaseEntity caseEntity) { this.caseEntity = caseEntity; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder type(String type) { this.type = type; return this; }
        public Builder isRead(Boolean isRead) { this.isRead = isRead; return this; }
        public Builder actionUrl(String actionUrl) { this.actionUrl = actionUrl; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Notification build() {
            return new Notification(id, user, caseEntity, title, message, type, isRead, actionUrl, createdAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public CaseEntity getCaseEntity() { return caseEntity; }
    public void setCaseEntity(CaseEntity caseEntity) { this.caseEntity = caseEntity; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Boolean getIsRead() { return isRead; }
    public void setIsRead(Boolean isRead) { this.isRead = isRead; }
    public String getActionUrl() { return actionUrl; }
    public void setActionUrl(String actionUrl) { this.actionUrl = actionUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
""")

write_file("entity/LegalUpdate.java", """
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
""")

write_file("entity/Bookmark.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookmarks", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "right_id"})
})
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id", nullable = false)
    private Right right;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Bookmark() {}

    public Bookmark(Long id, User user, Right right, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.right = right;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private Right right;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder right(Right right) { this.right = right; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Bookmark build() { return new Bookmark(id, user, right, createdAt); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
""")

write_file("entity/RecentlyViewed.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recently_viewed")
public class RecentlyViewed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id", nullable = false)
    private Right right;

    @Column(name = "viewed_at")
    private LocalDateTime viewedAt = LocalDateTime.now();

    public RecentlyViewed() {}

    public RecentlyViewed(Long id, User user, Right right, LocalDateTime viewedAt) {
        this.id = id;
        this.user = user;
        this.right = right;
        this.viewedAt = viewedAt != null ? viewedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private Right right;
        private LocalDateTime viewedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder right(Right right) { this.right = right; return this; }
        public Builder viewedAt(LocalDateTime viewedAt) { this.viewedAt = viewedAt; return this; }
        public RecentlyViewed build() { return new RecentlyViewed(id, user, right, viewedAt); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
    public LocalDateTime getViewedAt() { return viewedAt; }
    public void setViewedAt(LocalDateTime viewedAt) { this.viewedAt = viewedAt; }
}
""")

write_file("entity/SearchHistory.java", """
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
""")

write_file("entity/Feedback.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "feedback_type", length = 50)
    private String feedbackType = "GENERAL";

    private Integer rating;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comments;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Feedback() {}

    public Feedback(Long id, User user, String feedbackType, Integer rating, String comments, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.feedbackType = feedbackType != null ? feedbackType : "GENERAL";
        this.rating = rating;
        this.comments = comments;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private String feedbackType = "GENERAL";
        private Integer rating;
        private String comments;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder feedbackType(String feedbackType) { this.feedbackType = feedbackType; return this; }
        public Builder rating(Integer rating) { this.rating = rating; return this; }
        public Builder comments(String comments) { this.comments = comments; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Feedback build() { return new Feedback(id, user, feedbackType, rating, comments, createdAt); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getFeedbackType() { return feedbackType; }
    public void setFeedbackType(String feedbackType) { this.feedbackType = feedbackType; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
""")

write_file("entity/ContentReport.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "content_reports")
public class ContentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id", nullable = false)
    private Right right;

    @Column(nullable = false, length = 255)
    private String reason;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(length = 30)
    private String status = "PENDING";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public ContentReport() {}

    public ContentReport(Long id, User user, Right right, String reason, String details, String status, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.right = right;
        this.reason = reason;
        this.details = details;
        this.status = status != null ? status : "PENDING";
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private Right right;
        private String reason;
        private String details;
        private String status = "PENDING";
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder right(Right right) { this.right = right; return this; }
        public Builder reason(String reason) { this.reason = reason; return this; }
        public Builder details(String details) { this.details = details; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public ContentReport build() { return new ContentReport(id, user, right, reason, details, status, createdAt); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
""")

write_file("entity/AuditLog.java", """
package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private User actor;

    @Column(name = "actor_email", length = 150)
    private String actorEmail;

    @Column(nullable = false, length = 100)
    private String action;

    @Column(name = "entity_name", nullable = false, length = 100)
    private String entityName;

    @Column(name = "entity_id", length = 100)
    private String entityId;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(name = "ip_address", length = 100)
    private String ipAddress;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public AuditLog() {}

    public AuditLog(Long id, User actor, String actorEmail, String action, String entityName, String entityId, String details, String ipAddress, LocalDateTime createdAt) {
        this.id = id;
        this.actor = actor;
        this.actorEmail = actorEmail;
        this.action = action;
        this.entityName = entityName;
        this.entityId = entityId;
        this.details = details;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User actor;
        private String actorEmail;
        private String action;
        private String entityName;
        private String entityId;
        private String details;
        private String ipAddress;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder actor(User actor) { this.actor = actor; return this; }
        public Builder actorEmail(String actorEmail) { this.actorEmail = actorEmail; return this; }
        public Builder action(String action) { this.action = action; return this; }
        public Builder entityName(String entityName) { this.entityName = entityName; return this; }
        public Builder entityId(String entityId) { this.entityId = entityId; return this; }
        public Builder details(String details) { this.details = details; return this; }
        public Builder ipAddress(String ipAddress) { this.ipAddress = ipAddress; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public AuditLog build() { return new AuditLog(id, actor, actorEmail, action, entityName, entityId, details, ipAddress, createdAt); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getActor() { return actor; }
    public void setActor(User actor) { this.actor = actor; }
    public String getActorEmail() { return actorEmail; }
    public void setActorEmail(String actorEmail) { this.actorEmail = actorEmail; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public String getEntityName() { return entityName; }
    public void setEntityName(String entityName) { this.entityName = entityName; }
    public String getEntityId() { return entityId; }
    public void setEntityId(String entityId) { this.entityId = entityId; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
""")

print("Entities generated successfully.")
