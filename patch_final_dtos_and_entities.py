import os

BASE = "/Users/gollasupriyashri/.gemini/antigravity/scratch/rightsflow/backend/src/main/java/com/rightsflow"

# 1. Update AnalyticsDto.java with missing classes
with open(f"{BASE}/dto/AnalyticsDto.java", "r") as f:
    analytics_content = f.read()

extra_analytics = """
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
"""

last_brace = analytics_content.rfind("}")
analytics_content = analytics_content[:last_brace] + extra_analytics
with open(f"{BASE}/dto/AnalyticsDto.java", "w") as f:
    f.write(analytics_content)
print("AnalyticsDto updated with extra classes.")


# 2. Update LegalDto.java with ServiceDto, StepDto, DocRequirementDto, and AuthorityDto extra fields
with open(f"{BASE}/dto/LegalDto.java", "r") as f:
    legal_content = f.read()

extra_legal = """
    public static class StepDto {
        private Integer stepNumber;
        private String title;
        private String instruction;
        private String estimatedDuration;

        public StepDto() {}
        public StepDto(Integer stepNumber, String title, String instruction, String estimatedDuration) {
            this.stepNumber = stepNumber;
            this.title = title;
            this.instruction = instruction;
            this.estimatedDuration = estimatedDuration;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Integer stepNumber;
            private String title;
            private String instruction;
            private String estimatedDuration;

            public Builder stepNumber(Integer stepNumber) { this.stepNumber = stepNumber; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder instruction(String instruction) { this.instruction = instruction; return this; }
            public Builder estimatedDuration(String estimatedDuration) { this.estimatedDuration = estimatedDuration; return this; }
            public StepDto build() { return new StepDto(stepNumber, title, instruction, estimatedDuration); }
        }

        public Integer getStepNumber() { return stepNumber; }
        public void setStepNumber(Integer stepNumber) { this.stepNumber = stepNumber; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getInstruction() { return instruction; }
        public void setInstruction(String instruction) { this.instruction = instruction; }
        public String getEstimatedDuration() { return estimatedDuration; }
        public void setEstimatedDuration(String estimatedDuration) { this.estimatedDuration = estimatedDuration; }
    }

    public static class DocRequirementDto {
        private String documentName;
        private Boolean isMandatory;
        private String description;

        public DocRequirementDto() {}
        public DocRequirementDto(String documentName, Boolean isMandatory, String description) {
            this.documentName = documentName;
            this.isMandatory = isMandatory;
            this.description = description;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String documentName;
            private Boolean isMandatory;
            private String description;

            public Builder documentName(String documentName) { this.documentName = documentName; return this; }
            public Builder isMandatory(Boolean isMandatory) { this.isMandatory = isMandatory; return this; }
            public Builder description(String description) { this.description = description; return this; }
            public DocRequirementDto build() { return new DocRequirementDto(documentName, isMandatory, description); }
        }

        public String getDocumentName() { return documentName; }
        public void setDocumentName(String documentName) { this.documentName = documentName; }
        public Boolean getIsMandatory() { return isMandatory; }
        public void setIsMandatory(Boolean isMandatory) { this.isMandatory = isMandatory; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class ServiceDto {
        private Long id;
        private Long authorityId;
        private String authorityName;
        private Long categoryId;
        private String categoryName;
        private String name;
        private String description;
        private String eligibilityCriteria;
        private String onlinePortalUrl;
        private String feeDetails;
        private String processingTimeEstimate;
        private List<StepDto> steps;
        private List<DocRequirementDto> requiredDocuments;

        public ServiceDto() {}
        public ServiceDto(Long id, Long authorityId, String authorityName, Long categoryId, String categoryName, String name, String description, String eligibilityCriteria, String onlinePortalUrl, String feeDetails, String processingTimeEstimate, List<StepDto> steps, List<DocRequirementDto> requiredDocuments) {
            this.id = id;
            this.authorityId = authorityId;
            this.authorityName = authorityName;
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.name = name;
            this.description = description;
            this.eligibilityCriteria = eligibilityCriteria;
            this.onlinePortalUrl = onlinePortalUrl;
            this.feeDetails = feeDetails;
            this.processingTimeEstimate = processingTimeEstimate;
            this.steps = steps;
            this.requiredDocuments = requiredDocuments;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Long authorityId;
            private String authorityName;
            private Long categoryId;
            private String categoryName;
            private String name;
            private String description;
            private String eligibilityCriteria;
            private String onlinePortalUrl;
            private String feeDetails;
            private String processingTimeEstimate;
            private List<StepDto> steps;
            private List<DocRequirementDto> requiredDocuments;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder authorityId(Long authorityId) { this.authorityId = authorityId; return this; }
            public Builder authorityName(String authorityName) { this.authorityName = authorityName; return this; }
            public Builder categoryId(Long categoryId) { this.categoryId = categoryId; return this; }
            public Builder categoryName(String categoryName) { this.categoryName = categoryName; return this; }
            public Builder name(String name) { this.name = name; return this; }
            public Builder description(String description) { this.description = description; return this; }
            public Builder eligibilityCriteria(String eligibilityCriteria) { this.eligibilityCriteria = eligibilityCriteria; return this; }
            public Builder onlinePortalUrl(String onlinePortalUrl) { this.onlinePortalUrl = onlinePortalUrl; return this; }
            public Builder feeDetails(String feeDetails) { this.feeDetails = feeDetails; return this; }
            public Builder processingTimeEstimate(String processingTimeEstimate) { this.processingTimeEstimate = processingTimeEstimate; return this; }
            public Builder steps(List<StepDto> steps) { this.steps = steps; return this; }
            public Builder requiredDocuments(List<DocRequirementDto> requiredDocuments) { this.requiredDocuments = requiredDocuments; return this; }

            public ServiceDto build() {
                return new ServiceDto(id, authorityId, authorityName, categoryId, categoryName, name, description, eligibilityCriteria, onlinePortalUrl, feeDetails, processingTimeEstimate, steps, requiredDocuments);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getAuthorityId() { return authorityId; }
        public void setAuthorityId(Long authorityId) { this.authorityId = authorityId; }
        public String getAuthorityName() { return authorityName; }
        public void setAuthorityName(String authorityName) { this.authorityName = authorityName; }
        public Long getCategoryId() { return categoryId; }
        public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
        public String getCategoryName() { return categoryName; }
        public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getEligibilityCriteria() { return eligibilityCriteria; }
        public void setEligibilityCriteria(String eligibilityCriteria) { this.eligibilityCriteria = eligibilityCriteria; }
        public String getOnlinePortalUrl() { return onlinePortalUrl; }
        public void setOnlinePortalUrl(String onlinePortalUrl) { this.onlinePortalUrl = onlinePortalUrl; }
        public String getFeeDetails() { return feeDetails; }
        public void setFeeDetails(String feeDetails) { this.feeDetails = feeDetails; }
        public String getProcessingTimeEstimate() { return processingTimeEstimate; }
        public void setProcessingTimeEstimate(String processingTimeEstimate) { this.processingTimeEstimate = processingTimeEstimate; }
        public List<StepDto> getSteps() { return steps; }
        public void setSteps(List<StepDto> steps) { this.steps = steps; }
        public List<DocRequirementDto> getRequiredDocuments() { return requiredDocuments; }
        public void setRequiredDocuments(List<DocRequirementDto> requiredDocuments) { this.requiredDocuments = requiredDocuments; }
    }
}
"""

