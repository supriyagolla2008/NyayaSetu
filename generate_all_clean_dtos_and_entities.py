import os
import re

BASE = "/Users/gollasupriyashri/.gemini/antigravity/scratch/rightsflow/backend/src/main/java/com/rightsflow"

# ==============================================================================
# 1. ProblemDto.java
# ==============================================================================
problem_dto = """package com.rightsflow.dto;

import java.util.List;

public class ProblemDto {

    public static class ProblemAnalysisRequest {
        private String problemText;
        private String language; // en, hi, te
        private String categoryHint;
        private Boolean isVoice;

        public ProblemAnalysisRequest() {}
        public ProblemAnalysisRequest(String problemText, String language, String categoryHint, Boolean isVoice) {
            this.problemText = problemText;
            this.language = language;
            this.categoryHint = categoryHint;
            this.isVoice = isVoice;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String problemText;
            private String language;
            private String categoryHint;
            private Boolean isVoice;

            public Builder problemText(String problemText) { this.problemText = problemText; return this; }
            public Builder language(String language) { this.language = language; return this; }
            public Builder categoryHint(String categoryHint) { this.categoryHint = categoryHint; return this; }
            public Builder isVoice(Boolean isVoice) { this.isVoice = isVoice; return this; }
            public ProblemAnalysisRequest build() {
                return new ProblemAnalysisRequest(problemText, language, categoryHint, isVoice);
            }
        }

        public String getProblemText() { return problemText; }
        public void setProblemText(String problemText) { this.problemText = problemText; }
        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
        public String getCategoryHint() { return categoryHint; }
        public void setCategoryHint(String categoryHint) { this.categoryHint = categoryHint; }
        public Boolean getIsVoice() { return isVoice; }
        public void setIsVoice(Boolean isVoice) { this.isVoice = isVoice; }
    }

    public static class AIAnalysisResponse {
        private String summary;
        private String detectedLanguage;
        private String classifiedCategory;
        private Long relevantRightId;
        private String relevantRightTitle;
        private String relevantRightSlug;
        private String applicableLaw;
        private String applicableSection;
        private String simpleExplanation;
        private String realWorldExample;
        private List<String> whatYouShouldDoNow;
        private List<String> documentChecklist;
        private List<String> actionChecklist;
        private String relevantAuthority;
        private String relevantService;
        private String officialSourceUrl;
        private String sourceAuthority;
        private String lastVerified;
        private Double confidenceScore;
        private Boolean isDemoMode;
        private String voiceAudioBase64;

        public AIAnalysisResponse() {}
        public AIAnalysisResponse(String summary, String detectedLanguage, String classifiedCategory, Long relevantRightId, String relevantRightTitle, String relevantRightSlug, String applicableLaw, String applicableSection, String simpleExplanation, String realWorldExample, List<String> whatYouShouldDoNow, List<String> documentChecklist, List<String> actionChecklist, String relevantAuthority, String relevantService, String officialSourceUrl, String sourceAuthority, String lastVerified, Double confidenceScore, Boolean isDemoMode, String voiceAudioBase64) {
            this.summary = summary;
            this.detectedLanguage = detectedLanguage;
            this.classifiedCategory = classifiedCategory;
            this.relevantRightId = relevantRightId;
            this.relevantRightTitle = relevantRightTitle;
            this.relevantRightSlug = relevantRightSlug;
            this.applicableLaw = applicableLaw;
            this.applicableSection = applicableSection;
            this.simpleExplanation = simpleExplanation;
            this.realWorldExample = realWorldExample;
            this.whatYouShouldDoNow = whatYouShouldDoNow;
            this.documentChecklist = documentChecklist;
            this.actionChecklist = actionChecklist;
            this.relevantAuthority = relevantAuthority;
            this.relevantService = relevantService;
            this.officialSourceUrl = officialSourceUrl;
            this.sourceAuthority = sourceAuthority;
            this.lastVerified = lastVerified;
            this.confidenceScore = confidenceScore;
            this.isDemoMode = isDemoMode;
            this.voiceAudioBase64 = voiceAudioBase64;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String summary;
            private String detectedLanguage;
            private String classifiedCategory;
            private Long relevantRightId;
            private String relevantRightTitle;
            private String relevantRightSlug;
            private String applicableLaw;
            private String applicableSection;
            private String simpleExplanation;
            private String realWorldExample;
            private List<String> whatYouShouldDoNow;
            private List<String> documentChecklist;
            private List<String> actionChecklist;
            private String relevantAuthority;
            private String relevantService;
            private String officialSourceUrl;
            private String sourceAuthority;
            private String lastVerified;
            private Double confidenceScore;
            private Boolean isDemoMode;
            private String voiceAudioBase64;

            public Builder summary(String summary) { this.summary = summary; return this; }
            public Builder detectedLanguage(String detectedLanguage) { this.detectedLanguage = detectedLanguage; return this; }
            public Builder classifiedCategory(String classifiedCategory) { this.classifiedCategory = classifiedCategory; return this; }
            public Builder relevantRightId(Long relevantRightId) { this.relevantRightId = relevantRightId; return this; }
            public Builder relevantRightTitle(String relevantRightTitle) { this.relevantRightTitle = relevantRightTitle; return this; }
            public Builder relevantRightSlug(String relevantRightSlug) { this.relevantRightSlug = relevantRightSlug; return this; }
            public Builder applicableLaw(String applicableLaw) { this.applicableLaw = applicableLaw; return this; }
            public Builder applicableSection(String applicableSection) { this.applicableSection = applicableSection; return this; }
            public Builder simpleExplanation(String simpleExplanation) { this.simpleExplanation = simpleExplanation; return this; }
            public Builder realWorldExample(String realWorldExample) { this.realWorldExample = realWorldExample; return this; }
            public Builder whatYouShouldDoNow(List<String> whatYouShouldDoNow) { this.whatYouShouldDoNow = whatYouShouldDoNow; return this; }
            public Builder documentChecklist(List<String> documentChecklist) { this.documentChecklist = documentChecklist; return this; }
            public Builder actionChecklist(List<String> actionChecklist) { this.actionChecklist = actionChecklist; return this; }
            public Builder relevantAuthority(String relevantAuthority) { this.relevantAuthority = relevantAuthority; return this; }
            public Builder relevantService(String relevantService) { this.relevantService = relevantService; return this; }
            public Builder officialSourceUrl(String officialSourceUrl) { this.officialSourceUrl = officialSourceUrl; return this; }
            public Builder sourceAuthority(String sourceAuthority) { this.sourceAuthority = sourceAuthority; return this; }
            public Builder lastVerified(String lastVerified) { this.lastVerified = lastVerified; return this; }
            public Builder confidenceScore(Double confidenceScore) { this.confidenceScore = confidenceScore; return this; }
            public Builder isDemoMode(Boolean isDemoMode) { this.isDemoMode = isDemoMode; return this; }
            public Builder voiceAudioBase64(String voiceAudioBase64) { this.voiceAudioBase64 = voiceAudioBase64; return this; }

            public AIAnalysisResponse build() {
                return new AIAnalysisResponse(summary, detectedLanguage, classifiedCategory, relevantRightId, relevantRightTitle, relevantRightSlug, applicableLaw, applicableSection, simpleExplanation, realWorldExample, whatYouShouldDoNow, documentChecklist, actionChecklist, relevantAuthority, relevantService, officialSourceUrl, sourceAuthority, lastVerified, confidenceScore, isDemoMode, voiceAudioBase64);
            }
        }

        public String getSummary() { return summary; }
        public void setSummary(String summary) { this.summary = summary; }
        public String getDetectedLanguage() { return detectedLanguage; }
        public void setDetectedLanguage(String detectedLanguage) { this.detectedLanguage = detectedLanguage; }
        public String getClassifiedCategory() { return classifiedCategory; }
        public void setClassifiedCategory(String classifiedCategory) { this.classifiedCategory = classifiedCategory; }
        public Long getRelevantRightId() { return relevantRightId; }
        public void setRelevantRightId(Long relevantRightId) { this.relevantRightId = relevantRightId; }
        public String getRelevantRightTitle() { return relevantRightTitle; }
        public void setRelevantRightTitle(String relevantRightTitle) { this.relevantRightTitle = relevantRightTitle; }
        public String getRelevantRightSlug() { return relevantRightSlug; }
        public void setRelevantRightSlug(String relevantRightSlug) { this.relevantRightSlug = relevantRightSlug; }
        public String getApplicableLaw() { return applicableLaw; }
        public void setApplicableLaw(String applicableLaw) { this.applicableLaw = applicableLaw; }
        public String getApplicableSection() { return applicableSection; }
        public void setApplicableSection(String applicableSection) { this.applicableSection = applicableSection; }
        public String getSimpleExplanation() { return simpleExplanation; }
        public void setSimpleExplanation(String simpleExplanation) { this.simpleExplanation = simpleExplanation; }
        public String getRealWorldExample() { return realWorldExample; }
        public void setRealWorldExample(String realWorldExample) { this.realWorldExample = realWorldExample; }
        public List<String> getWhatYouShouldDoNow() { return whatYouShouldDoNow; }
        public void setWhatYouShouldDoNow(List<String> whatYouShouldDoNow) { this.whatYouShouldDoNow = whatYouShouldDoNow; }
        public List<String> getDocumentChecklist() { return documentChecklist; }
        public void setDocumentChecklist(List<String> documentChecklist) { this.documentChecklist = documentChecklist; }
        public List<String> getActionChecklist() { return actionChecklist; }
        public void setActionChecklist(List<String> actionChecklist) { this.actionChecklist = actionChecklist; }
        public String getRelevantAuthority() { return relevantAuthority; }
        public void setRelevantAuthority(String relevantAuthority) { this.relevantAuthority = relevantAuthority; }
        public String getRelevantService() { return relevantService; }
        public void setRelevantService(String relevantService) { this.relevantService = relevantService; }
        public String getOfficialSourceUrl() { return officialSourceUrl; }
        public void setOfficialSourceUrl(String officialSourceUrl) { this.officialSourceUrl = officialSourceUrl; }
        public String getSourceAuthority() { return sourceAuthority; }
        public void setSourceAuthority(String sourceAuthority) { this.sourceAuthority = sourceAuthority; }
        public String getLastVerified() { return lastVerified; }
        public void setLastVerified(String lastVerified) { this.lastVerified = lastVerified; }
        public Double getConfidenceScore() { return confidenceScore; }
        public void setConfidenceScore(Double confidenceScore) { this.confidenceScore = confidenceScore; }
        public Boolean getIsDemoMode() { return isDemoMode; }
        public void setIsDemoMode(Boolean isDemoMode) { this.isDemoMode = isDemoMode; }
        public String getVoiceAudioBase64() { return voiceAudioBase64; }
        public void setVoiceAudioBase64(String voiceAudioBase64) { this.voiceAudioBase64 = voiceAudioBase64; }
    }

    public static class VoiceInputRequest {
        private String audioBase64;
        private String audioFormat; // wav, mp3, webm
        private String languageHint; // en, hi, te

        public VoiceInputRequest() {}
        public VoiceInputRequest(String audioBase64, String audioFormat, String languageHint) {
            this.audioBase64 = audioBase64;
            this.audioFormat = audioFormat;
            this.languageHint = languageHint;
        }

        public String getAudioBase64() { return audioBase64; }
        public void setAudioBase64(String audioBase64) { this.audioBase64 = audioBase64; }
        public String getAudioFormat() { return audioFormat; }
        public void setAudioFormat(String audioFormat) { this.audioFormat = audioFormat; }
        public String getLanguageHint() { return languageHint; }
        public void setLanguageHint(String languageHint) { this.languageHint = languageHint; }
    }

    public static class VoiceTranscriptionResponse {
        private String transcribedText;
        private String detectedLanguage;
        private Double confidence;

        public VoiceTranscriptionResponse() {}
        public VoiceTranscriptionResponse(String transcribedText, String detectedLanguage, Double confidence) {
            this.transcribedText = transcribedText;
            this.detectedLanguage = detectedLanguage;
            this.confidence = confidence;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String transcribedText;
            private String detectedLanguage;
            private Double confidence;

            public Builder transcribedText(String transcribedText) { this.transcribedText = transcribedText; return this; }
            public Builder detectedLanguage(String detectedLanguage) { this.detectedLanguage = detectedLanguage; return this; }
            public Builder confidence(Double confidence) { this.confidence = confidence; return this; }
            public VoiceTranscriptionResponse build() {
                return new VoiceTranscriptionResponse(transcribedText, detectedLanguage, confidence);
            }
        }

        public String getTranscribedText() { return transcribedText; }
        public void setTranscribedText(String transcribedText) { this.transcribedText = transcribedText; }
        public String getDetectedLanguage() { return detectedLanguage; }
        public void setDetectedLanguage(String detectedLanguage) { this.detectedLanguage = detectedLanguage; }
        public Double getConfidence() { return confidence; }
        public void setConfidence(Double confidence) { this.confidence = confidence; }
    }

    public static class TextToSpeechRequest {
        private String text;
        private String language; // en, hi, te

        public TextToSpeechRequest() {}
        public TextToSpeechRequest(String text, String language) {
            this.text = text;
            this.language = language;
        }

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
    }

    public static class TextToSpeechResponse {
        private String audioBase64;
        private String audioFormat;

        public TextToSpeechResponse() {}
        public TextToSpeechResponse(String audioBase64, String audioFormat) {
            this.audioBase64 = audioBase64;
            this.audioFormat = audioFormat;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String audioBase64;
            private String audioFormat;

            public Builder audioBase64(String audioBase64) { this.audioBase64 = audioBase64; return this; }
            public Builder audioFormat(String audioFormat) { this.audioFormat = audioFormat; return this; }
            public TextToSpeechResponse build() {
                return new TextToSpeechResponse(audioBase64, audioFormat);
            }
        }

        public String getAudioBase64() { return audioBase64; }
        public void setAudioBase64(String audioBase64) { this.audioBase64 = audioBase64; }
        public String getAudioFormat() { return audioFormat; }
        public void setAudioFormat(String audioFormat) { this.audioFormat = audioFormat; }
    }
}
"""

