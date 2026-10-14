package com.rightsflow.controller;

import com.rightsflow.ai.AIService;
import com.rightsflow.dto.ProblemDto;
import com.rightsflow.dto.UpdateAndSearchDto;
import com.rightsflow.entity.User;
import com.rightsflow.service.SearchService;
import com.rightsflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "AI Problem Solver & Search", description = "AI legal analysis, intent classification, and semantic RAG search")
public class ProblemController {
    public ProblemController(AIService aiService, SearchService searchService, UserService userService) {
        this.aiService = aiService;
        this.searchService = searchService;
        this.userService = userService;
    }



    private final AIService aiService;
    private final SearchService searchService;
    private final UserService userService;

    @PostMapping("/problems/analyze")
    @Operation(summary = "Submit problem in natural language (text/voice) for AI RAG legal analysis")
    public ResponseEntity<ProblemDto.AIAnalysisResponse> analyzeProblem(
            @RequestBody ProblemDto.ProblemAnalysisRequest request
    ) {
        return ResponseEntity.ok(aiService.analyzeProblem(request));
    }

    @PostMapping("/ai/chat")
    @Operation(summary = "AI Legal Awareness Assistant conversational query")
    public ResponseEntity<ProblemDto.AIAnalysisResponse> chatWithAI(
            @RequestBody ProblemDto.ProblemAnalysisRequest request
    ) {
        return ResponseEntity.ok(aiService.analyzeProblem(request));
    }

    @GetMapping("/search")
    @Operation(summary = "Unified search across rights, laws, services, and updates")
    public ResponseEntity<UpdateAndSearchDto.GlobalSearchResponse> search(
            @RequestParam String query,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "ALL") String type,
            Authentication authentication
    ) {
        User user = authentication != null ? userService.findByEmail(authentication.getName()) : null;
        return ResponseEntity.ok(searchService.performSearch(query, categoryId, type, user));
    }
}
