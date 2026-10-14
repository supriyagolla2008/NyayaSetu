package com.rightsflow.entity;

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
