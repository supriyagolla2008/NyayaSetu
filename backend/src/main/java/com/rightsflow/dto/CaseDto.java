package com.rightsflow.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CaseDto {

    public static class CaseCreateRequest {
        private String title;
        private Long categoryId;
        private Long rightId;
        private String problemDescription;
        private String relatedLaws;
        private String relatedProvisions;
        private String authorityName;
        private String serviceName;
        private List<String> initialActions;
        private String notes;

        public CaseCreateRequest() {}
        public CaseCreateRequest(String title, Long categoryId, Long rightId, String problemDescription, String relatedLaws, String relatedProvisions, String authorityName, String serviceName, List<String> initialActions, String notes) {
            this.title = title;
            this.categoryId = categoryId;
            this.rightId = rightId;
            this.problemDescription = problemDescription;
            this.relatedLaws = relatedLaws;
            this.relatedProvisions = relatedProvisions;
            this.authorityName = authorityName;
            this.serviceName = serviceName;
            this.initialActions = initialActions;
            this.notes = notes;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String title;
            private Long categoryId;
            private Long rightId;
            private String problemDescription;
            private String relatedLaws;
            private String relatedProvisions;
            private String authorityName;
            private String serviceName;
            private List<String> initialActions;
            private String notes;

            public Builder title(String title) { this.title = title; return this; }
            public Builder categoryId(Long categoryId) { this.categoryId = categoryId; return this; }
            public Builder rightId(Long rightId) { this.rightId = rightId; return this; }
            public Builder problemDescription(String problemDescription) { this.problemDescription = problemDescription; return this; }
            public Builder relatedLaws(String relatedLaws) { this.relatedLaws = relatedLaws; return this; }
            public Builder relatedProvisions(String relatedProvisions) { this.relatedProvisions = relatedProvisions; return this; }
            public Builder authorityName(String authorityName) { this.authorityName = authorityName; return this; }
            public Builder serviceName(String serviceName) { this.serviceName = serviceName; return this; }
            public Builder initialActions(List<String> initialActions) { this.initialActions = initialActions; return this; }
            public Builder notes(String notes) { this.notes = notes; return this; }
            public CaseCreateRequest build() {
                return new CaseCreateRequest(title, categoryId, rightId, problemDescription, relatedLaws, relatedProvisions, authorityName, serviceName, initialActions, notes);
            }
        }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public Long getCategoryId() { return categoryId; }
        public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
        public Long getRightId() { return rightId; }
        public void setRightId(Long rightId) { this.rightId = rightId; }
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
        public List<String> getInitialActions() { return initialActions; }
        public void setInitialActions(List<String> initialActions) { this.initialActions = initialActions; }
        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
    }

    public static class CaseUpdateRequest {
        private String title;
        private String status;
        private String notes;

        public CaseUpdateRequest() {}
        public CaseUpdateRequest(String title, String status, String notes) {
            this.title = title;
            this.status = status;
            this.notes = notes;
        }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
    }

    public static class StatusTransitionRequest {
        private String toStatus;
        private String remarks;

        public StatusTransitionRequest() {}
        public StatusTransitionRequest(String toStatus, String remarks) {
            this.toStatus = toStatus;
            this.remarks = remarks;
        }

        public String getToStatus() { return toStatus; }
        public void setToStatus(String toStatus) { this.toStatus = toStatus; }
        public String getRemarks() { return remarks; }
        public void setRemarks(String remarks) { this.remarks = remarks; }
    }

    public static class ActionCreateUpdateRequest {
        private String title;
        private String description;
        private String actionType;
        private String status;
        private LocalDate dueDate;
        private Integer orderIndex;

        public ActionCreateUpdateRequest() {}
        public ActionCreateUpdateRequest(String title, String description, String actionType, String status, LocalDate dueDate, Integer orderIndex) {
            this.title = title;
            this.description = description;
            this.actionType = actionType;
            this.status = status;
            this.dueDate = dueDate;
            this.orderIndex = orderIndex;
        }

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
    }

    public static class CaseResponse {
        private Long id;
        private String caseNumber;
        private Long userId;
        private String userName;
        private Long categoryId;
        private String categoryName;
        private String categoryColor;
        private Long rightId;
        private String rightTitle;
        private String rightSlug;
        private String title;
        private String problemDescription;
        private String relatedLaws;
        private String relatedProvisions;
        private String authorityName;
        private String serviceName;
        private String status;
        private String notes;
        private Integer completionPercentage;
        private List<CaseStatusHistoryDto> timeline;
        private List<CaseActionItemDto> actions;
        private List<CaseDocumentItemDto> documents;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public CaseResponse() {}
        public CaseResponse(Long id, String caseNumber, Long userId, String userName, Long categoryId, String categoryName, String categoryColor, Long rightId, String rightTitle, String rightSlug, String title, String problemDescription, String relatedLaws, String relatedProvisions, String authorityName, String serviceName, String status, String notes, Integer completionPercentage, List<CaseStatusHistoryDto> timeline, List<CaseActionItemDto> actions, List<CaseDocumentItemDto> documents, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.id = id;
            this.caseNumber = caseNumber;
            this.userId = userId;
            this.userName = userName;
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.categoryColor = categoryColor;
            this.rightId = rightId;
            this.rightTitle = rightTitle;
            this.rightSlug = rightSlug;
            this.title = title;
            this.problemDescription = problemDescription;
            this.relatedLaws = relatedLaws;
            this.relatedProvisions = relatedProvisions;
            this.authorityName = authorityName;
            this.serviceName = serviceName;
            this.status = status;
            this.notes = notes;
            this.completionPercentage = completionPercentage;
            this.timeline = timeline;
            this.actions = actions;
            this.documents = documents;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String caseNumber;
            private Long userId;
            private String userName;
            private Long categoryId;
            private String categoryName;
            private String categoryColor;
            private Long rightId;
            private String rightTitle;
            private String rightSlug;
            private String title;
            private String problemDescription;
            private String relatedLaws;
            private String relatedProvisions;
            private String authorityName;
            private String serviceName;
            private String status;
            private String notes;
            private Integer completionPercentage;
            private List<CaseStatusHistoryDto> timeline;
            private List<CaseActionItemDto> actions;
            private List<CaseDocumentItemDto> documents;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder caseNumber(String caseNumber) { this.caseNumber = caseNumber; return this; }
            public Builder userId(Long userId) { this.userId = userId; return this; }
            public Builder userName(String userName) { this.userName = userName; return this; }
            public Builder categoryId(Long categoryId) { this.categoryId = categoryId; return this; }
            public Builder categoryName(String categoryName) { this.categoryName = categoryName; return this; }
            public Builder categoryColor(String categoryColor) { this.categoryColor = categoryColor; return this; }
            public Builder rightId(Long rightId) { this.rightId = rightId; return this; }
            public Builder rightTitle(String rightTitle) { this.rightTitle = rightTitle; return this; }
            public Builder rightSlug(String rightSlug) { this.rightSlug = rightSlug; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder problemDescription(String problemDescription) { this.problemDescription = problemDescription; return this; }
            public Builder relatedLaws(String relatedLaws) { this.relatedLaws = relatedLaws; return this; }
            public Builder relatedProvisions(String relatedProvisions) { this.relatedProvisions = relatedProvisions; return this; }
            public Builder authorityName(String authorityName) { this.authorityName = authorityName; return this; }
            public Builder serviceName(String serviceName) { this.serviceName = serviceName; return this; }
            public Builder status(String status) { this.status = status; return this; }
            public Builder notes(String notes) { this.notes = notes; return this; }
            public Builder completionPercentage(Integer completionPercentage) { this.completionPercentage = completionPercentage; return this; }
            public Builder timeline(List<CaseStatusHistoryDto> timeline) { this.timeline = timeline; return this; }
            public Builder actions(List<CaseActionItemDto> actions) { this.actions = actions; return this; }
            public Builder documents(List<CaseDocumentItemDto> documents) { this.documents = documents; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
            public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

            public CaseResponse build() {
                return new CaseResponse(id, caseNumber, userId, userName, categoryId, categoryName, categoryColor, rightId, rightTitle, rightSlug, title, problemDescription, relatedLaws, relatedProvisions, authorityName, serviceName, status, notes, completionPercentage, timeline, actions, documents, createdAt, updatedAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getCaseNumber() { return caseNumber; }
        public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        public Long getCategoryId() { return categoryId; }
        public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
        public String getCategoryName() { return categoryName; }
        public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
        public String getCategoryColor() { return categoryColor; }
        public void setCategoryColor(String categoryColor) { this.categoryColor = categoryColor; }
        public Long getRightId() { return rightId; }
        public void setRightId(Long rightId) { this.rightId = rightId; }
        public String getRightTitle() { return rightTitle; }
        public void setRightTitle(String rightTitle) { this.rightTitle = rightTitle; }
        public String getRightSlug() { return rightSlug; }
        public void setRightSlug(String rightSlug) { this.rightSlug = rightSlug; }
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
        public Integer getCompletionPercentage() { return completionPercentage; }
        public void setCompletionPercentage(Integer completionPercentage) { this.completionPercentage = completionPercentage; }
        public List<CaseStatusHistoryDto> getTimeline() { return timeline; }
        public void setTimeline(List<CaseStatusHistoryDto> timeline) { this.timeline = timeline; }
        public List<CaseActionItemDto> getActions() { return actions; }
        public void setActions(List<CaseActionItemDto> actions) { this.actions = actions; }
        public List<CaseDocumentItemDto> getDocuments() { return documents; }
        public void setDocuments(List<CaseDocumentItemDto> documents) { this.documents = documents; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        public LocalDateTime getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    }

    public static class CaseStatusHistoryDto {
        private Long id;
        private String fromStatus;
        private String toStatus;
        private String remarks;
        private String changedBy;
        private LocalDateTime changedAt;

        public CaseStatusHistoryDto() {}
        public CaseStatusHistoryDto(Long id, String fromStatus, String toStatus, String remarks, String changedBy, LocalDateTime changedAt) {
            this.id = id;
            this.fromStatus = fromStatus;
            this.toStatus = toStatus;
            this.remarks = remarks;
            this.changedBy = changedBy;
            this.changedAt = changedAt;
        }

        public static Builder builder() { return new Builder(); }
                public static class Builder {
            private Long id;
            private String fromStatus;
            private String toStatus;
            private String remarks;
            private String changedBy;
            private LocalDateTime changedAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder fromStatus(String fromStatus) { this.fromStatus = fromStatus; return this; }
            public Builder toStatus(String toStatus) { this.toStatus = toStatus; return this; }
            public Builder remarks(String remarks) { this.remarks = remarks; return this; }
            public Builder changedBy(String changedBy) { this.changedBy = changedBy; return this; }
            public Builder changedByName(String changedByName) { this.changedBy = changedByName; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.changedAt = createdAt; return this; }
            public Builder changedAt(LocalDateTime changedAt) { this.changedAt = changedAt; return this; }
            public CaseStatusHistoryDto build() {
                return new CaseStatusHistoryDto(id, fromStatus, toStatus, remarks, changedBy, changedAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getFromStatus() { return fromStatus; }
        public void setFromStatus(String fromStatus) { this.fromStatus = fromStatus; }
        public String getToStatus() { return toStatus; }
        public void setToStatus(String toStatus) { this.toStatus = toStatus; }
        public String getRemarks() { return remarks; }
        public void setRemarks(String remarks) { this.remarks = remarks; }
        public String getChangedBy() { return changedBy; }
        public void setChangedBy(String changedBy) { this.changedBy = changedBy; }
        public LocalDateTime getChangedAt() { return changedAt; }
        public void setChangedAt(LocalDateTime changedAt) { this.changedAt = changedAt; }
    }

    public static class CaseActionItemDto {
        private Long id;
        private String title;
        private String description;
        private String actionType;
        private String status;
        private LocalDate dueDate;
        private Integer orderIndex;
        private LocalDateTime completedAt;
        private LocalDateTime createdAt;

        public CaseActionItemDto() {}
        public CaseActionItemDto(Long id, String title, String description, String actionType, String status, LocalDate dueDate, Integer orderIndex, LocalDateTime completedAt, LocalDateTime createdAt) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.actionType = actionType;
            this.status = status;
            this.dueDate = dueDate;
            this.orderIndex = orderIndex;
            this.completedAt = completedAt;
            this.createdAt = createdAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String title;
            private String description;
            private String actionType;
            private String status;
            private LocalDate dueDate;
            private Integer orderIndex;
            private LocalDateTime completedAt;
            private LocalDateTime createdAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder description(String description) { this.description = description; return this; }
            public Builder actionType(String actionType) { this.actionType = actionType; return this; }
            public Builder status(String status) { this.status = status; return this; }
            public Builder dueDate(LocalDate dueDate) { this.dueDate = dueDate; return this; }
            public Builder orderIndex(Integer orderIndex) { this.orderIndex = orderIndex; return this; }
            public Builder completedAt(LocalDateTime completedAt) { this.completedAt = completedAt; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
            public CaseActionItemDto build() {
                return new CaseActionItemDto(id, title, description, actionType, status, dueDate, orderIndex, completedAt, createdAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
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
        public LocalDateTime getCompletedAt() { return completedAt; }
        public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }

        public static class CaseDocumentItemDto {
        private Long id;
        private Long documentId;
        private String fileName;
        private String originalName;
        private String fileType;
        private Long fileSize;
        private String documentCategory;
        private String documentType;
        private Boolean isVerified;
        private LocalDateTime uploadedAt;

        public CaseDocumentItemDto() {}
        public CaseDocumentItemDto(Long id, Long documentId, String fileName, String originalName, String fileType, Long fileSize, String documentCategory, String documentType, Boolean isVerified, LocalDateTime uploadedAt) {
            this.id = id;
            this.documentId = documentId;
            this.fileName = fileName;
            this.originalName = originalName;
            this.fileType = fileType;
            this.fileSize = fileSize;
            this.documentCategory = documentCategory;
            this.documentType = documentType;
            this.isVerified = isVerified;
            this.uploadedAt = uploadedAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Long documentId;
            private String fileName;
            private String originalName;
            private String fileType;
            private Long fileSize;
            private String documentCategory;
            private String documentType;
            private Boolean isVerified;
            private LocalDateTime uploadedAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder documentId(Long documentId) { this.documentId = documentId; return this; }
            public Builder fileName(String fileName) { this.fileName = fileName; return this; }
            public Builder originalName(String originalName) { this.originalName = originalName; return this; }
            public Builder fileType(String fileType) { this.fileType = fileType; return this; }
            public Builder fileSize(Long fileSize) { this.fileSize = fileSize; return this; }
            public Builder documentCategory(String documentCategory) { this.documentCategory = documentCategory; return this; }
            public Builder documentType(String documentType) { this.documentType = documentType; return this; }
            public Builder isVerified(Boolean isVerified) { this.isVerified = isVerified; return this; }
            public Builder uploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; return this; }
            public CaseDocumentItemDto build() {
                return new CaseDocumentItemDto(id, documentId, fileName, originalName, fileType, fileSize, documentCategory, documentType, isVerified, uploadedAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getDocumentId() { return documentId; }
        public void setDocumentId(Long documentId) { this.documentId = documentId; }
        public String getFileName() { return fileName; }
        public void setFileName(String fileName) { this.fileName = fileName; }
        public String getOriginalName() { return originalName; }
        public void setOriginalName(String originalName) { this.originalName = originalName; }
        public String getFileType() { return fileType; }
        public void setFileType(String fileType) { this.fileType = fileType; }
        public Long getFileSize() { return fileSize; }
        public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
        public String getDocumentCategory() { return documentCategory; }
        public void setDocumentCategory(String documentCategory) { this.documentCategory = documentCategory; }
        public String getDocumentType() { return documentType; }
        public void setDocumentType(String documentType) { this.documentType = documentType; }
        public Boolean getIsVerified() { return isVerified; }
        public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
        public LocalDateTime getUploadedAt() { return uploadedAt; }
        public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
    }

    public static class DocumentUploadRequest {
        private String documentType;
        private String description;

        public DocumentUploadRequest() {}
        public DocumentUploadRequest(String documentType, String description) {
            this.documentType = documentType;
            this.description = description;
        }

        public String getDocumentType() { return documentType; }
        public void setDocumentType(String documentType) { this.documentType = documentType; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
