package com.rightsflow.entity;

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
