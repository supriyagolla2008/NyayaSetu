package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "law_provisions")
public class LawProvision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "law_id", nullable = false)
    private Law law;

    @Column(name = "section_number", nullable = false, length = 100)
    private String sectionNumber;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "simple_summary", columnDefinition = "TEXT")
    private String simpleSummary;

    @Column(name = "full_text", columnDefinition = "TEXT")
    private String fullText;

    @Column(name = "penalty_or_remedy", columnDefinition = "TEXT")
    private String penaltyOrRemedy;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public LawProvision() {}

    public LawProvision(Long id, Law law, String sectionNumber, String title, String simpleSummary, String fullText, String penaltyOrRemedy, LocalDateTime createdAt) {
        this.id = id;
        this.law = law;
        this.sectionNumber = sectionNumber;
        this.title = title;
        this.simpleSummary = simpleSummary;
        this.fullText = fullText;
        this.penaltyOrRemedy = penaltyOrRemedy;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Law law;
        private String sectionNumber;
        private String title;
        private String simpleSummary;
        private String fullText;
        private String penaltyOrRemedy;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder law(Law law) { this.law = law; return this; }
        public Builder sectionNumber(String sectionNumber) { this.sectionNumber = sectionNumber; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder simpleSummary(String simpleSummary) { this.simpleSummary = simpleSummary; return this; }
        public Builder fullText(String fullText) { this.fullText = fullText; return this; }
        public Builder penaltyOrRemedy(String penaltyOrRemedy) { this.penaltyOrRemedy = penaltyOrRemedy; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public LawProvision build() {
            return new LawProvision(id, law, sectionNumber, title, simpleSummary, fullText, penaltyOrRemedy, createdAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Law getLaw() { return law; }
    public void setLaw(Law law) { this.law = law; }
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
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
