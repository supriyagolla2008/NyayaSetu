package com.rightsflow.repository;

import com.rightsflow.entity.CaseAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseActionRepository extends JpaRepository<CaseAction, Long> {
    List<CaseAction> findByCaseEntityIdOrderByOrderIndexAsc(Long caseId);
    long countByCaseEntityUserIdAndStatus(Long userId, String status);
}
