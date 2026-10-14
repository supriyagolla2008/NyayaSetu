package com.rightsflow.service;

import com.rightsflow.dto.CaseDto;
import com.rightsflow.entity.CaseDocument;
import com.rightsflow.entity.CaseEntity;
import com.rightsflow.entity.DocumentEntity;
import com.rightsflow.entity.User;
import com.rightsflow.exception.BadRequestException;
import com.rightsflow.exception.ResourceNotFoundException;
import com.rightsflow.exception.UnauthorizedException;
import com.rightsflow.repository.CaseDocumentRepository;
import com.rightsflow.repository.CaseRepository;
import com.rightsflow.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentStorageService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DocumentStorageService.class);

    public DocumentStorageService(DocumentRepository documentRepository, CaseDocumentRepository caseDocumentRepository, CaseRepository caseRepository, AuditLogService auditLogService) {
        this.documentRepository = documentRepository;
        this.caseDocumentRepository = caseDocumentRepository;
        this.caseRepository = caseRepository;
        this.auditLogService = auditLogService;
    }



    private final DocumentRepository documentRepository;
    private final CaseDocumentRepository caseDocumentRepository;
    private final CaseRepository caseRepository;
    private final AuditLogService auditLogService;

    @Value("${app.storage.upload-dir:./uploads}")
    private String uploadDir;

    @Transactional
    public CaseDto.CaseDocumentItemDto uploadDocument(MultipartFile file, Long caseId, String category, User user) {
        if (file.isEmpty()) {
            throw new BadRequestException("Uploaded file cannot be empty");
        }

        try {
            Path root = Paths.get(uploadDir);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            String originalName = file.getOriginalFilename() != null ? file.getOriginalFilename() : "document.pdf";
            String fileExtension = originalName.contains(".") ? originalName.substring(originalName.lastIndexOf(".")) : ".pdf";
            String savedFileName = "doc_" + UUID.randomUUID().toString() + fileExtension;
            Path destination = root.resolve(savedFileName);

            Files.copy(file.getInputStream(), destination);

            DocumentEntity doc = DocumentEntity.builder()
                    .user(user)
                    .fileName(savedFileName)
                    .originalName(originalName)
                    .fileType(file.getContentType() != null ? file.getContentType() : "application/octet-stream")
                    .fileSize(file.getSize())
                    .filePath(destination.toString())
                    .description("Uploaded document: " + originalName)
                    .build();

            DocumentEntity savedDoc = documentRepository.save(doc);

            CaseDocument caseDoc = null;
            if (caseId != null) {
                CaseEntity caseEntity = caseRepository.findById(caseId)
                        .orElseThrow(() -> new ResourceNotFoundException("Case", "id", caseId));

                caseDoc = CaseDocument.builder()
                        .caseEntity(caseEntity)
                        .document(savedDoc)
                        .documentCategory(category != null ? category : "SUPPORTING_EVIDENCE")
                        .isVerified(false)
                        .build();
                caseDoc = caseDocumentRepository.save(caseDoc);
            }

            auditLogService.logAction(user, "DOCUMENT_UPLOADED", "DOCUMENT", savedDoc.getId().toString(), "Uploaded: " + originalName);

            return CaseDto.CaseDocumentItemDto.builder()
                    .id(caseDoc != null ? caseDoc.getId() : savedDoc.getId())
                    .documentId(savedDoc.getId())
                    .fileName(savedDoc.getFileName())
                    .originalName(savedDoc.getOriginalName())
                    .fileType(savedDoc.getFileType())
                    .fileSize(savedDoc.getFileSize())
                    .documentCategory(caseDoc != null ? caseDoc.getDocumentCategory() : "GENERAL")
                    .isVerified(caseDoc != null && caseDoc.getIsVerified())
                    .uploadedAt(savedDoc.getCreatedAt())
                    .build();

        } catch (IOException e) {
            log.error("Failed to store file", e);
            throw new BadRequestException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<CaseDto.CaseDocumentItemDto> getUserDocuments(Long userId) {
        return documentRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(d -> CaseDto.CaseDocumentItemDto.builder()
                        .id(d.getId())
                        .documentId(d.getId())
                        .fileName(d.getFileName())
                        .originalName(d.getOriginalName())
                        .fileType(d.getFileType())
                        .fileSize(d.getFileSize())
                        .documentCategory("GENERAL")
                        .isVerified(true)
                        .uploadedAt(d.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteDocument(Long documentId, User user) {
        DocumentEntity doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document", "id", documentId));

        if (!doc.getUser().getId().equals(user.getId()) && user.getRoles().stream().noneMatch(r -> r.getName().equals("ADMINISTRATOR"))) {
            throw new UnauthorizedException("You do not have permission to delete this document.");
        }

        documentRepository.delete(doc);
        auditLogService.logAction(user, "DOCUMENT_DELETED", "DOCUMENT", documentId.toString(), "Deleted file: " + doc.getOriginalName());
    }
}
