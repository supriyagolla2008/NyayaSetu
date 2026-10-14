package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "case_documents")
public class CaseDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    private CaseEntity caseEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private DocumentEntity document;

    @Column(name = "document_category", length = 100)
    private String documentCategory = "SUPPORTING_EVIDENCE";

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "uploaded_at", updatable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    public CaseDocument() {}

    public CaseDocument(Long id, CaseEntity caseEntity, DocumentEntity document, String documentCategory, Boolean isVerified, LocalDateTime uploadedAt) {
        this.id = id;
        this.caseEntity = caseEntity;
        this.document = document;
        this.documentCategory = documentCategory != null ? documentCategory : "SUPPORTING_EVIDENCE";
        this.isVerified = isVerified != null ? isVerified : false;
        this.uploadedAt = uploadedAt != null ? uploadedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private CaseEntity caseEntity;
        private DocumentEntity document;
        private String documentCategory = "SUPPORTING_EVIDENCE";
        private Boolean isVerified = false;
        private LocalDateTime uploadedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder caseEntity(CaseEntity caseEntity) { this.caseEntity = caseEntity; return this; }
        public Builder document(DocumentEntity document) { this.document = document; return this; }
        public Builder documentCategory(String documentCategory) { this.documentCategory = documentCategory; return this; }
        public Builder isVerified(Boolean isVerified) { this.isVerified = isVerified; return this; }
        public Builder uploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; return this; }
        public CaseDocument build() {
            return new CaseDocument(id, caseEntity, document, documentCategory, isVerified, uploadedAt);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public CaseEntity getCaseEntity() { return caseEntity; }
    public void setCaseEntity(CaseEntity caseEntity) { this.caseEntity = caseEntity; }
    public DocumentEntity getDocument() { return document; }
    public void setDocument(DocumentEntity document) { this.document = document; }
    public String getDocumentCategory() { return documentCategory; }
    public void setDocumentCategory(String documentCategory) { this.documentCategory = documentCategory; }
    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}
