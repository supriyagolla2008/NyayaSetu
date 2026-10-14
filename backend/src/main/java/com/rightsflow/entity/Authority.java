package com.rightsflow.entity;

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
