package com.rightsflow.repository;

import com.rightsflow.entity.RecentlyViewed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecentlyViewedRepository extends JpaRepository<RecentlyViewed, Long> {
    List<RecentlyViewed> findTop6ByUserIdOrderByViewedAtDesc(Long userId);
    Optional<RecentlyViewed> findByUserIdAndRightId(Long userId, Long rightId);
}