with open(f"{BASE}/dto/ProblemDto.java", "w") as f:
    f.write(problem_dto)
print("ProblemDto.java updated.")

# ==============================================================================
# 2. CaseDto.java
# ==============================================================================
case_dto = """package com.rightsflow.dto;

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
        private String fileName;
        private String fileType;
        private Long fileSize;
        private String documentType;
        private LocalDateTime uploadedAt;

        public CaseDocumentItemDto() {}
        public CaseDocumentItemDto(Long id, String fileName, String fileType, Long fileSize, String documentType, LocalDateTime uploadedAt) {
            this.id = id;
            this.fileName = fileName;
            this.fileType = fileType;
            this.fileSize = fileSize;
            this.documentType = documentType;
            this.uploadedAt = uploadedAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String fileName;
            private String fileType;
            private Long fileSize;
            private String documentType;
            private LocalDateTime uploadedAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder fileName(String fileName) { this.fileName = fileName; return this; }
            public Builder fileType(String fileType) { this.fileType = fileType; return this; }
            public Builder fileSize(Long fileSize) { this.fileSize = fileSize; return this; }
            public Builder documentType(String documentType) { this.documentType = documentType; return this; }
            public Builder uploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; return this; }
            public CaseDocumentItemDto build() {
                return new CaseDocumentItemDto(id, fileName, fileType, fileSize, documentType, uploadedAt);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getFileName() { return fileName; }
        public void setFileName(String fileName) { this.fileName = fileName; }
        public String getFileType() { return fileType; }
        public void setFileType(String fileType) { this.fileType = fileType; }
        public Long getFileSize() { return fileSize; }
        public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
        public String getDocumentType() { return documentType; }
        public void setDocumentType(String documentType) { this.documentType = documentType; }
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
"""

with open(f"{BASE}/dto/CaseDto.java", "w") as f:
    f.write(case_dto)
print("CaseDto.java updated.")

# ==============================================================================
# 3. AnalyticsDto.java
# ==============================================================================
analytics_dto = """package com.rightsflow.dto;

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
            public Builder userId(Long userId) { this.userId = userId; return this; }
            public Builder userName(String userName) { this.userName = userName; return this; }
            public Builder userRole(String userRole) { this.userRole = userRole; return this; }
            public Builder action(String action) { this.action = action; return this; }
            public Builder entityType(String entityType) { this.entityType = entityType; return this; }
            public Builder entityId(Long entityId) { this.entityId = entityId; return this; }
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
}
"""

with open(f"{BASE}/dto/AnalyticsDto.java", "w") as f:
    f.write(analytics_dto)
print("AnalyticsDto.java updated.")

# ==============================================================================
# 4. UpdateAndSearchDto.java
# ==============================================================================
update_search_dto = """package com.rightsflow.dto;

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
            public Builder read(Boolean read) { this.read = read; return this; }
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
"""

with open(f"{BASE}/dto/UpdateAndSearchDto.java", "w") as f:
    f.write(update_search_dto)
print("UpdateAndSearchDto.java updated.")

