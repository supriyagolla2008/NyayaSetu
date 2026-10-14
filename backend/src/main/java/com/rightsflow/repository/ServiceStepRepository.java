package com.rightsflow.repository;

import com.rightsflow.entity.ServiceStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceStepRepository extends JpaRepository<ServiceStep, Long> {
    List<ServiceStep> findByServiceIdOrderByStepNumberAsc(Long serviceId);
}
