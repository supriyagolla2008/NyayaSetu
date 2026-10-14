package com.rightsflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "embeddings")
public class Embedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "right_id")
    private Right right;

    @Column(name = "source_type", length = 50)
    private String sourceType = "RIGHT";

    @Column(name = "content_chunk", nullable = false, columnDefinition = "TEXT")
    private String contentChunk;

    @Column(name = "metadata_json", columnDefinition = "TEXT")
    private String metadataJson;

    @Column(name = "embedding_vector", columnDefinition = "TEXT")
    private String embeddingVector;

    private Integer dimension = 768;

    @Column(length = 30)
    private String status = "EMBEDDED";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Embedding() {}

    public Embedding(Long id, Right right, String sourceType, String contentChunk, String metadataJson, String embeddingVector, Integer dimension, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.right = right;
        this.sourceType = sourceType != null ? sourceType : "RIGHT";
        this.contentChunk = contentChunk;
        this.metadataJson = metadataJson;
        this.embeddingVector = embeddingVector;
        this.dimension = dimension != null ? dimension : 768;
        this.status = status != null ? status : "EMBEDDED";
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Right right;
        private String sourceType = "RIGHT";
        private String contentChunk;
        private String metadataJson;
        private String embeddingVector;
        private Integer dimension = 768;
        private String status = "EMBEDDED";
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder right(Right right) { this.right = right; return this; }
        public Builder sourceType(String sourceType) { this.sourceType = sourceType; return this; }
        public Builder contentChunk(String contentChunk) { this.contentChunk = contentChunk; return this; }
        public Builder metadataJson(String metadataJson) { this.metadataJson = metadataJson; return this; }
        public Builder embeddingVector(String embeddingVector) { this.embeddingVector = embeddingVector; return this; }
        public Builder dimension(Integer dimension) { this.dimension = dimension; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public Embedding build() {
            return new Embedding(id, right, sourceType, contentChunk, metadataJson, embeddingVector, dimension, status, createdAt, updatedAt);
        }
    }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Right getRight() { return right; }
    public void setRight(Right right) { this.right = right; }
    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }
    public String getContentChunk() { return contentChunk; }
    public void setContentChunk(String contentChunk) { this.contentChunk = contentChunk; }
    public String getMetadataJson() { return metadataJson; }
    public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }
    public String getEmbeddingVector() { return embeddingVector; }
    public void setEmbeddingVector(String embeddingVector) { this.embeddingVector = embeddingVector; }
    public Integer getDimension() { return dimension; }
    public void setDimension(Integer dimension) { this.dimension = dimension; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
