package com.rightsflow.repository;

import com.rightsflow.entity.Embedding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmbeddingRepository extends JpaRepository<Embedding, Long> {
    List<Embedding> findByStatus(String status);
    long countByStatus(String status);

    @Query("SELECT e FROM Embedding e WHERE " +
           "LOWER(e.contentChunk) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(e.metadataJson) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Embedding> searchSimilarChunks(@Param("query") String query);

    Page<Embedding> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
