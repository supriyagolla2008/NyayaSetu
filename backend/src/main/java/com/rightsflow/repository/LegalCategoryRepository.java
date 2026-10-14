package com.rightsflow.repository;

import com.rightsflow.entity.LegalCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LegalCategoryRepository extends JpaRepository<LegalCategory, Long> {
    Optional<LegalCategory> findBySlug(String slug);
    List<LegalCategory> findAllByOrderByDisplayOrderAsc();
}
