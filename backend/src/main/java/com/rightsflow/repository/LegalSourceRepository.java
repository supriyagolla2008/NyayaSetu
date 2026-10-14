package com.rightsflow.repository;

import com.rightsflow.entity.LegalSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegalSourceRepository extends JpaRepository<LegalSource, Long> {
    List<LegalSource> findByIsActiveTrue();
}
