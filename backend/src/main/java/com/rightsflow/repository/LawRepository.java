package com.rightsflow.repository;

import com.rightsflow.entity.Law;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawRepository extends JpaRepository<Law, Long> {

    @Query("SELECT l FROM Law l WHERE " +
           "(LOWER(l.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(l.shortCode) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(l.description) LIKE LOWER(CONCAT('%', :query, '%'))) AND " +
           "(:status IS NULL OR l.status = :status)")
    Page<Law> searchLaws(@Param("query") String query, @Param("status") String status, Pageable pageable);

    @Query("SELECT l FROM Law l WHERE (:status IS NULL OR l.status = :status)")
    Page<Law> findFiltered(@Param("status") String status, Pageable pageable);

    List<Law> findByStatus(String status);
}