last_brace = legal_content.rfind("}")
legal_content = legal_content[:last_brace] + extra_legal
with open(f"{BASE}/dto/LegalDto.java", "w") as f:
    f.write(legal_content)
print("LegalDto updated with ServiceDto, StepDto, DocRequirementDto.")


# 3. Patch CaseDto.CaseDocumentItemDto with documentId, originalName, documentCategory, isVerified
case_doc_replacement = """    public static class CaseDocumentItemDto {
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
    }"""

with open(f"{BASE}/dto/CaseDto.java", "r") as f:
    case_content = f.read()

import re
case_content = re.sub(r"public static class CaseDocumentItemDto \{.*?\n    \}", case_doc_replacement, case_content, flags=re.DOTALL)
with open(f"{BASE}/dto/CaseDto.java", "w") as f:
    f.write(case_content)
print("CaseDto updated with full CaseDocumentItemDto.")

# 4. Patch Authority entity with contactPhone, contactEmail, officialWebsite, physicalAddress, services
authority_code = """package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 100)
    private String type;

    @Column(name = "jurisdiction_level", length = 50)
    private String jurisdictionLevel;

    @Column(name = "jurisdiction_area", length = 100)
    private String jurisdictionArea;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 50)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String website;

    @Column(name = "portal_url", length = 255)
    private String portalUrl;

    @Column(name = "working_hours", length = 100)
    private String workingHours;

    @Column(name = "helpline_number", length = 50)
    private String helplineNumber;

    @Column(name = "category_name", length = 100)
    private String categoryName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LegalService> services = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Authority() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getJurisdictionLevel() { return jurisdictionLevel; }
    public void setJurisdictionLevel(String jurisdictionLevel) { this.jurisdictionLevel = jurisdictionLevel; }
    public String getJurisdictionArea() { return jurisdictionArea; }
    public void setJurisdictionArea(String jurisdictionArea) { this.jurisdictionArea = jurisdictionArea; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhysicalAddress() { return address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getContactPhone() { return phone != null ? phone : helplineNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContactEmail() { return email; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public String getOfficialWebsite() { return website != null ? website : portalUrl; }
    public String getPortalUrl() { return portalUrl; }
    public void setPortalUrl(String portalUrl) { this.portalUrl = portalUrl; }
    public String getWorkingHours() { return workingHours; }
    public void setWorkingHours(String workingHours) { this.workingHours = workingHours; }
    public String getHelplineNumber() { return helplineNumber; }
    public void setHelplineNumber(String helplineNumber) { this.helplineNumber = helplineNumber; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<LegalService> getServices() { return services; }
    public void setServices(List<LegalService> services) { this.services = services; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
"""

