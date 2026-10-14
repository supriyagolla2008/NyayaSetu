package com.rightsflow.repository;

import com.rightsflow.entity.Right;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RightRepository extends JpaRepository<Right, Long> {
    Optional<Right> findBySlug(String slug);

    @Query("SELECT r FROM Right r WHERE " +
           "(:categoryId IS NULL OR r.category.id = :categoryId) AND " +
           "(:contentStatus IS NULL OR r.contentStatus = :contentStatus) AND " +
           "(:verificationStatus IS NULL OR r.verificationStatus = :verificationStatus) AND " +
           "(LOWER(r.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           " LOWER(r.shortDescription) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           " LOWER(r.simpleExplanation) LIKE LOWER(CONCAT('%', :query, '%')))")
    Page<Right> searchRights(
            @Param("query") String query,
            @Param("categoryId") Long categoryId,
            @Param("contentStatus") String contentStatus,
            @Param("verificationStatus") String verificationStatus,
            Pageable pageable
    );

    @Query("SELECT r FROM Right r WHERE " +
           "(:categoryId IS NULL OR r.category.id = :categoryId) AND " +
           "(:contentStatus IS NULL OR r.contentStatus = :contentStatus) AND " +
           "(:verificationStatus IS NULL OR r.verificationStatus = :verificationStatus)")
    Page<Right> findFiltered(
            @Param("categoryId") Long categoryId,
            @Param("contentStatus") String contentStatus,
            @Param("verificationStatus") String verificationStatus,
            Pageable pageable
    );

    List<Right> findTop6ByContentStatusAndVerificationStatusOrderByViewCountDesc(String contentStatus, String verificationStatus);

    long countByContentStatus(String contentStatus);
    long countByVerificationStatus(String verificationStatus);
}
