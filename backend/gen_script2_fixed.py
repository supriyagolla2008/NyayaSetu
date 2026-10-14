import os
import textwrap

BASE_DIR = "/Users/gollasupriyashri/.gemini/antigravity/scratch/rightsflow/backend"
PKG_DIR = os.path.join(BASE_DIR, "src/main/java/com/rightsflow")

def write_file(path, content):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w", encoding="utf-8") as f:
        f.write(textwrap.dedent(content).strip() + "\n")

entities = {
    "User": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
@Column(unique = true, nullable = false) private String email;
private String password; private String fullName; private String phone;
private String preferredLanguage = "EN"; private String state;
private Boolean active = true; private Boolean emailVerified = false;
private LocalDateTime createdAt; private LocalDateTime updatedAt;
@ManyToMany(fetch = FetchType.EAGER) @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")) private Set<Role> roles = new HashSet<>();
@OneToMany(mappedBy = "userId") private List<Case> cases = new ArrayList<>();
@OneToMany(mappedBy = "userId") private List<Bookmark> bookmarks = new ArrayList<>();
@OneToMany(mappedBy = "userId") private List<Notification> notifications = new ArrayList<>();
@PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); updatedAt = LocalDateTime.now(); }
@PreUpdate protected void onUpdate() { updatedAt = LocalDateTime.now(); }""",
    "Role": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
@Enumerated(EnumType.STRING) private RoleName name; private String description;""",
    "UserPreference": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long userId; private Boolean notificationEnabled; private Boolean emailNotification;
@Column(columnDefinition="TEXT") private String legalInterests; private String language;""",
    "LegalCategory": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String name; @Column(unique = true) private String slug; private String description;
private String iconName; private String accentColor; private Integer displayOrder; private Boolean active;
@OneToMany(mappedBy = "category") private List<LegalRight> rights = new ArrayList<>();
@OneToMany(mappedBy = "categoryId") private List<Issue> issues = new ArrayList<>();""",
    "Issue": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String title; private String description; private Long categoryId;
@ManyToMany private Set<LegalRight> relatedRights = new HashSet<>();""",
    "LegalRight": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String title; private String slug; private Long categoryId;
private String shortDescription; @Column(columnDefinition="TEXT") private String simpleExplanation;
@Column(columnDefinition="TEXT") private String detailedExplanation; private String whoItAppliesTo;
private String whyItMayApply; private String applicableLaw; private String relevantProvision;
private String realWorldExample; @Column(columnDefinition="TEXT") private String recommendedActions;
@Column(columnDefinition="TEXT") private String requiredDocuments; private String authority;
private String service; private String procedure; private String officialSourceUrl;
private String sourceAuthority; private LocalDateTime publishedDate; private LocalDateTime effectiveFrom;
private LocalDateTime effectiveTo; private LocalDateTime lastVerified; private Integer version;
@Enumerated(EnumType.STRING) private VerificationStatus verificationStatus;
@Enumerated(EnumType.STRING) private ContentStatus contentStatus; private String language;
private Long createdBy; private LocalDateTime createdAt; private LocalDateTime updatedAt;
@ManyToOne @JoinColumn(name = "categoryId", insertable=false, updatable=false) private LegalCategory category;
@ManyToMany private Set<Law> laws = new HashSet<>();""",
    "Law": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String title; private String shortTitle; private String description;
private String actNumber; private Integer yearEnacted; private String sourceUrl;
private String sourceAuthority; private Boolean active; private LocalDateTime createdAt;
@OneToMany(mappedBy = "lawId") private List<LawProvision> provisions = new ArrayList<>();""",
    "LawProvision": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long lawId; private String sectionNumber; private String title;
private String description; private String simpleExplanation; private String sourceUrl; private Boolean active;""",
    "LegalSource": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String name; private String authority; private String baseUrl;
private String description; private String trustLevel; private Boolean active;""",
    "LegalVersion": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String entityType; private Long entityId; private Integer version;
private String changeDescription; private Long changedBy; private LocalDateTime changedAt;
@Column(columnDefinition="TEXT") private String previousData;""",
    "LegalService": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String name; private String authority; private String description; private String eligibility;
@Column(columnDefinition="TEXT") private String procedure; private String officialWebsite;
private String contactInfo; private Long relatedCategoryId; private Boolean active;
@OneToMany(mappedBy = "serviceId") private List<ServiceStep> serviceSteps = new ArrayList<>();
@OneToMany(mappedBy = "serviceId") private List<ServiceDocument> serviceDocuments = new ArrayList<>();""",
    "Authority": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String name; private String type; private String jurisdiction; private String description;
private String website; private String contactEmail; private String contactPhone; private String address; private Boolean active;""",
    "ServiceStep": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long serviceId; private Integer stepNumber; private String title; private String description;""",
    "ServiceDocument": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long serviceId; private String documentName; private String description; private Boolean mandatory;""",
    "FAQ": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String question; private String answer; private Long categoryId; private Long rightId;
private String language; private Integer displayOrder; private Boolean active;""",
    "Example": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String title; private String scenario; private String outcome;
private Long categoryId; private Long rightId; private String language; private Boolean active;""",
    "Case": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
