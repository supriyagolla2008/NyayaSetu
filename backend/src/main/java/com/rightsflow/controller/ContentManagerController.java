package com.rightsflow.controller;

import com.rightsflow.ai.EmbeddingService;
import com.rightsflow.dto.AnalyticsDto;
import com.rightsflow.dto.LegalDto;
import com.rightsflow.dto.UpdateAndSearchDto;
import com.rightsflow.entity.Embedding;
import com.rightsflow.entity.Right;
import com.rightsflow.entity.User;
import com.rightsflow.repository.ContentReportRepository;
import com.rightsflow.repository.EmbeddingRepository;
import com.rightsflow.repository.RightRepository;
import com.rightsflow.service.LegalUpdateService;
import com.rightsflow.service.RightService;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/content")
@PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'ADMINISTRATOR')")
@Tag(name = "Content Manager", description = "Legal knowledge authoring, verification, versioning, and RAG index management")
public class ContentManagerController {
    public ContentManagerController(RightService rightService, LegalUpdateService updateService, UserService userService, EmbeddingService embeddingService, RightRepository rightRepository, EmbeddingRepository embeddingRepository, ContentReportRepository reportRepository) {
        this.rightService = rightService;
        this.updateService = updateService;
        this.userService = userService;
        this.embeddingService = embeddingService;
        this.rightRepository = rightRepository;
        this.embeddingRepository = embeddingRepository;
        this.reportRepository = reportRepository;
    }



    private final RightService rightService;
    private final LegalUpdateService updateService;
    private final UserService userService;
    private final EmbeddingService embeddingService;
    private final RightRepository rightRepository;
    private final EmbeddingRepository embeddingRepository;
    private final ContentReportRepository reportRepository;

    @GetMapping("/rights")
    @Operation(summary = "List all rights with drafts, under review, and outdated statuses")
    public ResponseEntity<Page<LegalDto.RightSummaryDto>> getAllRights(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String contentStatus,
            @RequestParam(required = false) String verificationStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(rightService.searchRights(query, categoryId, contentStatus, verificationStatus, pageable));
    }

    @PostMapping("/rights")
    @Operation(summary = "Create a new legal right in PostgreSQL with versioning and embeddings")
    public ResponseEntity<LegalDto.RightDetailDto> createRight(
            @RequestBody LegalDto.RightCreateUpdateRequest request,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(rightService.createRight(request, user));
    }

    @PutMapping("/rights/{id}")
    @Operation(summary = "Update and verify an existing legal right with version snapshot")
    public ResponseEntity<LegalDto.RightDetailDto> updateRight(
            @PathVariable Long id,
            @RequestBody LegalDto.RightCreateUpdateRequest request,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(rightService.updateRight(id, request, user));
    }

    @DeleteMapping("/rights/{id}")
    @Operation(summary = "Archive a legal right")
    public ResponseEntity<Void> archiveRight(
            @PathVariable Long id,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        rightService.deleteOrArchiveRight(id, user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/legal-updates")
    @Operation(summary = "Publish a verified legal update")
    public ResponseEntity<UpdateAndSearchDto.LegalUpdateItemDto> createUpdate(
            @RequestBody UpdateAndSearchDto.LegalUpdateCreateRequest request,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(updateService.createLegalUpdate(request, user));
    }

    @GetMapping("/rag/embeddings")
    @Operation(summary = "Get RAG knowledge chunks and embedding statuses")
    public ResponseEntity<Page<AnalyticsDto.EmbeddingItemDto>> getEmbeddings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AnalyticsDto.EmbeddingItemDto> items = embeddingRepository.findAllByOrderByCreatedAtDesc(pageable)
                .map(e -> AnalyticsDto.EmbeddingItemDto.builder()
                        .id(e.getId())
                        .rightId(e.getRight() != null ? e.getRight().getId() : null)
                        .rightTitle(e.getRight() != null ? e.getRight().getTitle() : "General Knowledge")
                        .sourceType(e.getSourceType())
                        .contentChunk(e.getContentChunk())
                        .metadataJson(e.getMetadataJson())
                        .status(e.getStatus())
                        .dimension(e.getDimension())
                        .createdAt(e.getCreatedAt())
                        .updatedAt(e.getUpdatedAt())
                        .build());
        return ResponseEntity.ok(items);
    }

    @PostMapping("/rag/reindex")
    @Operation(summary = "Trigger full re-indexing of all verified legal knowledge into pgvector embeddings")
    public ResponseEntity<Map<String, Object>> reindexKnowledge() {
        List<Right> all = rightRepository.findAll();
        int count = embeddingService.reindexAllEmbeddings(all);
        return ResponseEntity.ok(Map.of("message", "Reindexing completed successfully", "chunksIndexed", count));
    }

    @GetMapping("/reports")
    @Operation(summary = "Get user reported content and outdated legal notices")
    public ResponseEntity<Page<AnalyticsDto.ContentReportResponse>> getReports(
            @RequestParam(defaultValue = "PENDING") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AnalyticsDto.ContentReportResponse> reports = reportRepository.findByStatusOrderByCreatedAtDesc(status, pageable)
                .map(r -> AnalyticsDto.ContentReportResponse.builder()
                        .id(r.getId())
                        .rightId(r.getRight().getId())
                        .rightTitle(r.getRight().getTitle())
                        .reporterEmail(r.getUser() != null ? r.getUser().getEmail() : "Anonymous")
                        .reason(r.getReason())
                        .details(r.getDetails())
                        .status(r.getStatus())
                        .createdAt(r.getCreatedAt())
                        .build());
        return ResponseEntity.ok(reports);
    }
}
