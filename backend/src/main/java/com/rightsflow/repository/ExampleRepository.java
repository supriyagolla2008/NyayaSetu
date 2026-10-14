package com.rightsflow.repository;

import com.rightsflow.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
    List<Example> findByRightId(Long rightId);
}
