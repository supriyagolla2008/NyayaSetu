package com.rightsflow.repository;

import com.rightsflow.entity.LegalVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegalVersionRepository extends JpaRepository<LegalVersion, Long> {
    List<LegalVersion> findByRightIdOrderByVersionDesc(Long rightId);
}
