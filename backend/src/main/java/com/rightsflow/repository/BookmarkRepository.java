package com.rightsflow.repository;

import com.rightsflow.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUserIdOrderByCreatedAtDesc(Long userId);
    Optional<Bookmark> findByUserIdAndRightId(Long userId, Long rightId);
    boolean existsByUserIdAndRightId(Long userId, Long rightId);
    void deleteByUserIdAndRightId(Long userId, Long rightId);
    long countByUserId(Long userId);
}
