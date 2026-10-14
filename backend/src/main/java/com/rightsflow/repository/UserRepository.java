package com.rightsflow.repository;

import com.rightsflow.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
           "(LOWER(u.fullName) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :query, '%'))) AND " +
           "(:status IS NULL OR u.status = :status)")
    Page<User> searchUsers(@Param("query") String query, @Param("status") String status, Pageable pageable);

    @Query("SELECT u FROM User u WHERE (:status IS NULL OR u.status = :status)")
    Page<User> findFiltered(@Param("status") String status, Pageable pageable);

    long countByStatus(String status);
}