# ==============================================================================
# 5. LegalDto.java
# ==============================================================================
legal_dto = """package com.rightsflow.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LegalDto {

    public static class CategoryDto {
        private Long id;
        private String name;
        private String slug;
        private String description;
        private String iconName;
        private String colorTheme;
        private Integer displayOrder;
        private Long rightsCount;

        public CategoryDto() {}
        public CategoryDto(Long id, String name, String slug, String description, String iconName, String colorTheme, Integer displayOrder, Long rightsCount) {
            this.id = id;
            this.name = name;
            this.slug = slug;
            this.description = description;
            this.iconName = iconName;
            this.colorTheme = colorTheme;
            this.displayOrder = displayOrder;
            this.rightsCount = rightsCount;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String name;
            private String slug;
            private String description;
            private String iconName;
            private String colorTheme;
            private Integer displayOrder;
            private Long rightsCount;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder name(String name) { this.name = name; return this; }
            public Builder slug(String slug) { this.slug = slug; return this; }
            public Builder description(String description) { this.description = description; return this; }
            public Builder iconName(String iconName) { this.iconName = iconName; return this; }
            public Builder colorTheme(String colorTheme) { this.colorTheme = colorTheme; return this; }
            public Builder displayOrder(Integer displayOrder) { this.displayOrder = displayOrder; return this; }
            public Builder rightsCount(Long rightsCount) { this.rightsCount = rightsCount; return this; }

            public CategoryDto build() {
                return new CategoryDto(id, name, slug, description, iconName, colorTheme, displayOrder, rightsCount);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getSlug() { return slug; }
        public void setSlug(String slug) { this.slug = slug; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getIconName() { return iconName; }
        public void setIconName(String iconName) { this.iconName = iconName; }
        public String getColorTheme() { return colorTheme; }
        public void setColorTheme(String colorTheme) { this.colorTheme = colorTheme; }
        public Integer getDisplayOrder() { return displayOrder; }
        public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
        public Long getRightsCount() { return rightsCount; }
        public void setRightsCount(Long rightsCount) { this.rightsCount = rightsCount; }
    }

    public static class LawDto {
        private Long id;
        private String title;
        private String shortCode;
        private Integer year;
        private LocalDate enactmentDate;
        private String jurisdiction;
        private String description;
        private Long sourceId;
        private String sourceName;
        private String officialLink;
        private String status;
        private List<ProvisionDto> provisions;

        public LawDto() {}
        public LawDto(Long id, String title, String shortCode, Integer year, LocalDate enactmentDate, String jurisdiction, String description, Long sourceId, String sourceName, String officialLink, String status, List<ProvisionDto> provisions) {
            this.id = id;
            this.title = title;
            this.shortCode = shortCode;
            this.year = year;
            this.enactmentDate = enactmentDate;
            this.jurisdiction = jurisdiction;
            this.description = description;
            this.sourceId = sourceId;
            this.sourceName = sourceName;
            this.officialLink = officialLink;
            this.status = status;
            this.provisions = provisions;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String title;
            private String shortCode;
            private Integer year;
            private LocalDate enactmentDate;
            private String jurisdiction;
            private String description;
            private Long sourceId;
            private String sourceName;
            private String officialLink;
            private String status;
            private List<ProvisionDto> provisions;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder shortCode(String shortCode) { this.shortCode = shortCode; return this; }
            public Builder year(Integer year) { this.year = year; return this; }
            public Builder enactmentDate(LocalDate enactmentDate) { this.enactmentDate = enactmentDate; return this; }
            public Builder jurisdiction(String jurisdiction) { this.jurisdiction = jurisdiction; return this; }
            public Builder description(String description) { this.description = description; return this; }
            public Builder sourceId(Long sourceId) { this.sourceId = sourceId; return this; }
            public Builder sourceName(String sourceName) { this.sourceName = sourceName; return this; }
            public Builder officialLink(String officialLink) { this.officialLink = officialLink; return this; }
            public Builder status(String status) { this.status = status; return this; }
            public Builder provisions(List<ProvisionDto> provisions) { this.provisions = provisions; return this; }

            public LawDto build() {
                return new LawDto(id, title, shortCode, year, enactmentDate, jurisdiction, description, sourceId, sourceName, officialLink, status, provisions);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getShortCode() { return shortCode; }
        public void setShortCode(String shortCode) { this.shortCode = shortCode; }
        public Integer getYear() { return year; }
        public void setYear(Integer year) { this.year = year; }
        public LocalDate getEnactmentDate() { return enactmentDate; }
        public void setEnactmentDate(LocalDate enactmentDate) { this.enactmentDate = enactmentDate; }
        public String getJurisdiction() { return jurisdiction; }
        public void setJurisdiction(String jurisdiction) { this.jurisdiction = jurisdiction; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Long getSourceId() { return sourceId; }
        public void setSourceId(Long sourceId) { this.sourceId = sourceId; }
        public String getSourceName() { return sourceName; }
        public void setSourceName(String sourceName) { this.sourceName = sourceName; }
        public String getOfficialLink() { return officialLink; }
        public void setOfficialLink(String officialLink) { this.officialLink = officialLink; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public List<ProvisionDto> getProvisions() { return provisions; }
        public void setProvisions(List<ProvisionDto> provisions) { this.provisions = provisions; }
    }

    public static class ProvisionDto {
        private Long id;
        private Long lawId;
        private String lawTitle;
        private String sectionNumber;
        private String title;
        private String simpleSummary;
        private String fullText;
        private String penaltyOrRemedy;

        public ProvisionDto() {}
        public ProvisionDto(Long id, Long lawId, String lawTitle, String sectionNumber, String title, String simpleSummary, String fullText, String penaltyOrRemedy) {
            this.id = id;
            this.lawId = lawId;
            this.lawTitle = lawTitle;
            this.sectionNumber = sectionNumber;
            this.title = title;
            this.simpleSummary = simpleSummary;
            this.fullText = fullText;
            this.penaltyOrRemedy = penaltyOrRemedy;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Long lawId;
            private String lawTitle;
            private String sectionNumber;
            private String title;
            private String simpleSummary;
            private String fullText;
            private String penaltyOrRemedy;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder lawId(Long lawId) { this.lawId = lawId; return this; }
            public Builder lawTitle(String lawTitle) { this.lawTitle = lawTitle; return this; }
            public Builder sectionNumber(String sectionNumber) { this.sectionNumber = sectionNumber; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder simpleSummary(String simpleSummary) { this.simpleSummary = simpleSummary; return this; }
            public Builder fullText(String fullText) { this.fullText = fullText; return this; }
            public Builder penaltyOrRemedy(String penaltyOrRemedy) { this.penaltyOrRemedy = penaltyOrRemedy; return this; }

            public ProvisionDto build() {
                return new ProvisionDto(id, lawId, lawTitle, sectionNumber, title, simpleSummary, fullText, penaltyOrRemedy);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getLawId() { return lawId; }
        public void setLawId(Long lawId) { this.lawId = lawId; }
        public String getLawTitle() { return lawTitle; }
        public void setLawTitle(String lawTitle) { this.lawTitle = lawTitle; }
        public String getSectionNumber() { return sectionNumber; }
        public void setSectionNumber(String sectionNumber) { this.sectionNumber = sectionNumber; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSimpleSummary() { return simpleSummary; }
        public void setSimpleSummary(String simpleSummary) { this.simpleSummary = simpleSummary; }
        public String getFullText() { return fullText; }
        public void setFullText(String fullText) { this.fullText = fullText; }
        public String getPenaltyOrRemedy() { return penaltyOrRemedy; }
        public void setPenaltyOrRemedy(String penaltyOrRemedy) { this.penaltyOrRemedy = penaltyOrRemedy; }
    }

    public static class RightSummaryDto {
        private Long id;
        private String title;
        private String slug;
        private Long categoryId;
        private String categoryName;
        private String colorTheme;
        private String shortDescription;
        private String simpleExplanation;
        private String applicableLaw;
        private String relevantProvision;
        private String relevantAuthority;
        private String relevantService;
        private String verificationStatus;
        private String contentStatus;
        private Long viewCount;
        private LocalDate lastVerified;

        public RightSummaryDto() {}
        public RightSummaryDto(Long id, String title, String slug, Long categoryId, String categoryName, String colorTheme, String shortDescription, String simpleExplanation, String applicableLaw, String relevantProvision, String relevantAuthority, String relevantService, String verificationStatus, String contentStatus, Long viewCount, LocalDate lastVerified) {
            this.id = id;
            this.title = title;
            this.slug = slug;
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.colorTheme = colorTheme;
            this.shortDescription = shortDescription;
            this.simpleExplanation = simpleExplanation;
            this.applicableLaw = applicableLaw;
            this.relevantProvision = relevantProvision;
            this.relevantAuthority = relevantAuthority;
            this.relevantService = relevantService;
            this.verificationStatus = verificationStatus;
            this.contentStatus = contentStatus;
            this.viewCount = viewCount;
            this.lastVerified = lastVerified;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String title;
            private String slug;
            private Long categoryId;
            private String categoryName;
            private String colorTheme;
            private String shortDescription;
            private String simpleExplanation;
            private String applicableLaw;
            private String relevantProvision;
            private String relevantAuthority;
            private String relevantService;
            private String verificationStatus;
            private String contentStatus;
            private Long viewCount;
            private LocalDate lastVerified;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder slug(String slug) { this.slug = slug; return this; }
            public Builder categoryId(Long categoryId) { this.categoryId = categoryId; return this; }
            public Builder categoryName(String categoryName) { this.categoryName = categoryName; return this; }
            public Builder colorTheme(String colorTheme) { this.colorTheme = colorTheme; return this; }
            public Builder shortDescription(String shortDescription) { this.shortDescription = shortDescription; return this; }
            public Builder simpleExplanation(String simpleExplanation) { this.simpleExplanation = simpleExplanation; return this; }
            public Builder applicableLaw(String applicableLaw) { this.applicableLaw = applicableLaw; return this; }
            public Builder relevantProvision(String relevantProvision) { this.relevantProvision = relevantProvision; return this; }
            public Builder relevantAuthority(String relevantAuthority) { this.relevantAuthority = relevantAuthority; return this; }
            public Builder relevantService(String relevantService) { this.relevantService = relevantService; return this; }
            public Builder verificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; return this; }
            public Builder contentStatus(String contentStatus) { this.contentStatus = contentStatus; return this; }
            public Builder viewCount(Long viewCount) { this.viewCount = viewCount; return this; }
            public Builder lastVerified(LocalDate lastVerified) { this.lastVerified = lastVerified; return this; }

            public RightSummaryDto build() {
                return new RightSummaryDto(id, title, slug, categoryId, categoryName, colorTheme, shortDescription, simpleExplanation, applicableLaw, relevantProvision, relevantAuthority, relevantService, verificationStatus, contentStatus, viewCount, lastVerified);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSlug() { return slug; }
        public void setSlug(String slug) { this.slug = slug; }
        public Long getCategoryId() { return categoryId; }
        public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
        public String getCategoryName() { return categoryName; }
        public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
        public String getColorTheme() { return colorTheme; }
        public void setColorTheme(String colorTheme) { this.colorTheme = colorTheme; }
        public String getShortDescription() { return shortDescription; }
        public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
        public String getSimpleExplanation() { return simpleExplanation; }
        public void setSimpleExplanation(String simpleExplanation) { this.simpleExplanation = simpleExplanation; }
        public String getApplicableLaw() { return applicableLaw; }
        public void setApplicableLaw(String applicableLaw) { this.applicableLaw = applicableLaw; }
        public String getRelevantProvision() { return relevantProvision; }
        public void setRelevantProvision(String relevantProvision) { this.relevantProvision = relevantProvision; }
        public String getRelevantAuthority() { return relevantAuthority; }
        public void setRelevantAuthority(String relevantAuthority) { this.relevantAuthority = relevantAuthority; }
        public String getRelevantService() { return relevantService; }
        public void setRelevantService(String relevantService) { this.relevantService = relevantService; }
        public String getVerificationStatus() { return verificationStatus; }
        public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }
        public String getContentStatus() { return contentStatus; }
        public void setContentStatus(String contentStatus) { this.contentStatus = contentStatus; }
        public Long getViewCount() { return viewCount; }
        public void setViewCount(Long viewCount) { this.viewCount = viewCount; }
        public LocalDate getLastVerified() { return lastVerified; }
        public void setLastVerified(LocalDate lastVerified) { this.lastVerified = lastVerified; }
    }

    public static class RightDetailDto {
        private Long id;
        private Long categoryId;
        private String categoryName;
        private String categoryColor;
        private String title;
        private String slug;
        private String shortDescription;
        private String simpleExplanation;
        private String detailedExplanation;
        private String whoItAppliesTo;
        private String whyItApplies;
        private String realWorldExample;
        private String recommendedActions;
        private String requiredDocuments;
        private String relevantAuthority;
        private String relevantService;
        private String procedureSummary;
        private String officialSourceUrl;
        private String sourceAuthority;
        private LocalDate lastVerified;
        private LocalDate effectiveFrom;
        private LocalDate effectiveTo;
        private Integer version;
        private String verificationStatus;
        private String contentStatus;
        private Long viewCount;
        private List<LawDto> laws;
        private List<ProvisionDto> provisions;
        private List<FaqDto> faqs;
        private List<ExampleDto> examples;
        private Boolean bookmarked;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public RightDetailDto() {}
        public RightDetailDto(Long id, Long categoryId, String categoryName, String categoryColor, String title, String slug, String shortDescription, String simpleExplanation, String detailedExplanation, String whoItAppliesTo, String whyItApplies, String realWorldExample, String recommendedActions, String requiredDocuments, String relevantAuthority, String relevantService, String procedureSummary, String officialSourceUrl, String sourceAuthority, LocalDate lastVerified, LocalDate effectiveFrom, LocalDate effectiveTo, Integer version, String verificationStatus, String contentStatus, Long viewCount, List<LawDto> laws, List<ProvisionDto> provisions, List<FaqDto> faqs, List<ExampleDto> examples, Boolean bookmarked, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.id = id;
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.categoryColor = categoryColor;
            this.title = title;
            this.slug = slug;
            this.shortDescription = shortDescription;
            this.simpleExplanation = simpleExplanation;
            this.detailedExplanation = detailedExplanation;
            this.whoItAppliesTo = whoItAppliesTo;
            this.whyItApplies = whyItApplies;
            this.realWorldExample = realWorldExample;
            this.recommendedActions = recommendedActions;
            this.requiredDocuments = requiredDocuments;
            this.relevantAuthority = relevantAuthority;
            this.relevantService = relevantService;
            this.procedureSummary = procedureSummary;
            this.officialSourceUrl = officialSourceUrl;
            this.sourceAuthority = sourceAuthority;
            this.lastVerified = lastVerified;
            this.effectiveFrom = effectiveFrom;
            this.effectiveTo = effectiveTo;
            this.version = version;
            this.verificationStatus = verificationStatus;
            this.contentStatus = contentStatus;
            this.viewCount = viewCount;
            this.laws = laws;
            this.provisions = provisions;
            this.faqs = faqs;
            this.examples = examples;
            this.bookmarked = bookmarked;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Long categoryId;
            private String categoryName;
            private String categoryColor;
            private String title;
            private String slug;
            private String shortDescription;
            private String simpleExplanation;
            private String detailedExplanation;
            private String whoItAppliesTo;
            private String whyItApplies;
            private String realWorldExample;
            private String recommendedActions;
            private String requiredDocuments;
            private String relevantAuthority;
            private String relevantService;
            private String procedureSummary;
            private String officialSourceUrl;
            private String sourceAuthority;
            private LocalDate lastVerified;
            private LocalDate effectiveFrom;
            private LocalDate effectiveTo;
            private Integer version;
            private String verificationStatus;
            private String contentStatus;
            private Long viewCount;
            private List<LawDto> laws;
            private List<ProvisionDto> provisions;
            private List<FaqDto> faqs;
            private List<ExampleDto> examples;
            private Boolean bookmarked;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder categoryId(Long categoryId) { this.categoryId = categoryId; return this; }
            public Builder categoryName(String categoryName) { this.categoryName = categoryName; return this; }
            public Builder categoryColor(String categoryColor) { this.categoryColor = categoryColor; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder slug(String slug) { this.slug = slug; return this; }
            public Builder shortDescription(String shortDescription) { this.shortDescription = shortDescription; return this; }
            public Builder simpleExplanation(String simpleExplanation) { this.simpleExplanation = simpleExplanation; return this; }
            public Builder detailedExplanation(String detailedExplanation) { this.detailedExplanation = detailedExplanation; return this; }
            public Builder whoItAppliesTo(String whoItAppliesTo) { this.whoItAppliesTo = whoItAppliesTo; return this; }
            public Builder whyItApplies(String whyItApplies) { this.whyItApplies = whyItApplies; return this; }
            public Builder realWorldExample(String realWorldExample) { this.realWorldExample = realWorldExample; return this; }
            public Builder recommendedActions(String recommendedActions) { this.recommendedActions = recommendedActions; return this; }
            public Builder requiredDocuments(String requiredDocuments) { this.requiredDocuments = requiredDocuments; return this; }
            public Builder relevantAuthority(String relevantAuthority) { this.relevantAuthority = relevantAuthority; return this; }
            public Builder relevantService(String relevantService) { this.relevantService = relevantService; return this; }
            public Builder procedureSummary(String procedureSummary) { this.procedureSummary = procedureSummary; return this; }
            public Builder officialSourceUrl(String officialSourceUrl) { this.officialSourceUrl = officialSourceUrl; return this; }
            public Builder sourceAuthority(String sourceAuthority) { this.sourceAuthority = sourceAuthority; return this; }
            public Builder lastVerified(LocalDate lastVerified) { this.lastVerified = lastVerified; return this; }
            public Builder effectiveFrom(LocalDate effectiveFrom) { this.effectiveFrom = effectiveFrom; return this; }
            public Builder effectiveTo(LocalDate effectiveTo) { this.effectiveTo = effectiveTo; return this; }
            public Builder version(Integer version) { this.version = version; return this; }
            public Builder verificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; return this; }
            public Builder contentStatus(String contentStatus) { this.contentStatus = contentStatus; return this; }
            public Builder viewCount(Long viewCount) { this.viewCount = viewCount; return this; }
            public Builder laws(List<LawDto> laws) { this.laws = laws; return this; }
            public Builder provisions(List<ProvisionDto> provisions) { this.provisions = provisions; return this; }
            public Builder faqs(List<FaqDto> faqs) { this.faqs = faqs; return this; }
            public Builder examples(List<ExampleDto> examples) { this.examples = examples; return this; }
            public Builder bookmarked(Boolean bookmarked) { this.bookmarked = bookmarked; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
            public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

            public RightDetailDto build() {
                return new RightDetailDto(id, categoryId, categoryName, categoryColor, title, slug, shortDescription, simpleExplanation, detailedExplanation, whoItAppliesTo, whyItApplies, realWorldExample, recommendedActions, requiredDocuments, relevantAuthority, relevantService, procedureSummary, officialSourceUrl, sourceAuthority, lastVerified, effectiveFrom, effectiveTo, version, verificationStatus, contentStatus, viewCount, laws, provisions, faqs, examples, bookmarked, createdAt, updatedAt);
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
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSlug() { return slug; }
        public void setSlug(String slug) { this.slug = slug; }
        public String getShortDescription() { return shortDescription; }
        public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
        public String getSimpleExplanation() { return simpleExplanation; }
        public void setSimpleExplanation(String simpleExplanation) { this.simpleExplanation = simpleExplanation; }
        public String getDetailedExplanation() { return detailedExplanation; }
        public void setDetailedExplanation(String detailedExplanation) { this.detailedExplanation = detailedExplanation; }
        public String getWhoItAppliesTo() { return whoItAppliesTo; }
        public void setWhoItAppliesTo(String whoItAppliesTo) { this.whoItAppliesTo = whoItAppliesTo; }
        public String getWhyItApplies() { return whyItApplies; }
        public void setWhyItApplies(String whyItApplies) { this.whyItApplies = whyItApplies; }
        public String getRealWorldExample() { return realWorldExample; }
        public void setRealWorldExample(String realWorldExample) { this.realWorldExample = realWorldExample; }
        public String getRecommendedActions() { return recommendedActions; }
        public void setRecommendedActions(String recommendedActions) { this.recommendedActions = recommendedActions; }
        public String getRequiredDocuments() { return requiredDocuments; }
        public void setRequiredDocuments(String requiredDocuments) { this.requiredDocuments = requiredDocuments; }
        public String getRelevantAuthority() { return relevantAuthority; }
        public void setRelevantAuthority(String relevantAuthority) { this.relevantAuthority = relevantAuthority; }
        public String getRelevantService() { return relevantService; }
        public void setRelevantService(String relevantService) { this.relevantService = relevantService; }
        public String getProcedureSummary() { return procedureSummary; }
        public void setProcedureSummary(String procedureSummary) { this.procedureSummary = procedureSummary; }
        public String getOfficialSourceUrl() { return officialSourceUrl; }
        public void setOfficialSourceUrl(String officialSourceUrl) { this.officialSourceUrl = officialSourceUrl; }
        public String getSourceAuthority() { return sourceAuthority; }
        public void setSourceAuthority(String sourceAuthority) { this.sourceAuthority = sourceAuthority; }
        public LocalDate getLastVerified() { return lastVerified; }
        public void setLastVerified(LocalDate lastVerified) { this.lastVerified = lastVerified; }
        public LocalDate getEffectiveFrom() { return effectiveFrom; }
        public void setEffectiveFrom(LocalDate effectiveFrom) { this.effectiveFrom = effectiveFrom; }
        public LocalDate getEffectiveTo() { return effectiveTo; }
        public void setEffectiveTo(LocalDate effectiveTo) { this.effectiveTo = effectiveTo; }
        public Integer getVersion() { return version; }
        public void setVersion(Integer version) { this.version = version; }
        public String getVerificationStatus() { return verificationStatus; }
        public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }
        public String getContentStatus() { return contentStatus; }
        public void setContentStatus(String contentStatus) { this.contentStatus = contentStatus; }
        public Long getViewCount() { return viewCount; }
        public void setViewCount(Long viewCount) { this.viewCount = viewCount; }
        public List<LawDto> getLaws() { return laws; }
        public void setLaws(List<LawDto> laws) { this.laws = laws; }
        public List<ProvisionDto> getProvisions() { return provisions; }
        public void setProvisions(List<ProvisionDto> provisions) { this.provisions = provisions; }
        public List<FaqDto> getFaqs() { return faqs; }
        public void setFaqs(List<FaqDto> faqs) { this.faqs = faqs; }
        public List<ExampleDto> getExamples() { return examples; }
        public void setExamples(List<ExampleDto> examples) { this.examples = examples; }
        public Boolean getBookmarked() { return bookmarked; }
        public void setBookmarked(Boolean bookmarked) { this.bookmarked = bookmarked; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        public LocalDateTime getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    }

    public static class RightCreateUpdateRequest {
        private Long categoryId;
        private String title;
        private String slug;
        private String shortDescription;
        private String simpleExplanation;
        private String detailedExplanation;
        private String whoItAppliesTo;
        private String whyItApplies;
        private String realWorldExample;
        private String recommendedActions;
        private String requiredDocuments;
        private String relevantAuthority;
        private String relevantService;
        private String procedureSummary;
        private String officialSourceUrl;
        private String sourceAuthority;
        private LocalDate effectiveFrom;
        private LocalDate effectiveTo;
        private String verificationStatus;
        private String contentStatus;
        private List<Long> lawIds;

        public RightCreateUpdateRequest() {}
        public RightCreateUpdateRequest(Long categoryId, String title, String slug, String shortDescription, String simpleExplanation, String detailedExplanation, String whoItAppliesTo, String whyItApplies, String realWorldExample, String recommendedActions, String requiredDocuments, String relevantAuthority, String relevantService, String procedureSummary, String officialSourceUrl, String sourceAuthority, LocalDate effectiveFrom, LocalDate effectiveTo, String verificationStatus, String contentStatus, List<Long> lawIds) {
            this.categoryId = categoryId;
            this.title = title;
            this.slug = slug;
            this.shortDescription = shortDescription;
            this.simpleExplanation = simpleExplanation;
            this.detailedExplanation = detailedExplanation;
            this.whoItAppliesTo = whoItAppliesTo;
            this.whyItApplies = whyItApplies;
            this.realWorldExample = realWorldExample;
            this.recommendedActions = recommendedActions;
            this.requiredDocuments = requiredDocuments;
            this.relevantAuthority = relevantAuthority;
            this.relevantService = relevantService;
            this.procedureSummary = procedureSummary;
            this.officialSourceUrl = officialSourceUrl;
            this.sourceAuthority = sourceAuthority;
            this.effectiveFrom = effectiveFrom;
            this.effectiveTo = effectiveTo;
            this.verificationStatus = verificationStatus;
            this.contentStatus = contentStatus;
            this.lawIds = lawIds;
        }

        public Long getCategoryId() { return categoryId; }
        public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSlug() { return slug; }
        public void setSlug(String slug) { this.slug = slug; }
        public String getShortDescription() { return shortDescription; }
        public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
        public String getSimpleExplanation() { return simpleExplanation; }
        public void setSimpleExplanation(String simpleExplanation) { this.simpleExplanation = simpleExplanation; }
        public String getDetailedExplanation() { return detailedExplanation; }
        public void setDetailedExplanation(String detailedExplanation) { this.detailedExplanation = detailedExplanation; }
        public String getWhoItAppliesTo() { return whoItAppliesTo; }
        public void setWhoItAppliesTo(String whoItAppliesTo) { this.whoItAppliesTo = whoItAppliesTo; }
        public String getWhyItApplies() { return whyItApplies; }
        public void setWhyItApplies(String whyItApplies) { this.whyItApplies = whyItApplies; }
        public String getRealWorldExample() { return realWorldExample; }
        public void setRealWorldExample(String realWorldExample) { this.realWorldExample = realWorldExample; }
        public String getRecommendedActions() { return recommendedActions; }
        public void setRecommendedActions(String recommendedActions) { this.recommendedActions = recommendedActions; }
        public String getRequiredDocuments() { return requiredDocuments; }
        public void setRequiredDocuments(String requiredDocuments) { this.requiredDocuments = requiredDocuments; }
        public String getRelevantAuthority() { return relevantAuthority; }
        public void setRelevantAuthority(String relevantAuthority) { this.relevantAuthority = relevantAuthority; }
        public String getRelevantService() { return relevantService; }
        public void setRelevantService(String relevantService) { this.relevantService = relevantService; }
        public String getProcedureSummary() { return procedureSummary; }
        public void setProcedureSummary(String procedureSummary) { this.procedureSummary = procedureSummary; }
        public String getOfficialSourceUrl() { return officialSourceUrl; }
        public void setOfficialSourceUrl(String officialSourceUrl) { this.officialSourceUrl = officialSourceUrl; }
        public String getSourceAuthority() { return sourceAuthority; }
        public void setSourceAuthority(String sourceAuthority) { this.sourceAuthority = sourceAuthority; }
        public LocalDate getEffectiveFrom() { return effectiveFrom; }
        public void setEffectiveFrom(LocalDate effectiveFrom) { this.effectiveFrom = effectiveFrom; }
        public LocalDate getEffectiveTo() { return effectiveTo; }
        public void setEffectiveTo(LocalDate effectiveTo) { this.effectiveTo = effectiveTo; }
        public String getVerificationStatus() { return verificationStatus; }
        public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }
        public String getContentStatus() { return contentStatus; }
        public void setContentStatus(String contentStatus) { this.contentStatus = contentStatus; }
        public List<Long> getLawIds() { return lawIds; }
        public void setLawIds(List<Long> lawIds) { this.lawIds = lawIds; }
    }

    public static class AuthorityDto {
        private Long id;
        private String name;
        private String type;
        private String jurisdictionLevel;
        private String jurisdictionArea;
        private String address;
        private String phone;
        private String email;
        private String website;
        private String portalUrl;
        private String workingHours;
        private String helplineNumber;
        private String categoryName;
        private String description;

        public AuthorityDto() {}
        public AuthorityDto(Long id, String name, String type, String jurisdictionLevel, String jurisdictionArea, String address, String phone, String email, String website, String portalUrl, String workingHours, String helplineNumber, String categoryName, String description) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.jurisdictionLevel = jurisdictionLevel;
            this.jurisdictionArea = jurisdictionArea;
            this.address = address;
            this.phone = phone;
            this.email = email;
            this.website = website;
            this.portalUrl = portalUrl;
            this.workingHours = workingHours;
            this.helplineNumber = helplineNumber;
            this.categoryName = categoryName;
            this.description = description;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String name;
            private String type;
            private String jurisdictionLevel;
            private String jurisdictionArea;
            private String address;
            private String phone;
            private String email;
            private String website;
            private String portalUrl;
            private String workingHours;
            private String helplineNumber;
            private String categoryName;
            private String description;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder name(String name) { this.name = name; return this; }
            public Builder type(String type) { this.type = type; return this; }
            public Builder jurisdictionLevel(String jurisdictionLevel) { this.jurisdictionLevel = jurisdictionLevel; return this; }
            public Builder jurisdictionArea(String jurisdictionArea) { this.jurisdictionArea = jurisdictionArea; return this; }
            public Builder address(String address) { this.address = address; return this; }
            public Builder phone(String phone) { this.phone = phone; return this; }
            public Builder email(String email) { this.email = email; return this; }
            public Builder website(String website) { this.website = website; return this; }
            public Builder portalUrl(String portalUrl) { this.portalUrl = portalUrl; return this; }
            public Builder workingHours(String workingHours) { this.workingHours = workingHours; return this; }
            public Builder helplineNumber(String helplineNumber) { this.helplineNumber = helplineNumber; return this; }
            public Builder categoryName(String categoryName) { this.categoryName = categoryName; return this; }
            public Builder description(String description) { this.description = description; return this; }

            public AuthorityDto build() {
                return new AuthorityDto(id, name, type, jurisdictionLevel, jurisdictionArea, address, phone, email, website, portalUrl, workingHours, helplineNumber, categoryName, description);
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
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getWebsite() { return website; }
        public void setWebsite(String website) { this.website = website; }
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
    }

    public static class LegalServiceDto {
        private Long id;
        private String name;
        private String description;
        private String serviceType;
        private String portalUrl;
        private String processingTime;
        private String feeDetails;
        private Boolean onlineAvailable;
        private String authorityName;
        private List<ServiceStepDto> steps;
        private List<ServiceDocumentDto> documents;

        public LegalServiceDto() {}
        public LegalServiceDto(Long id, String name, String description, String serviceType, String portalUrl, String processingTime, String feeDetails, Boolean onlineAvailable, String authorityName, List<ServiceStepDto> steps, List<ServiceDocumentDto> documents) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.serviceType = serviceType;
            this.portalUrl = portalUrl;
            this.processingTime = processingTime;
            this.feeDetails = feeDetails;
            this.onlineAvailable = onlineAvailable;
            this.authorityName = authorityName;
            this.steps = steps;
            this.documents = documents;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String name;
            private String description;
            private String serviceType;
            private String portalUrl;
            private String processingTime;
            private String feeDetails;
            private Boolean onlineAvailable;
            private String authorityName;
            private List<ServiceStepDto> steps;
            private List<ServiceDocumentDto> documents;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder name(String name) { this.name = name; return this; }
            public Builder description(String description) { this.description = description; return this; }
            public Builder serviceType(String serviceType) { this.serviceType = serviceType; return this; }
            public Builder portalUrl(String portalUrl) { this.portalUrl = portalUrl; return this; }
            public Builder processingTime(String processingTime) { this.processingTime = processingTime; return this; }
            public Builder feeDetails(String feeDetails) { this.feeDetails = feeDetails; return this; }
            public Builder onlineAvailable(Boolean onlineAvailable) { this.onlineAvailable = onlineAvailable; return this; }
            public Builder authorityName(String authorityName) { this.authorityName = authorityName; return this; }
            public Builder steps(List<ServiceStepDto> steps) { this.steps = steps; return this; }
            public Builder documents(List<ServiceDocumentDto> documents) { this.documents = documents; return this; }

            public LegalServiceDto build() {
                return new LegalServiceDto(id, name, description, serviceType, portalUrl, processingTime, feeDetails, onlineAvailable, authorityName, steps, documents);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getServiceType() { return serviceType; }
        public void setServiceType(String serviceType) { this.serviceType = serviceType; }
        public String getPortalUrl() { return portalUrl; }
        public void setPortalUrl(String portalUrl) { this.portalUrl = portalUrl; }
        public String getProcessingTime() { return processingTime; }
        public void setProcessingTime(String processingTime) { this.processingTime = processingTime; }
        public String getFeeDetails() { return feeDetails; }
        public void setFeeDetails(String feeDetails) { this.feeDetails = feeDetails; }
        public Boolean getOnlineAvailable() { return onlineAvailable; }
        public void setOnlineAvailable(Boolean onlineAvailable) { this.onlineAvailable = onlineAvailable; }
        public String getAuthorityName() { return authorityName; }
        public void setAuthorityName(String authorityName) { this.authorityName = authorityName; }
        public List<ServiceStepDto> getSteps() { return steps; }
        public void setSteps(List<ServiceStepDto> steps) { this.steps = steps; }
        public List<ServiceDocumentDto> getDocuments() { return documents; }
        public void setDocuments(List<ServiceDocumentDto> documents) { this.documents = documents; }
    }

    public static class ServiceStepDto {
        private Long id;
        private Integer stepNumber;
        private String title;
        private String description;
        private String timelineEstimate;
        private String tips;

        public ServiceStepDto() {}
        public ServiceStepDto(Long id, Integer stepNumber, String title, String description, String timelineEstimate, String tips) {
            this.id = id;
            this.stepNumber = stepNumber;
            this.title = title;
            this.description = description;
            this.timelineEstimate = timelineEstimate;
            this.tips = tips;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Integer stepNumber;
            private String title;
            private String description;
            private String timelineEstimate;
            private String tips;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder stepNumber(Integer stepNumber) { this.stepNumber = stepNumber; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder description(String description) { this.description = description; return this; }
            public Builder timelineEstimate(String timelineEstimate) { this.timelineEstimate = timelineEstimate; return this; }
            public Builder tips(String tips) { this.tips = tips; return this; }

            public ServiceStepDto build() {
                return new ServiceStepDto(id, stepNumber, title, description, timelineEstimate, tips);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Integer getStepNumber() { return stepNumber; }
        public void setStepNumber(Integer stepNumber) { this.stepNumber = stepNumber; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getTimelineEstimate() { return timelineEstimate; }
        public void setTimelineEstimate(String timelineEstimate) { this.timelineEstimate = timelineEstimate; }
        public String getTips() { return tips; }
        public void setTips(String tips) { this.tips = tips; }
    }

    public static class ServiceDocumentDto {
        private Long id;
        private String documentName;
        private String description;
        private Boolean mandatory;
        private String formatAllowed;

        public ServiceDocumentDto() {}
        public ServiceDocumentDto(Long id, String documentName, String description, Boolean mandatory, String formatAllowed) {
            this.id = id;
            this.documentName = documentName;
            this.description = description;
            this.mandatory = mandatory;
            this.formatAllowed = formatAllowed;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String documentName;
            private String description;
            private Boolean mandatory;
            private String formatAllowed;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder documentName(String documentName) { this.documentName = documentName; return this; }
            public Builder description(String description) { this.description = description; return this; }
            public Builder mandatory(Boolean mandatory) { this.mandatory = mandatory; return this; }
            public Builder formatAllowed(String formatAllowed) { this.formatAllowed = formatAllowed; return this; }

            public ServiceDocumentDto build() {
                return new ServiceDocumentDto(id, documentName, description, mandatory, formatAllowed);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getDocumentName() { return documentName; }
        public void setDocumentName(String documentName) { this.documentName = documentName; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Boolean getMandatory() { return mandatory; }
        public void setMandatory(Boolean mandatory) { this.mandatory = mandatory; }
        public String getFormatAllowed() { return formatAllowed; }
        public void setFormatAllowed(String formatAllowed) { this.formatAllowed = formatAllowed; }
    }

    public static class FaqDto {
        private Long id;
        private String question;
        private String answer;

        public FaqDto() {}
        public FaqDto(Long id, String question, String answer) {
            this.id = id;
            this.question = question;
            this.answer = answer;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String question;
            private String answer;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder question(String question) { this.question = question; return this; }
            public Builder answer(String answer) { this.answer = answer; return this; }

            public FaqDto build() {
                return new FaqDto(id, question, answer);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getQuestion() { return question; }
        public void setQuestion(String question) { this.question = question; }
        public String getAnswer() { return answer; }
        public void setAnswer(String answer) { this.answer = answer; }
    }

    public static class ExampleDto {
        private Long id;
        private String scenarioTitle;
        private String scenarioDescription;
        private String solutionOutcome;

        public ExampleDto() {}
        public ExampleDto(Long id, String scenarioTitle, String scenarioDescription, String solutionOutcome) {
            this.id = id;
            this.scenarioTitle = scenarioTitle;
            this.scenarioDescription = scenarioDescription;
            this.solutionOutcome = solutionOutcome;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String scenarioTitle;
            private String scenarioDescription;
            private String solutionOutcome;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder scenarioTitle(String scenarioTitle) { this.scenarioTitle = scenarioTitle; return this; }
            public Builder scenarioDescription(String scenarioDescription) { this.scenarioDescription = scenarioDescription; return this; }
            public Builder solutionOutcome(String solutionOutcome) { this.solutionOutcome = solutionOutcome; return this; }

            public ExampleDto build() {
                return new ExampleDto(id, scenarioTitle, scenarioDescription, solutionOutcome);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getScenarioTitle() { return scenarioTitle; }
        public void setScenarioTitle(String scenarioTitle) { this.scenarioTitle = scenarioTitle; }
        public String getScenarioDescription() { return scenarioDescription; }
        public void setScenarioDescription(String scenarioDescription) { this.scenarioDescription = scenarioDescription; }
        public String getSolutionOutcome() { return solutionOutcome; }
        public void setSolutionOutcome(String solutionOutcome) { this.solutionOutcome = solutionOutcome; }
    }
}
"""

