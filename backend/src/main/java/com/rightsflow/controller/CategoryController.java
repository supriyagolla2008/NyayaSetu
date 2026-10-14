package com.rightsflow.controller;

import com.rightsflow.dto.LegalDto;
import com.rightsflow.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Legal Categories", description = "Legal categories and curated thematic areas")
public class CategoryController {
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all legal categories ordered by display order")
    public ResponseEntity<List<LegalDto.CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{slug}")
    @Operation(summary = "Get category details by slug")
    public ResponseEntity<LegalDto.CategoryDto> getCategoryBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(categoryService.getCategoryBySlug(slug));
    }
}
