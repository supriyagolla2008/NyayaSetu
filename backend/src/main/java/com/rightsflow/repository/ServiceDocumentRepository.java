package com.rightsflow.repository;

import com.rightsflow.entity.ServiceDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceDocumentRepository extends JpaRepository<ServiceDocument, Long> {
    List<ServiceDocument> findByServiceId(Long serviceId);
}
