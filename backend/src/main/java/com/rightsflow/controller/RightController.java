package com.rightsflow.controller;

import com.rightsflow.dto.LegalDto;
import com.rightsflow.entity.User;
import com.rightsflow.service.RightService;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rights")
@Tag(name = "Legal Rights Library", description = "Public & citizen legal rights exploration and retrieval")
public class RightController {
    public RightController(RightService rightService, UserService userService) {
        this.rightService = rightService;
        this.userService = userService;
    }



    private final RightService rightService;
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Search and filter published legal rights")
    public ResponseEntity<Page<LegalDto.RightSummaryDto>> searchRights(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "viewCount") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(rightService.searchRights(query, categoryId, "PUBLISHED", "VERIFIED", pageable));
    }

    @GetMapping("/{slugOrId}")
    @Operation(summary = "Get full legal right details, statutory provisions, actions, and FAQs")
    public ResponseEntity<LegalDto.RightDetailDto> getRightDetail(
            @PathVariable String slugOrId,
            Authentication authentication
    ) {
        User user = authentication != null ? userService.findByEmail(authentication.getName()) : null;
        return ResponseEntity.ok(rightService.getRightBySlugOrId(slugOrId, user));
    }
}
