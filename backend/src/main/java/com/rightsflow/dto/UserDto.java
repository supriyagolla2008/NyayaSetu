package com.rightsflow.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String preferredLanguage;
    private String stateLocation;
    private String status;
    private List<String> roles;
    private List<String> legalInterests;
    private Boolean emailNotifications;
    private Boolean caseUpdates;
    private Boolean legalAlerts;
    private LocalDateTime createdAt;

    public UserDto() {}

    public UserDto(Long id, String fullName, String email, String phone, String preferredLanguage, String stateLocation, String status, List<String> roles, List<String> legalInterests, Boolean emailNotifications, Boolean caseUpdates, Boolean legalAlerts, LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.preferredLanguage = preferredLanguage;
        this.stateLocation = stateLocation;
        this.status = status;
        this.roles = roles;
        this.legalInterests = legalInterests;
        this.emailNotifications = emailNotifications;
        this.caseUpdates = caseUpdates;
        this.legalAlerts = legalAlerts;
        this.createdAt = createdAt;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String fullName;
        private String email;
        private String phone;
        private String preferredLanguage;
        private String stateLocation;
        private String status;
        private List<String> roles;
        private List<String> legalInterests;
        private Boolean emailNotifications;
        private Boolean caseUpdates;
        private Boolean legalAlerts;
        private LocalDateTime createdAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder fullName(String fullName) { this.fullName = fullName; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder preferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; return this; }
        public Builder stateLocation(String stateLocation) { this.stateLocation = stateLocation; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder roles(List<String> roles) { this.roles = roles; return this; }
        public Builder legalInterests(List<String> legalInterests) { this.legalInterests = legalInterests; return this; }
        public Builder emailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; return this; }
        public Builder caseUpdates(Boolean caseUpdates) { this.caseUpdates = caseUpdates; return this; }
        public Builder legalAlerts(Boolean legalAlerts) { this.legalAlerts = legalAlerts; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public UserDto build() {
            return new UserDto(id, fullName, email, phone, preferredLanguage, stateLocation, status, roles, legalInterests, emailNotifications, caseUpdates, legalAlerts, createdAt);
        }
    }

    public static class StatusUpdateRequest {
        private String status;
        public StatusUpdateRequest() {}
        public StatusUpdateRequest(String status) { this.status = status; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    public static class RoleUpdateRequest {
        private List<String> roles;
        public RoleUpdateRequest() {}
        public RoleUpdateRequest(List<String> roles) { this.roles = roles; }
        public List<String> getRoles() { return roles; }
        public void setRoles(List<String> roles) { this.roles = roles; }
    }

    public static class ProfileUpdateRequest {
        private String fullName;
        private String phone;
        private String preferredLanguage;
        private String stateLocation;
        private List<String> legalInterests;
        private Boolean emailNotifications;
        private Boolean caseUpdates;
        private Boolean legalAlerts;

        public ProfileUpdateRequest() {}

        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getPreferredLanguage() { return preferredLanguage; }
        public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
        public String getStateLocation() { return stateLocation; }
        public void setStateLocation(String stateLocation) { this.stateLocation = stateLocation; }
        public List<String> getLegalInterests() { return legalInterests; }
        public void setLegalInterests(List<String> legalInterests) { this.legalInterests = legalInterests; }
        public Boolean getEmailNotifications() { return emailNotifications; }
        public void setEmailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; }
        public Boolean getCaseUpdates() { return caseUpdates; }
        public void setCaseUpdates(Boolean caseUpdates) { this.caseUpdates = caseUpdates; }
        public Boolean getLegalAlerts() { return legalAlerts; }
        public void setLegalAlerts(Boolean legalAlerts) { this.legalAlerts = legalAlerts; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    public String getStateLocation() { return stateLocation; }
    public void setStateLocation(String stateLocation) { this.stateLocation = stateLocation; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
    public List<String> getLegalInterests() { return legalInterests; }
    public void setLegalInterests(List<String> legalInterests) { this.legalInterests = legalInterests; }
    public Boolean getEmailNotifications() { return emailNotifications; }
    public void setEmailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; }
    public Boolean getCaseUpdates() { return caseUpdates; }
    public void setCaseUpdates(Boolean caseUpdates) { this.caseUpdates = caseUpdates; }
    public Boolean getLegalAlerts() { return legalAlerts; }
    public void setLegalAlerts(Boolean legalAlerts) { this.legalAlerts = legalAlerts; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