with open(f"{BASE}/entity/Authority.java", "w") as f:
    f.write(authority_code)
print("Authority entity updated with aliases.")

# 5. Patch LegalService entity with aliases
legal_service_code = """package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "legal_services")
public class LegalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private LegalCategory category;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "service_type", length = 100)
    private String serviceType;

    @Column(name = "portal_url", length = 255)
    private String portalUrl;

    @Column(name = "processing_time", length = 100)
    private String processingTime;

    @Column(name = "fee_details", length = 255)
    private String feeDetails;

    @Column(name = "online_available")
    private Boolean onlineAvailable = true;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceStep> steps = new ArrayList<>();

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceDocument> documents = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public LegalService() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Authority getAuthority() { return authority; }
    public void setAuthority(Authority authority) { this.authority = authority; }
    public LegalCategory getCategory() { return category; }
    public void setCategory(LegalCategory category) { this.category = category; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public String getPortalUrl() { return portalUrl; }
    public void setPortalUrl(String portalUrl) { this.portalUrl = portalUrl; }
    public String getOnlinePortalUrl() { return portalUrl; }
    public String getProcessingTime() { return processingTime; }
    public void setProcessingTime(String processingTime) { this.processingTime = processingTime; }
    public String getProcessingTimeEstimate() { return processingTime; }
    public String getFeeDetails() { return feeDetails; }
    public void setFeeDetails(String feeDetails) { this.feeDetails = feeDetails; }
    public Boolean getOnlineAvailable() { return onlineAvailable; }
    public void setOnlineAvailable(Boolean onlineAvailable) { this.onlineAvailable = onlineAvailable; }
    public String getEligibilityCriteria() { return "Eligible for all Indian citizens in relevant jurisdiction"; }
    public List<ServiceStep> getSteps() { return steps; }
    public void setSteps(List<ServiceStep> steps) { this.steps = steps; }
    public List<ServiceDocument> getDocuments() { return documents; }
    public void setDocuments(List<ServiceDocument> documents) { this.documents = documents; }
    public List<ServiceDocument> getRequiredDocuments() { return documents; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
"""

