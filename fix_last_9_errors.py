import os
import re

BASE = "/Users/gollasupriyashri/.gemini/antigravity/scratch/rightsflow/backend/src/main/java/com/rightsflow"

# 1. Update CaseDto.CaseStatusHistoryDto builder to add changedByName
with open(f"{BASE}/dto/CaseDto.java", "r") as f:
    case_dto = f.read()

history_patch = """        public static class Builder {
            private Long id;
            private String fromStatus;
            private String toStatus;
            private String remarks;
            private String changedBy;
            private LocalDateTime changedAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder fromStatus(String fromStatus) { this.fromStatus = fromStatus; return this; }
            public Builder toStatus(String toStatus) { this.toStatus = toStatus; return this; }
            public Builder remarks(String remarks) { this.remarks = remarks; return this; }
            public Builder changedBy(String changedBy) { this.changedBy = changedBy; return this; }
            public Builder changedByName(String changedByName) { this.changedBy = changedByName; return this; }
            public Builder changedAt(LocalDateTime changedAt) { this.changedAt = changedAt; return this; }
            public CaseStatusHistoryDto build() {
                return new CaseStatusHistoryDto(id, fromStatus, toStatus, remarks, changedBy, changedAt);
            }
        }"""

case_dto = re.sub(r"public static class Builder \{\s*private Long id;\s*private String fromStatus;.*?public CaseStatusHistoryDto build\(\).*?\}", history_patch, case_dto, flags=re.DOTALL)
with open(f"{BASE}/dto/CaseDto.java", "w") as f:
    f.write(case_dto)
print("CaseDto changedByName patched.")


# 2. Update AnalyticsDto.AuditLogItemDto builder to add actorEmail
with open(f"{BASE}/dto/AnalyticsDto.java", "r") as f:
    analytics_dto = f.read()

audit_patch = """            public Builder actorEmail(String actorEmail) { this.userName = actorEmail; return this; }
            public Builder id(Long id) { this.id = id; return this; }"""
analytics_dto = analytics_dto.replace("public Builder id(Long id) { this.id = id; return this; }", audit_patch, 1)
with open(f"{BASE}/dto/AnalyticsDto.java", "w") as f:
    f.write(analytics_dto)
print("AnalyticsDto actorEmail patched.")


# 3. Update UpdateAndSearchDto.NotificationItemDto builder to add userId and isRead
with open(f"{BASE}/dto/UpdateAndSearchDto.java", "r") as f:
    notif_dto = f.read()

notif_patch = """            public Builder userId(Long userId) { return this; }
            public Builder isRead(Boolean isRead) { this.read = isRead; return this; }
            public Builder id(Long id) { this.id = id; return this; }"""
notif_dto = notif_dto.replace("public Builder id(Long id) { this.id = id; return this; }", notif_patch, 1)
with open(f"{BASE}/dto/UpdateAndSearchDto.java", "w") as f:
    f.write(notif_dto)
print("UpdateAndSearchDto userId/isRead patched.")


# 4. In LegalDto.java:
# - Add VersionDto
# - In RightDetailDto.Builder: allow List<String> or String for recommendedActions and requiredDocuments, and add isBookmarked(Boolean)
with open(f"{BASE}/dto/LegalDto.java", "r") as f:
    leg_dto = f.read()

version_dto = """
    public static class VersionDto {
        private Long id;
        private Integer version;
        private String title;
        private String changeSummary;
        private String changedByEmail;
        private LocalDateTime createdAt;

        public VersionDto() {}
        public VersionDto(Long id, Integer version, String title, String changeSummary, String changedByEmail, LocalDateTime createdAt) {
            this.id = id;
            this.version = version;
            this.title = title;
            this.changeSummary = changeSummary;
            this.changedByEmail = changedByEmail;
            this.createdAt = createdAt;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private Integer version;
            private String title;
            private String changeSummary;
            private String changedByEmail;
            private LocalDateTime createdAt;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder version(Integer version) { this.version = version; return this; }
            public Builder title(String title) { this.title = title; return this; }
            public Builder changeSummary(String changeSummary) { this.changeSummary = changeSummary; return this; }
            public Builder changedByEmail(String changedByEmail) { this.changedByEmail = changedByEmail; return this; }
            public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
            public VersionDto build() { return new VersionDto(id, version, title, changeSummary, changedByEmail, createdAt); }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Integer getVersion() { return version; }
        public void setVersion(Integer version) { this.version = version; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getChangeSummary() { return changeSummary; }
        public void setChangeSummary(String changeSummary) { this.changeSummary = changeSummary; }
        public String getChangedByEmail() { return changedByEmail; }
        public void setChangedByEmail(String changedByEmail) { this.changedByEmail = changedByEmail; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }
"""

last_b = leg_dto.rfind("}")
leg_dto = leg_dto[:last_b] + version_dto + "\n}"

# In RightDetailDto.Builder, add overloaded recommendedActions(List<String>), requiredDocuments(List<String>), isBookmarked(Boolean)
actions_patch = """            public Builder recommendedActions(String recommendedActions) { this.recommendedActions = recommendedActions; return this; }
            public Builder recommendedActions(List<String> list) { this.recommendedActions = list != null ? String.join(" | ", list) : null; return this; }
            public Builder requiredDocuments(String requiredDocuments) { this.requiredDocuments = requiredDocuments; return this; }
            public Builder requiredDocuments(List<String> list) { this.requiredDocuments = list != null ? String.join(" | ", list) : null; return this; }
            public Builder isBookmarked(Boolean isBookmarked) { this.bookmarked = isBookmarked; return this; }"""

leg_dto = leg_dto.replace("public Builder recommendedActions(String recommendedActions) { this.recommendedActions = recommendedActions; return this; }", actions_patch)
# remove the duplicate requiredDocuments(String) right below it
leg_dto = leg_dto.replace("""            public Builder recommendedActions(String recommendedActions) { this.recommendedActions = recommendedActions; return this; }
            public Builder recommendedActions(List<String> list) { this.recommendedActions = list != null ? String.join(" | ", list) : null; return this; }
            public Builder requiredDocuments(String requiredDocuments) { this.requiredDocuments = requiredDocuments; return this; }
            public Builder requiredDocuments(List<String> list) { this.requiredDocuments = list != null ? String.join(" | ", list) : null; return this; }
            public Builder isBookmarked(Boolean isBookmarked) { this.bookmarked = isBookmarked; return this; }
            public Builder requiredDocuments(String requiredDocuments) { this.requiredDocuments = requiredDocuments; return this; }""", actions_patch)

with open(f"{BASE}/dto/LegalDto.java", "w") as f:
    f.write(leg_dto)
print("LegalDto VersionDto and Builder overloads patched.")
