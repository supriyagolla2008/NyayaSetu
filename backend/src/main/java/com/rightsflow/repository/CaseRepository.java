package com.rightsflow.repository;

import com.rightsflow.entity.CaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaseRepository extends JpaRepository<CaseEntity, Long> {
    Optional<CaseEntity> findByCaseNumber(String caseNumber);

    List<CaseEntity> findByUserIdOrderByCreatedAtDesc(Long userId);

    @Query("SELECT c FROM CaseEntity c WHERE " +
           "(:userId IS NULL OR c.user.id = :userId) AND " +
           "(:status IS NULL OR c.status = :status) AND " +
           "(:categoryId IS NULL OR c.category.id = :categoryId) AND " +
           "(LOWER(c.caseNumber) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(c.title) LIKE LOWER(CONCAT('%', :query, '%')))")
    Page<CaseEntity> searchCases(
            @Param("userId") Long userId,
            @Param("status") String status,
            @Param("categoryId") Long categoryId,
            @Param("query") String query,
            Pageable pageable
    );

    @Query("SELECT c FROM CaseEntity c WHERE " +
           "(:userId IS NULL OR c.user.id = :userId) AND " +
           "(:status IS NULL OR c.status = :status) AND " +
           "(:categoryId IS NULL OR c.category.id = :categoryId)")
    Page<CaseEntity> findFiltered(
            @Param("userId") Long userId,
            @Param("status") String status,
            @Param("categoryId") Long categoryId,
            Pageable pageable
    );

    long countByUserId(Long userId);
    long countByUserIdAndStatus(Long userId, String status);
    long countByUserIdAndStatusNotIn(Long userId, List<String> statuses);
    long countByStatus(String status);
}