with open(f"{BASE}/entity/LegalService.java", "w") as f:
    f.write(legal_service_code)
print("LegalService entity updated with aliases.")

# 6. Patch ServiceStep and ServiceDocument
service_step_code = """package com.rightsflow.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_steps")
public class ServiceStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private LegalService service;

    @Column(name = "step_number", nullable = false)
    private Integer stepNumber;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "timeline_estimate", length = 100)
    private String timelineEstimate;

    @Column(columnDefinition = "TEXT")
    private String tips;

    public ServiceStep() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LegalService getService() { return service; }
    public void setService(LegalService service) { this.service = service; }
    public Integer getStepNumber() { return stepNumber; }
    public void setStepNumber(Integer stepNumber) { this.stepNumber = stepNumber; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getInstruction() { return description; }
    public String getTimelineEstimate() { return timelineEstimate; }
    public void setTimelineEstimate(String timelineEstimate) { this.timelineEstimate = timelineEstimate; }
    public String getEstimatedDuration() { return timelineEstimate; }
    public String getTips() { return tips; }
    public void setTips(String tips) { this.tips = tips; }
}
"""
with open(f"{BASE}/entity/ServiceStep.java", "w") as f:
    f.write(service_step_code)

service_doc_code = """package com.rightsflow.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_documents")
public class ServiceDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private LegalService service;

    @Column(name = "document_name", nullable = false, length = 255)
    private String documentName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean mandatory = true;

    @Column(name = "format_allowed", length = 100)
    private String formatAllowed;

    public ServiceDocument() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LegalService getService() { return service; }
    public void setService(LegalService service) { this.service = service; }
    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getMandatory() { return mandatory; }
    public void setMandatory(Boolean mandatory) { this.mandatory = mandatory; }
    public Boolean getIsMandatory() { return mandatory != null ? mandatory : true; }
    public String getFormatAllowed() { return formatAllowed; }
    public void setFormatAllowed(String formatAllowed) { this.formatAllowed = formatAllowed; }
}
"""
with open(f"{BASE}/entity/ServiceDocument.java", "w") as f:
    f.write(service_doc_code)
print("ServiceStep and ServiceDocument updated with aliases.")

