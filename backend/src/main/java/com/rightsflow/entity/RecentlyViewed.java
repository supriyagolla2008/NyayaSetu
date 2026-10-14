package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recently_viewed")
public class RecentlyViewed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id", nullable = false)
    private Right right;

    @Column(name = "viewed_at")
    private LocalDateTime viewedAt = LocalDateTime.now();

    public RecentlyViewed() {}

    public RecentlyViewed(Long id, User user, Right right, LocalDateTime viewedAt) {
        this.id = id;
        this.user = user;
        this.right = right;
        this.viewedAt = viewedAt != null ? viewedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private User user;
        private Right right;
        private LocalDateTime viewedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder right(Right right) { this.right = right; return this; }
        public Builder viewedAt(LocalDateTime viewedAt) { this.viewedAt = viewedAt; return this; }
        public RecentlyViewed build() { return new RecentlyViewed(id, user, right, viewedAt); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
    public LocalDateTime getViewedAt() { return viewedAt; }
    public void setViewedAt(LocalDateTime viewedAt) { this.viewedAt = viewedAt; }
}
