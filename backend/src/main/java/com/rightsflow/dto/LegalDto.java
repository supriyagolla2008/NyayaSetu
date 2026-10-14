package com.rightsflow.dto;

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
            public Builder recommendedActions(List<String> list) { this.recommendedActions = list != null ? String.join(" | ", list) : null; return this; }
            public Builder requiredDocuments(String requiredDocuments) { this.requiredDocuments = requiredDocuments; return this; }
            public Builder requiredDocuments(List<String> list) { this.requiredDocuments = list != null ? String.join(" | ", list) : null; return this; }
            public Builder isBookmarked(Boolean isBookmarked) { this.bookmarked = isBookmarked; return this; }
            public Builder versions(List<VersionDto> versions) { return this; }
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

    public static class VersionDto {
        private Long id;
        private Integer version;
        private String title;
        private String changeSummary;
        private String changedByEmail;
        private LocalDateTime createdAt;

        public VersionDto() {}
        public VersionDto(Long id, Integer version, String title, String changeSummary, String changedByEmail, LocalDateTime createdAt) {
            this.id = id;
            this.version = version;
            this.title = title;
            this.changeSummary = changeSummary;
            this.changedByEmail = changedByEmail;
            this.createdAt = createdAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Integer version;
            private String title;
            private String changeSummary;
            private String changedByEmail;
            private LocalDateTime createdAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder version(Integer version) { this.version = version; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder changeSummary(String changeSummary) { this.changeSummary = changeSummary; return this; }
            public Builder changedByEmail(String changedByEmail) { this.changedByEmail = changedByEmail; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
            public VersionDto build() { return new VersionDto(id, version, title, changeSummary, changedByEmail, createdAt); }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Integer getVersion() { return version; }
        public void setVersion(Integer version) { this.version = version; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getChangeSummary() { return changeSummary; }
        public void setChangeSummary(String changeSummary) { this.changeSummary = changeSummary; }
        public String getChangedByEmail() { return changedByEmail; }
        public void setChangedByEmail(String changedByEmail) { this.changedByEmail = changedByEmail; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }

}