# 7. Update LegalDto.AuthorityDto with contactPhone, contactEmail, officialWebsite, physicalAddress, services
auth_dto_patch = """    public static class AuthorityDto {
        private Long id;
        private String name;
        private String type;
        private String jurisdictionLevel;
        private String jurisdictionArea;
        private String address;
        private String physicalAddress;
        private String phone;
        private String contactPhone;
        private String email;
        private String contactEmail;
        private String website;
        private String officialWebsite;
        private String portalUrl;
        private String workingHours;
        private String helplineNumber;
        private String categoryName;
        private String description;
        private List<ServiceDto> services;

        public AuthorityDto() {}
        public AuthorityDto(Long id, String name, String type, String jurisdictionLevel, String jurisdictionArea, String address, String physicalAddress, String phone, String contactPhone, String email, String contactEmail, String website, String officialWebsite, String portalUrl, String workingHours, String helplineNumber, String categoryName, String description, List<ServiceDto> services) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.jurisdictionLevel = jurisdictionLevel;
            this.jurisdictionArea = jurisdictionArea;
            this.address = address;
            this.physicalAddress = physicalAddress != null ? physicalAddress : address;
            this.phone = phone;
            this.contactPhone = contactPhone != null ? contactPhone : phone;
            this.email = email;
            this.contactEmail = contactEmail != null ? contactEmail : email;
            this.website = website;
            this.officialWebsite = officialWebsite != null ? officialWebsite : website;
            this.portalUrl = portalUrl;
            this.workingHours = workingHours;
            this.helplineNumber = helplineNumber;
            this.categoryName = categoryName;
            this.description = description;
            this.services = services;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String name;
            private String type;
            private String jurisdictionLevel;
            private String jurisdictionArea;
            private String address;
            private String physicalAddress;
            private String phone;
            private String contactPhone;
            private String email;
            private String contactEmail;
            private String website;
            private String officialWebsite;
            private String portalUrl;
            private String workingHours;
            private String helplineNumber;
            private String categoryName;
            private String description;
            private List<ServiceDto> services;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder name(String name) { this.name = name; return this; }
            public Builder type(String type) { this.type = type; return this; }
            public Builder jurisdictionLevel(String jurisdictionLevel) { this.jurisdictionLevel = jurisdictionLevel; return this; }
            public Builder jurisdictionArea(String jurisdictionArea) { this.jurisdictionArea = jurisdictionArea; return this; }
            public Builder address(String address) { this.address = address; return this; }
            public Builder physicalAddress(String physicalAddress) { this.physicalAddress = physicalAddress; return this; }
            public Builder phone(String phone) { this.phone = phone; return this; }
            public Builder contactPhone(String contactPhone) { this.contactPhone = contactPhone; return this; }
            public Builder email(String email) { this.email = email; return this; }
            public Builder contactEmail(String contactEmail) { this.contactEmail = contactEmail; return this; }
            public Builder website(String website) { this.website = website; return this; }
            public Builder officialWebsite(String officialWebsite) { this.officialWebsite = officialWebsite; return this; }
            public Builder portalUrl(String portalUrl) { this.portalUrl = portalUrl; return this; }
            public Builder workingHours(String workingHours) { this.workingHours = workingHours; return this; }
            public Builder helplineNumber(String helplineNumber) { this.helplineNumber = helplineNumber; return this; }
            public Builder categoryName(String categoryName) { this.categoryName = categoryName; return this; }
            public Builder description(String description) { this.description = description; return this; }
            public Builder services(List<ServiceDto> services) { this.services = services; return this; }

            public AuthorityDto build() {
                return new AuthorityDto(id, name, type, jurisdictionLevel, jurisdictionArea, address, physicalAddress, phone, contactPhone, email, contactEmail, website, officialWebsite, portalUrl, workingHours, helplineNumber, categoryName, description, services);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getJurisdictionLevel() { return jurisdictionLevel; }
        public void setJurisdictionLevel(String jurisdictionLevel) { this.jurisdictionLevel = jurisdictionLevel; }
        public String getJurisdictionArea() { return jurisdictionArea; }
        public void setJurisdictionArea(String jurisdictionArea) { this.jurisdictionArea = jurisdictionArea; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getPhysicalAddress() { return physicalAddress; }
        public void setPhysicalAddress(String physicalAddress) { this.physicalAddress = physicalAddress; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getContactPhone() { return contactPhone; }
        public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getContactEmail() { return contactEmail; }
        public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
        public String getWebsite() { return website; }
        public void setWebsite(String website) { this.website = website; }
        public String getOfficialWebsite() { return officialWebsite; }
        public void setOfficialWebsite(String officialWebsite) { this.officialWebsite = officialWebsite; }
        public String getPortalUrl() { return portalUrl; }
        public void setPortalUrl(String portalUrl) { this.portalUrl = portalUrl; }
        public String getWorkingHours() { return workingHours; }
        public void setWorkingHours(String workingHours) { this.workingHours = workingHours; }
        public String getHelplineNumber() { return helplineNumber; }
        public void setHelplineNumber(String helplineNumber) { this.helplineNumber = helplineNumber; }
        public String getCategoryName() { return categoryName; }
        public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public List<ServiceDto> getServices() { return services; }
        public void setServices(List<ServiceDto> services) { this.services = services; }
    }"""

with open(f"{BASE}/dto/LegalDto.java", "r") as f:
    leg = f.read()

leg = re.sub(r"public static class AuthorityDto \{.*?\n    \}", auth_dto_patch, leg, flags=re.DOTALL)
with open(f"{BASE}/dto/LegalDto.java", "w") as f:
    f.write(leg)
print("LegalDto AuthorityDto patched.")
