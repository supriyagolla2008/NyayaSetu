package com.rightsflow.controller;

import com.rightsflow.dto.LegalDto;
import com.rightsflow.entity.User;
import com.rightsflow.service.BookmarkService;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookmarks")
@Tag(name = "Bookmarks", description = "Citizen saved legal rights")
public class BookmarkController {
    public BookmarkController(BookmarkService bookmarkService, UserService userService) {
        this.bookmarkService = bookmarkService;
        this.userService = userService;
    }



    private final BookmarkService bookmarkService;
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all saved/bookmarked legal rights for current citizen")
    public ResponseEntity<List<LegalDto.RightSummaryDto>> getBookmarks(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(bookmarkService.getUserBookmarks(user.getId()));
    }

    @PostMapping("/{rightId}")
    @Operation(summary = "Toggle bookmark status for a legal right in PostgreSQL")
    public ResponseEntity<Map<String, Boolean>> toggleBookmark(
            @PathVariable Long rightId,
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        boolean bookmarked = bookmarkService.toggleBookmark(rightId, user);
        return ResponseEntity.ok(Map.of("bookmarked", bookmarked));
    }
}
