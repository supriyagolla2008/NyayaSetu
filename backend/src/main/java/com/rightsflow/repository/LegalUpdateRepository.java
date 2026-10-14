package com.rightsflow.repository;

import com.rightsflow.entity.LegalUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegalUpdateRepository extends JpaRepository<LegalUpdate, Long> {

    @Query("SELECT u FROM LegalUpdate u WHERE " +
           "(:categoryId IS NULL OR u.category.id = :categoryId) AND " +
           "(:status IS NULL OR u.status = :status) AND " +
           "(LOWER(u.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(u.summary) LIKE LOWER(CONCAT('%', :query, '%')))")
    Page<LegalUpdate> searchLegalUpdates(
            @Param("query") String query,
            @Param("categoryId") Long categoryId,
            @Param("status") String status,
            Pageable pageable
    );

    @Query("SELECT u FROM LegalUpdate u WHERE " +
           "(:categoryId IS NULL OR u.category.id = :categoryId) AND " +
           "(:status IS NULL OR u.status = :status)")
    Page<LegalUpdate> findFiltered(
            @Param("categoryId") Long categoryId,
            @Param("status") String status,
            Pageable pageable
    );

    List<LegalUpdate> findTop5ByStatusOrderByPublishedDateDesc(String status);
    long countByStatus(String status);
}
