package com.rightsflow.service;

import com.rightsflow.dto.LegalDto;
import com.rightsflow.entity.LegalCategory;
import com.rightsflow.exception.ResourceNotFoundException;
import com.rightsflow.repository.LegalCategoryRepository;
import com.rightsflow.repository.RightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    public CategoryService(LegalCategoryRepository categoryRepository, RightRepository rightRepository) {
        this.categoryRepository = categoryRepository;
        this.rightRepository = rightRepository;
    }



    private final LegalCategoryRepository categoryRepository;
    private final RightRepository rightRepository;

    @Transactional(readOnly = true)
    public List<LegalDto.CategoryDto> getAllCategories() {
        return categoryRepository.findAllByOrderByDisplayOrderAsc().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LegalDto.CategoryDto getCategoryBySlug(String slug) {
        LegalCategory cat = categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "slug", slug));
        return mapToDto(cat);
    }

    private LegalDto.CategoryDto mapToDto(LegalCategory c) {
        return LegalDto.CategoryDto.builder()
                .id(c.getId())
                .name(c.getName())
                .slug(c.getSlug())
                .description(c.getDescription())
                .iconName(c.getIconName())
                .colorTheme(c.getColorTheme())
                .displayOrder(c.getDisplayOrder())
                .rightsCount(0L) // Dynamically calculated or rendered
                .build();
    }
}
