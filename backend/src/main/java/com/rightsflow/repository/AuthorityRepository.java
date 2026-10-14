package com.rightsflow.repository;

import com.rightsflow.entity.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Query("SELECT a FROM Authority a WHERE " +
           "(LOWER(a.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(a.description) LIKE LOWER(CONCAT('%', :query, '%')))")
    Page<Authority> searchAuthorities(@Param("query") String query, Pageable pageable);

    @Query("SELECT a FROM Authority a")
    Page<Authority> findFiltered(Pageable pageable);

    List<Authority> findByJurisdictionLevel(String jurisdictionLevel);
}
