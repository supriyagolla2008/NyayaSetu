package com.rightsflow.dto;

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