with open(f"{BASE}/dto/LegalDto.java", "w") as f:
    f.write(legal_dto)
print("LegalDto.java updated.")

# ==============================================================================
# 6. Clean Entities (Faq, Example, Authority, LegalService, ServiceStep, ServiceDocument, Issue, NotificationPreference, UserPreference)
# ==============================================================================
faq_entity = """package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "faqs")
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id")
    private Right right;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "question", nullable = false, length = 500)
    private String question;

    @Column(name = "answer", nullable = false, columnDefinition = "TEXT")
    private String answer;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Faq() {}
    public Faq(Long id, Right right, Long categoryId, String question, String answer, LocalDateTime createdAt) {
        this.id = id;
        this.right = right;
        this.categoryId = categoryId;
        this.question = question;
        this.answer = answer;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private Right right;
        private Long categoryId;
        private String question;
        private String answer;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder right(Right right) { this.right = right; return this; }
        public Builder categoryId(Long categoryId) { this.categoryId = categoryId; return this; }
        public Builder question(String question) { this.question = question; return this; }
        public Builder answer(String answer) { this.answer = answer; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Faq build() { return new Faq(id, right, categoryId, question, answer, createdAt); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
"""

with open(f"{BASE}/entity/Faq.java", "w") as f:
    f.write(faq_entity)
