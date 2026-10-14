package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rights")
public class Right {

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

    @Column(name = "short_description", nullable = false, columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "simple_explanation", nullable = false, columnDefinition = "TEXT")
    private String simpleExplanation;

    @Column(name = "detailed_explanation", columnDefinition = "TEXT")
    private String detailedExplanation;

    @Column(name = "who_it_applies_to", columnDefinition = "TEXT")
    private String whoItAppliesTo;

    @Column(name = "why_it_applies", columnDefinition = "TEXT")
    private String whyItApplies;

    @Column(name = "real_world_example", columnDefinition = "TEXT")
    private String realWorldExample;

    @Column(name = "recommended_actions", columnDefinition = "TEXT")
    private String recommendedActions;

    @Column(name = "required_documents", columnDefinition = "TEXT")
    private String requiredDocuments;

    @Column(name = "relevant_authority", length = 255)
    private String relevantAuthority;

    @Column(name = "relevant_service", length = 255)
    private String relevantService;

    @Column(name = "procedure_summary", columnDefinition = "TEXT")
    private String procedureSummary;

    @Column(name = "official_source_url", length = 500)
    private String officialSourceUrl;

    @Column(name = "source_authority", length = 255)
    private String sourceAuthority;

    @Column(name = "last_verified")
    private LocalDate lastVerified = LocalDate.now();

    @Column(name = "effective_from")
    private LocalDate effectiveFrom = LocalDate.now();

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    private Integer version = 1;

    @Column(name = "verification_status", length = 30)
    private String verificationStatus = "VERIFIED";

    @Column(name = "content_status", length = 30)
    private String contentStatus = "PUBLISHED";

    @Column(name = "view_count")
    private Long viewCount = 0L;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "right_laws",
            joinColumns = @JoinColumn(name = "right_id"),
            inverseJoinColumns = @JoinColumn(name = "law_id")
    )
    private Set<Law> laws = new HashSet<>();

    @OneToMany(mappedBy = "right", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Faq> faqs = new ArrayList<>();

    @OneToMany(mappedBy = "right", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Example> examples = new ArrayList<>();

    @OneToMany(mappedBy = "right", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LegalVersion> versions = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Right() {}

    public Right(Long id, LegalCategory category, String title, String slug, String shortDescription, String simpleExplanation, String detailedExplanation, String whoItAppliesTo, String whyItApplies, String realWorldExample, String recommendedActions, String requiredDocuments, String relevantAuthority, String relevantService, String procedureSummary, String officialSourceUrl, String sourceAuthority, LocalDate lastVerified, LocalDate effectiveFrom, LocalDate effectiveTo, Integer version, String verificationStatus, String contentStatus, Long viewCount, Set<Law> laws, List<Faq> faqs, List<Example> examples, List<LegalVersion> versions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.category = category;
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
        this.lastVerified = lastVerified != null ? lastVerified : LocalDate.now();
        this.effectiveFrom = effectiveFrom != null ? effectiveFrom : LocalDate.now();
        this.effectiveTo = effectiveTo;
        this.version = version != null ? version : 1;
        this.verificationStatus = verificationStatus != null ? verificationStatus : "VERIFIED";
        this.contentStatus = contentStatus != null ? contentStatus : "PUBLISHED";
        this.viewCount = viewCount != null ? viewCount : 0L;
        this.laws = laws != null ? laws : new HashSet<>();
        this.faqs = faqs != null ? faqs : new ArrayList<>();
        this.examples = examples != null ? examples : new ArrayList<>();
        this.versions = versions != null ? versions : new ArrayList<>();
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private LegalCategory category;
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
        private LocalDate lastVerified = LocalDate.now();
        private LocalDate effectiveFrom = LocalDate.now();
        private LocalDate effectiveTo;
        private Integer version = 1;
        private String verificationStatus = "VERIFIED";
        private String contentStatus = "PUBLISHED";
        private Long viewCount = 0L;
        private Set<Law> laws = new HashSet<>();
        private List<Faq> faqs = new ArrayList<>();
        private List<Example> examples = new ArrayList<>();
        private List<LegalVersion> versions = new ArrayList<>();
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder category(LegalCategory category) { this.category = category; return this; }
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
        public Builder laws(Set<Law> laws) { this.laws = laws; return this; }
        public Builder faqs(List<Faq> faqs) { this.faqs = faqs; return this; }
        public Builder examples(List<Example> examples) { this.examples = examples; return this; }
        public Builder versions(List<LegalVersion> versions) { this.versions = versions; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public Right build() {
            return new Right(id, category, title, slug, shortDescription, simpleExplanation, detailedExplanation, whoItAppliesTo, whyItApplies, realWorldExample, recommendedActions, requiredDocuments, relevantAuthority, relevantService, procedureSummary, officialSourceUrl, sourceAuthority, lastVerified, effectiveFrom, effectiveTo, version, verificationStatus, contentStatus, viewCount, laws, faqs, examples, versions, createdAt, updatedAt);
        }
    }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LegalCategory getCategory() { return category; }
    public void setCategory(LegalCategory category) { this.category = category; }
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
    public Set<Law> getLaws() { return laws; }
    public void setLaws(Set<Law> laws) { this.laws = laws; }
    public List<Faq> getFaqs() { return faqs; }
    public void setFaqs(List<Faq> faqs) { this.faqs = faqs; }
    public List<Example> getExamples() { return examples; }
    public void setExamples(List<Example> examples) { this.examples = examples; }
    public List<LegalVersion> getVersions() { return versions; }
    public void setVersions(List<LegalVersion> versions) { this.versions = versions; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
