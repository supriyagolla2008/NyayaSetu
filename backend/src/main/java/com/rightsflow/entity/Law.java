package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "laws")
public class Law {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "short_code", length = 50)
    private String shortCode;

    private Integer year;

    @Column(name = "enactment_date")
    private LocalDate enactmentDate;

    @Column(length = 100)
    private String jurisdiction = "India";

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    private LegalSource source;

    @Column(name = "official_link", length = 500)
    private String officialLink;

    @Column(length = 30)
    private String status = "ACTIVE";

    @OneToMany(mappedBy = "law", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LawProvision> provisions = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Law() {}

    public Law(Long id, String title, String shortCode, Integer year, LocalDate enactmentDate, String jurisdiction, String description, LegalSource source, String officialLink, String status, List<LawProvision> provisions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.shortCode = shortCode;
        this.year = year;
        this.enactmentDate = enactmentDate;
        this.jurisdiction = jurisdiction != null ? jurisdiction : "India";
        this.description = description;
        this.source = source;
        this.officialLink = officialLink;
        this.status = status != null ? status : "ACTIVE";
        this.provisions = provisions != null ? provisions : new ArrayList<>();
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String title;
        private String shortCode;
        private Integer year;
        private LocalDate enactmentDate;
        private String jurisdiction = "India";
        private String description;
        private LegalSource source;
        private String officialLink;
        private String status = "ACTIVE";
        private List<LawProvision> provisions = new ArrayList<>();
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder shortCode(String shortCode) { this.shortCode = shortCode; return this; }
        public Builder year(Integer year) { this.year = year; return this; }
        public Builder enactmentDate(LocalDate enactmentDate) { this.enactmentDate = enactmentDate; return this; }
        public Builder jurisdiction(String jurisdiction) { this.jurisdiction = jurisdiction; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder source(LegalSource source) { this.source = source; return this; }
        public Builder officialLink(String officialLink) { this.officialLink = officialLink; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder provisions(List<LawProvision> provisions) { this.provisions = provisions; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public Law build() {
            return new Law(id, title, shortCode, year, enactmentDate, jurisdiction, description, source, officialLink, status, provisions, createdAt, updatedAt);
        }
    }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

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
    public LegalSource getSource() { return source; }
    public void setSource(LegalSource source) { this.source = source; }
    public String getOfficialLink() { return officialLink; }
    public void setOfficialLink(String officialLink) { this.officialLink = officialLink; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<LawProvision> getProvisions() { return provisions; }
    public void setProvisions(List<LawProvision> provisions) { this.provisions = provisions; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
