package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_preferences")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "legal_interests", columnDefinition = "TEXT")
    private String legalInterests;

    @Column(name = "email_notifications")
    private Boolean emailNotifications = true;

    @Column(name = "sms_notifications")
    private Boolean smsNotifications = false;

    @Column(name = "case_updates")
    private Boolean caseUpdates = true;

    @Column(name = "legal_alerts")
    private Boolean legalAlerts = true;

    @Column(name = "voice_assistant_enabled")
    private Boolean voiceAssistantEnabled = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public UserPreference() {}

    public UserPreference(Long id, User user, String legalInterests, Boolean emailNotifications, Boolean smsNotifications, Boolean caseUpdates, Boolean legalAlerts, Boolean voiceAssistantEnabled, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.legalInterests = legalInterests;
        this.emailNotifications = emailNotifications != null ? emailNotifications : true;
        this.smsNotifications = smsNotifications != null ? smsNotifications : false;
        this.caseUpdates = caseUpdates != null ? caseUpdates : true;
        this.legalAlerts = legalAlerts != null ? legalAlerts : true;
        this.voiceAssistantEnabled = voiceAssistantEnabled != null ? voiceAssistantEnabled : true;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private String legalInterests;
        private Boolean emailNotifications = true;
        private Boolean smsNotifications = false;
        private Boolean caseUpdates = true;
        private Boolean legalAlerts = true;
        private Boolean voiceAssistantEnabled = true;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder legalInterests(String legalInterests) { this.legalInterests = legalInterests; return this; }
        public Builder emailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; return this; }
        public Builder smsNotifications(Boolean smsNotifications) { this.smsNotifications = smsNotifications; return this; }
        public Builder caseUpdates(Boolean caseUpdates) { this.caseUpdates = caseUpdates; return this; }
        public Builder legalAlerts(Boolean legalAlerts) { this.legalAlerts = legalAlerts; return this; }
        public Builder voiceAssistantEnabled(Boolean voiceAssistantEnabled) { this.voiceAssistantEnabled = voiceAssistantEnabled; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public UserPreference build() {
            return new UserPreference(id, user, legalInterests, emailNotifications, smsNotifications, caseUpdates, legalAlerts, voiceAssistantEnabled, createdAt, updatedAt);
        }
    }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getLegalInterests() { return legalInterests; }
    public void setLegalInterests(String legalInterests) { this.legalInterests = legalInterests; }
    public Boolean getEmailNotifications() { return emailNotifications; }
    public void setEmailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; }
    public Boolean getSmsNotifications() { return smsNotifications; }
    public void setSmsNotifications(Boolean smsNotifications) { this.smsNotifications = smsNotifications; }
    public Boolean getCaseUpdates() { return caseUpdates; }
    public void setCaseUpdates(Boolean caseUpdates) { this.caseUpdates = caseUpdates; }
    public Boolean getLegalAlerts() { return legalAlerts; }
    public void setLegalAlerts(Boolean legalAlerts) { this.legalAlerts = legalAlerts; }
    public Boolean getVoiceAssistantEnabled() { return voiceAssistantEnabled; }
    public void setVoiceAssistantEnabled(Boolean voiceAssistantEnabled) { this.voiceAssistantEnabled = voiceAssistantEnabled; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
