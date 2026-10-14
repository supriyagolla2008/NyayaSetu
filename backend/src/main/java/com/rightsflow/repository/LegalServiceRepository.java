package com.rightsflow.repository;

import com.rightsflow.entity.LegalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegalServiceRepository extends JpaRepository<LegalService, Long> {

    @Query("SELECT s FROM LegalService s WHERE " +
           "(LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(s.description) LIKE LOWER(CONCAT('%', :query, '%'))) AND " +
           "(:categoryId IS NULL OR s.category.id = :categoryId)")
    Page<LegalService> searchServices(@Param("query") String query, @Param("categoryId") Long categoryId, Pageable pageable);

    @Query("SELECT s FROM LegalService s WHERE (:categoryId IS NULL OR s.category.id = :categoryId)")
    Page<LegalService> findFiltered(@Param("categoryId") Long categoryId, Pageable pageable);

    List<LegalService> findByCategoryId(Long categoryId);
}