@Column(unique = true) private String caseNumber; private Long userId;
private String title; private String description; private Long categoryId;
@Enumerated(EnumType.STRING) private CaseStatus status;
private LocalDateTime createdAt; private LocalDateTime updatedAt; private LocalDateTime resolvedAt;
@ManyToMany private Set<LegalRight> relatedRights = new HashSet<>();
@OneToMany(mappedBy = "caseId") private List<CaseStatusHistory> statusHistory = new ArrayList<>();
@OneToMany(mappedBy = "caseId") private List<CaseAction> actions = new ArrayList<>();
@OneToMany(mappedBy = "caseId") private List<CaseDocument> documents = new ArrayList<>();""",
    "CaseStatusHistory": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long caseId; @Enumerated(EnumType.STRING) private CaseStatus fromStatus;
@Enumerated(EnumType.STRING) private CaseStatus toStatus; private Long changedBy;
private LocalDateTime changedAt; private String notes;""",
    "CaseAction": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long caseId; private String title; private String description; private String actionType;
private String status; private LocalDateTime dueDate; private LocalDateTime completedAt; private Integer displayOrder;""",
    "CaseDocument": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long caseId; private Long documentId;""",
    "Document": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long userId; private String fileName; private String originalFileName;
private String contentType; private Long fileSize; private String storagePath; private LocalDateTime uploadedAt;""",
    "Notification": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long userId; private String title; private String message;
@Enumerated(EnumType.STRING) private NotificationType type; private Boolean isRead = false;
private LocalDateTime readAt; private LocalDateTime createdAt; private String relatedEntityType; private Long relatedEntityId;""",
    "NotificationPreference": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long userId; @Enumerated(EnumType.STRING) private NotificationType type;
private Boolean enabled; private Boolean emailEnabled;""",
    "LegalUpdate": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String title; private String summary; private String whatChanged;
private String whoIsAffected; private LocalDateTime effectiveDate; private Long categoryId;
private String sourceUrl; private String sourceAuthority; private LocalDateTime publishedDate;
private LocalDateTime lastVerified; private Integer version; @Enumerated(EnumType.STRING) private ContentStatus status;
private Long createdBy; private LocalDateTime createdAt;""",
    "Bookmark": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long userId; @Enumerated(EnumType.STRING) private SearchEntityType entityType;
private Long entityId; private LocalDateTime createdAt;""",
    "RecentlyViewed": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long userId; @Enumerated(EnumType.STRING) private SearchEntityType entityType;
private Long entityId; private LocalDateTime viewedAt;""",
    "SearchHistory": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long userId; private String query; private String language;
private Integer resultCount; private LocalDateTime searchedAt;""",
    "Feedback": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long userId; @Enumerated(EnumType.STRING) private FeedbackType type;
private Integer rating; private String comment; private String relatedEntityType;
private Long relatedEntityId; private String status; private LocalDateTime createdAt;""",
    "ContentReport": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long userId; private String entityType; private Long entityId;
@Enumerated(EnumType.STRING) private ReportType reportType; private String description;
@Enumerated(EnumType.STRING) private ReportStatus status; private Long reviewedBy;
private LocalDateTime reviewedAt; private LocalDateTime createdAt;""",
    "Embedding": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private String entityType; private Long entityId; @Column(columnDefinition="TEXT") private String content;
@Column(columnDefinition="vector(1536)") private Object embedding; private String model; private LocalDateTime createdAt;""",
    "AuditLog": """@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
private Long actorId; private String actorEmail; private String action;
private String entityType; private Long entityId; @Column(columnDefinition="TEXT") private String details;
private String ipAddress; private LocalDateTime createdAt;"""
}

# Write Entities
for name, fields in entities.items():
    content = f"""
package com.rightsflow.entity;

import com.rightsflow.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "{name.lower()}s")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class {name} {{
{fields}
}}
"""
    write_file(os.path.join(PKG_DIR, f"entity/{name}.java"), content)

# Write Repositories
for name in entities.keys():
    methods = ""
    if name == "User": methods = 'Optional<User> findByEmail(String email); boolean existsByEmail(String email);'
    if name == "LegalRight": methods = 'List<LegalRight> findByCategoryId(Long categoryId); List<LegalRight> findByVerificationStatus(VerificationStatus status); @Query("SELECT r FROM LegalRight r WHERE LOWER(r.title) LIKE LOWER(CONCAT(\\'%\\', :query, \\'%\\'))") List<LegalRight> search(@Param("query") String query);'
    if name == "Case": methods = 'List<Case> findByUserId(Long userId); List<Case> findByStatus(CaseStatus status); long countByUserId(Long userId);'
    if name == "Notification": methods = 'List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId); long countByUserIdAndIsReadFalse(Long userId);'
    if name == "LegalCategory": methods = 'List<LegalCategory> findByActiveTrueOrderByDisplayOrder();'
    if name == "SearchHistory": methods = 'List<SearchHistory> findByUserIdOrderBySearchedAtDesc(Long userId);'
    if name == "Bookmark": methods = 'List<Bookmark> findByUserId(Long userId); boolean existsByUserIdAndEntityTypeAndEntityId(Long userId, SearchEntityType entityType, Long entityId);'
    if name == "Feedback": methods = 'List<Feedback> findByStatus(String status);'
    if name == "AuditLog": methods = 'List<AuditLog> findByActorId(Long actorId); List<AuditLog> findByEntityType(String entityType);'
    if name == "Embedding": methods = 'Optional<Embedding> findByEntityTypeAndEntityId(String entityType, Long entityId);'
    if name == "LegalUpdate": methods = 'List<LegalUpdate> findByStatusOrderByPublishedDateDesc(ContentStatus status);'
    
    content = f"""
package com.rightsflow.repository;

import com.rightsflow.entity.{name};
import com.rightsflow.entity.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface {name}Repository extends JpaRepository<{name}, Long> {{
    {methods}
}}
"""
    write_file(os.path.join(PKG_DIR, f"repository/{name}Repository.java"), content)

print("Entities & Repositories generation script done.")
