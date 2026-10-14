package com.rightsflow.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_preferences")
public class NotificationPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "email_notifications")
    private Boolean emailNotifications = true;

    @Column(name = "case_status_updates")
    private Boolean caseStatusUpdates = true;

    @Column(name = "action_reminders")
    private Boolean actionReminders = true;

    @Column(name = "legal_alerts")
    private Boolean legalAlerts = true;

    @Column(name = "system_announcements")
    private Boolean systemAnnouncements = true;

    public NotificationPreference() {}
    public NotificationPreference(Long id, User user, Boolean emailNotifications, Boolean caseStatusUpdates, Boolean actionReminders, Boolean legalAlerts, Boolean systemAnnouncements) {
        this.id = id;
        this.user = user;
        this.emailNotifications = emailNotifications;
        this.caseStatusUpdates = caseStatusUpdates;
        this.actionReminders = actionReminders;
        this.legalAlerts = legalAlerts;
        this.systemAnnouncements = systemAnnouncements;
    }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private User user;
        private Boolean emailNotifications = true;
        private Boolean caseStatusUpdates = true;
        private Boolean actionReminders = true;
        private Boolean legalAlerts = true;
        private Boolean systemAnnouncements = true;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder emailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; return this; }
        public Builder caseStatusUpdates(Boolean caseStatusUpdates) { this.caseStatusUpdates = caseStatusUpdates; return this; }
        public Builder actionReminders(Boolean actionReminders) { this.actionReminders = actionReminders; return this; }
        public Builder legalAlerts(Boolean legalAlerts) { this.legalAlerts = legalAlerts; return this; }
        public Builder systemAnnouncements(Boolean systemAnnouncements) { this.systemAnnouncements = systemAnnouncements; return this; }
        public NotificationPreference build() {
            return new NotificationPreference(id, user, emailNotifications, caseStatusUpdates, actionReminders, legalAlerts, systemAnnouncements);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Boolean getEmailNotifications() { return emailNotifications; }
    public void setEmailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; }
    public Boolean getCaseStatusUpdates() { return caseStatusUpdates; }
    public void setCaseStatusUpdates(Boolean caseStatusUpdates) { this.caseStatusUpdates = caseStatusUpdates; }
    public Boolean getActionReminders() { return actionReminders; }
    public void setActionReminders(Boolean actionReminders) { this.actionReminders = actionReminders; }
    public Boolean getLegalAlerts() { return legalAlerts; }
    public void setLegalAlerts(Boolean legalAlerts) { this.legalAlerts = legalAlerts; }
    public Boolean getSystemAnnouncements() { return systemAnnouncements; }
    public void setSystemAnnouncements(Boolean systemAnnouncements) { this.systemAnnouncements = systemAnnouncements; }
}
