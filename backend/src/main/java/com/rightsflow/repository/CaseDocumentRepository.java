package com.rightsflow.repository;

import com.rightsflow.entity.CaseDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaseDocumentRepository extends JpaRepository<CaseDocument, Long> {
    List<CaseDocument> findByCaseEntityId(Long caseId);
    Optional<CaseDocument> findByCaseEntityIdAndDocumentId(Long caseId, Long documentId);
}