print("Faq.java updated.")

example_entity = """package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "examples")
public class Example {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id", nullable = false)
    private Right right;

    @Column(name = "scenario_title", nullable = false, length = 255)
    private String scenarioTitle;

    @Column(name = "scenario_description", nullable = false, columnDefinition = "TEXT")
    private String scenarioDescription;

    @Column(name = "solution_outcome", nullable = false, columnDefinition = "TEXT")
    private String solutionOutcome;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Example() {}
    public Example(Long id, Right right, String scenarioTitle, String scenarioDescription, String solutionOutcome, LocalDateTime createdAt) {
        this.id = id;
        this.right = right;
        this.scenarioTitle = scenarioTitle;
        this.scenarioDescription = scenarioDescription;
        this.solutionOutcome = solutionOutcome;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private Right right;
        private String scenarioTitle;
        private String scenarioDescription;
        private String solutionOutcome;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder right(Right right) { this.right = right; return this; }
        public Builder scenarioTitle(String scenarioTitle) { this.scenarioTitle = scenarioTitle; return this; }
        public Builder scenarioDescription(String scenarioDescription) { this.scenarioDescription = scenarioDescription; return this; }
        public Builder solutionOutcome(String solutionOutcome) { this.solutionOutcome = solutionOutcome; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Example build() { return new Example(id, right, scenarioTitle, scenarioDescription, solutionOutcome, createdAt); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
    public String getScenarioTitle() { return scenarioTitle; }
    public void setScenarioTitle(String scenarioTitle) { this.scenarioTitle = scenarioTitle; }
    public String getScenarioDescription() { return scenarioDescription; }
    public void setScenarioDescription(String scenarioDescription) { this.scenarioDescription = scenarioDescription; }
    public String getSolutionOutcome() { return solutionOutcome; }
    public void setSolutionOutcome(String solutionOutcome) { this.solutionOutcome = solutionOutcome; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
"""

