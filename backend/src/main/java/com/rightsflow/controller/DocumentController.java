package com.rightsflow.controller;

import com.rightsflow.dto.CaseDto;
import com.rightsflow.entity.User;
import com.rightsflow.service.DocumentStorageService;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@Tag(name = "Document Management", description = "Secure document uploads, attachments, and metadata tracking")
public class DocumentController {
    public DocumentController(DocumentStorageService documentService, UserService userService) {
        this.documentService = documentService;
        this.userService = userService;
    }



    private final DocumentStorageService documentService;
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all documents uploaded by current authenticated citizen")
    public ResponseEntity<List<CaseDto.CaseDocumentItemDto>> getUserDocuments(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(documentService.getUserDocuments(user.getId()));
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload a document and optionally link to a legal case")
    public ResponseEntity<CaseDto.CaseDocumentItemDto> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "caseId", required = false) Long caseId,
            @RequestParam(value = "category", defaultValue = "SUPPORTING_EVIDENCE") String category,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(documentService.uploadDocument(file, caseId, category, user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an uploaded document with backend authorization checks")
    public ResponseEntity<Void> deleteDocument(
            @PathVariable Long id,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        documentService.deleteDocument(id, user);
        return ResponseEntity.noContent().build();
    }
}
