package com.rightsflow.entity;

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