with open(f"{BASE}/entity/Example.java", "w") as f:
    f.write(example_entity)
print("Example.java updated.")

authority_entity = """package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Authority() {}
    public Authority(Long id, String name, String type, String jurisdictionLevel, String jurisdictionArea, String address, String phone, String email, String website, String portalUrl, String workingHours, String helplineNumber, String categoryName, String description, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.jurisdictionLevel = jurisdictionLevel;
        this.jurisdictionArea = jurisdictionArea;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.portalUrl = portalUrl;
        this.workingHours = workingHours;
        this.helplineNumber = helplineNumber;
        this.categoryName = categoryName;
        this.description = description;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private String name;
        private String type;
        private String jurisdictionLevel;
        private String jurisdictionArea;
        private String address;
        private String phone;
        private String email;
        private String website;
        private String portalUrl;
        private String workingHours;
        private String helplineNumber;
        private String categoryName;
        private String description;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder type(String type) { this.type = type; return this; }
        public Builder jurisdictionLevel(String jurisdictionLevel) { this.jurisdictionLevel = jurisdictionLevel; return this; }
        public Builder jurisdictionArea(String jurisdictionArea) { this.jurisdictionArea = jurisdictionArea; return this; }
        public Builder address(String address) { this.address = address; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder website(String website) { this.website = website; return this; }
        public Builder portalUrl(String portalUrl) { this.portalUrl = portalUrl; return this; }
        public Builder workingHours(String workingHours) { this.workingHours = workingHours; return this; }
        public Builder helplineNumber(String helplineNumber) { this.helplineNumber = helplineNumber; return this; }
        public Builder categoryName(String categoryName) { this.categoryName = categoryName; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Authority build() {
            return new Authority(id, name, type, jurisdictionLevel, jurisdictionArea, address, phone, email, website, portalUrl, workingHours, helplineNumber, categoryName, description, createdAt);
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
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
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
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
"""

