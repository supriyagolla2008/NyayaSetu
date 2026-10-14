package com.rightsflow.repository;

import com.rightsflow.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> findByUserIdOrderByCreatedAtDesc(Long userId);
    long countByUserId(Long userId);
}
