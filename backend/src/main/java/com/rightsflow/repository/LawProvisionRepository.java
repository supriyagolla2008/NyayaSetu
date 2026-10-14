package com.rightsflow.repository;

import com.rightsflow.entity.LawProvision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawProvisionRepository extends JpaRepository<LawProvision, Long> {
    List<LawProvision> findByLawId(Long lawId);
}