with open(f"{BASE}/entity/Authority.java", "w") as f:
    f.write(authority_entity)
print("Authority.java updated.")

# ==============================================================================
# 7. More Clean Entities: LegalService, ServiceStep, ServiceDocument, Issue, NotificationPreference
# ==============================================================================
legal_service_entity = """package com.rightsflow.entity;

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
    public LegalService(Long id, Authority authority, LegalCategory category, String name, String description, String serviceType, String portalUrl, String processingTime, String feeDetails, Boolean onlineAvailable, List<ServiceStep> steps, List<ServiceDocument> documents, LocalDateTime createdAt) {
        this.id = id;
        this.authority = authority;
        this.category = category;
        this.name = name;
        this.description = description;
        this.serviceType = serviceType;
        this.portalUrl = portalUrl;
        this.processingTime = processingTime;
        this.feeDetails = feeDetails;
        this.onlineAvailable = onlineAvailable;
        this.steps = steps != null ? steps : new ArrayList<>();
        this.documents = documents != null ? documents : new ArrayList<>();
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private Authority authority;
        private LegalCategory category;
        private String name;
        private String description;
        private String serviceType;
        private String portalUrl;
        private String processingTime;
        private String feeDetails;
        private Boolean onlineAvailable = true;
        private List<ServiceStep> steps = new ArrayList<>();
        private List<ServiceDocument> documents = new ArrayList<>();
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder authority(Authority authority) { this.authority = authority; return this; }
        public Builder category(LegalCategory category) { this.category = category; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder serviceType(String serviceType) { this.serviceType = serviceType; return this; }
        public Builder portalUrl(String portalUrl) { this.portalUrl = portalUrl; return this; }
        public Builder processingTime(String processingTime) { this.processingTime = processingTime; return this; }
        public Builder feeDetails(String feeDetails) { this.feeDetails = feeDetails; return this; }
        public Builder onlineAvailable(Boolean onlineAvailable) { this.onlineAvailable = onlineAvailable; return this; }
        public Builder steps(List<ServiceStep> steps) { this.steps = steps; return this; }
        public Builder documents(List<ServiceDocument> documents) { this.documents = documents; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public LegalService build() {
            return new LegalService(id, authority, category, name, description, serviceType, portalUrl, processingTime, feeDetails, onlineAvailable, steps, documents, createdAt);
        }
    }

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
    public String getProcessingTime() { return processingTime; }
    public void setProcessingTime(String processingTime) { this.processingTime = processingTime; }
    public String getFeeDetails() { return feeDetails; }
    public void setFeeDetails(String feeDetails) { this.feeDetails = feeDetails; }
    public Boolean getOnlineAvailable() { return onlineAvailable; }
    public void setOnlineAvailable(Boolean onlineAvailable) { this.onlineAvailable = onlineAvailable; }
    public List<ServiceStep> getSteps() { return steps; }
    public void setSteps(List<ServiceStep> steps) { this.steps = steps; }
    public List<ServiceDocument> getDocuments() { return documents; }
    public void setDocuments(List<ServiceDocument> documents) { this.documents = documents; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
"""

