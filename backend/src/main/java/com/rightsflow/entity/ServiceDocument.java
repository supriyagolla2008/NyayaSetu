package com.rightsflow.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_documents")
public class ServiceDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private LegalService service;

    @Column(name = "document_name", nullable = false, length = 255)
    private String documentName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean mandatory = true;

    @Column(name = "format_allowed", length = 100)
    private String formatAllowed;

    public ServiceDocument() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LegalService getService() { return service; }
    public void setService(LegalService service) { this.service = service; }
    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getMandatory() { return mandatory; }
    public void setMandatory(Boolean mandatory) { this.mandatory = mandatory; }
    public Boolean getIsMandatory() { return mandatory != null ? mandatory : true; }
    public String getFormatAllowed() { return formatAllowed; }
    public void setFormatAllowed(String formatAllowed) { this.formatAllowed = formatAllowed; }
}