with open(f"{BASE}/entity/LegalService.java", "w") as f:
    f.write(legal_service_entity)
print("LegalService.java updated.")

service_step_entity = """package com.rightsflow.entity;

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
    public ServiceStep(Long id, LegalService service, Integer stepNumber, String title, String description, String timelineEstimate, String tips) {
        this.id = id;
        this.service = service;
        this.stepNumber = stepNumber;
        this.title = title;
        this.description = description;
        this.timelineEstimate = timelineEstimate;
        this.tips = tips;
    }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private LegalService service;
        private Integer stepNumber;
        private String title;
        private String description;
        private String timelineEstimate;
        private String tips;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder service(LegalService service) { this.service = service; return this; }
        public Builder stepNumber(Integer stepNumber) { this.stepNumber = stepNumber; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder timelineEstimate(String timelineEstimate) { this.timelineEstimate = timelineEstimate; return this; }
        public Builder tips(String tips) { this.tips = tips; return this; }
        public ServiceStep build() {
            return new ServiceStep(id, service, stepNumber, title, description, timelineEstimate, tips);
        }
    }

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
    public String getTimelineEstimate() { return timelineEstimate; }
    public void setTimelineEstimate(String timelineEstimate) { this.timelineEstimate = timelineEstimate; }
    public String getTips() { return tips; }
    public void setTips(String tips) { this.tips = tips; }
}
"""

with open(f"{BASE}/entity/ServiceStep.java", "w") as f:
    f.write(service_step_entity)
print("ServiceStep.java updated.")

service_doc_entity = """package com.rightsflow.entity;

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
    public ServiceDocument(Long id, LegalService service, String documentName, String description, Boolean mandatory, String formatAllowed) {
        this.id = id;
        this.service = service;
        this.documentName = documentName;
        this.description = description;
        this.mandatory = mandatory;
        this.formatAllowed = formatAllowed;
    }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private LegalService service;
        private String documentName;
        private String description;
        private Boolean mandatory = true;
        private String formatAllowed;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder service(LegalService service) { this.service = service; return this; }
        public Builder documentName(String documentName) { this.documentName = documentName; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder mandatory(Boolean mandatory) { this.mandatory = mandatory; return this; }
        public Builder formatAllowed(String formatAllowed) { this.formatAllowed = formatAllowed; return this; }
        public ServiceDocument build() {
            return new ServiceDocument(id, service, documentName, description, mandatory, formatAllowed);
        }
    }

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
    public String getFormatAllowed() { return formatAllowed; }
    public void setFormatAllowed(String formatAllowed) { this.formatAllowed = formatAllowed; }
}
"""

with open(f"{BASE}/entity/ServiceDocument.java", "w") as f:
    f.write(service_doc_entity)
print("ServiceDocument.java updated.")

issue_entity = """package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private LegalCategory category;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, unique = true, length = 255)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "issue_rights",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "right_id")
    )
    private Set<Right> rights = new HashSet<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Issue() {}
    public Issue(Long id, LegalCategory category, String title, String slug, String description, Set<Right> rights, LocalDateTime createdAt) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.rights = rights != null ? rights : new HashSet<>();
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private LegalCategory category;
        private String title;
        private String slug;
        private String description;
        private Set<Right> rights = new HashSet<>();
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder category(LegalCategory category) { this.category = category; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder slug(String slug) { this.slug = slug; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder rights(Set<Right> rights) { this.rights = rights; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Issue build() {
            return new Issue(id, category, title, slug, description, rights, createdAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LegalCategory getCategory() { return category; }
    public void setCategory(LegalCategory category) { this.category = category; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Set<Right> getRights() { return rights; }
    public void setRights(Set<Right> rights) { this.rights = rights; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
"""

with open(f"{BASE}/entity/Issue.java", "w") as f:
    f.write(issue_entity)
print("Issue.java updated.")

notif_pref_entity = """package com.rightsflow.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_preferences")
public class NotificationPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "email_notifications")
    private Boolean emailNotifications = true;

    @Column(name = "case_status_updates")
    private Boolean caseStatusUpdates = true;

    @Column(name = "action_reminders")
    private Boolean actionReminders = true;

    @Column(name = "legal_alerts")
    private Boolean legalAlerts = true;

    @Column(name = "system_announcements")
    private Boolean systemAnnouncements = true;

    public NotificationPreference() {}
    public NotificationPreference(Long id, User user, Boolean emailNotifications, Boolean caseStatusUpdates, Boolean actionReminders, Boolean legalAlerts, Boolean systemAnnouncements) {
        this.id = id;
        this.user = user;
        this.emailNotifications = emailNotifications;
        this.caseStatusUpdates = caseStatusUpdates;
        this.actionReminders = actionReminders;
        this.legalAlerts = legalAlerts;
        this.systemAnnouncements = systemAnnouncements;
    }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private User user;
        private Boolean emailNotifications = true;
        private Boolean caseStatusUpdates = true;
        private Boolean actionReminders = true;
        private Boolean legalAlerts = true;
        private Boolean systemAnnouncements = true;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder emailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; return this; }
        public Builder caseStatusUpdates(Boolean caseStatusUpdates) { this.caseStatusUpdates = caseStatusUpdates; return this; }
        public Builder actionReminders(Boolean actionReminders) { this.actionReminders = actionReminders; return this; }
        public Builder legalAlerts(Boolean legalAlerts) { this.legalAlerts = legalAlerts; return this; }
        public Builder systemAnnouncements(Boolean systemAnnouncements) { this.systemAnnouncements = systemAnnouncements; return this; }
        public NotificationPreference build() {
            return new NotificationPreference(id, user, emailNotifications, caseStatusUpdates, actionReminders, legalAlerts, systemAnnouncements);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Boolean getEmailNotifications() { return emailNotifications; }
    public void setEmailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; }
    public Boolean getCaseStatusUpdates() { return caseStatusUpdates; }
    public void setCaseStatusUpdates(Boolean caseStatusUpdates) { this.caseStatusUpdates = caseStatusUpdates; }
    public Boolean getActionReminders() { return actionReminders; }
    public void setActionReminders(Boolean actionReminders) { this.actionReminders = actionReminders; }
    public Boolean getLegalAlerts() { return legalAlerts; }
    public void setLegalAlerts(Boolean legalAlerts) { this.legalAlerts = legalAlerts; }
    public Boolean getSystemAnnouncements() { return systemAnnouncements; }
    public void setSystemAnnouncements(Boolean systemAnnouncements) { this.systemAnnouncements = systemAnnouncements; }
}
"""

with open(f"{BASE}/entity/NotificationPreference.java", "w") as f:
    f.write(notif_pref_entity)
print("NotificationPreference.java updated.")
