package com.rightsflow.ai;

import com.rightsflow.entity.Embedding;
import com.rightsflow.entity.Right;
import com.rightsflow.repository.EmbeddingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmbeddingService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EmbeddingService.class);

    public EmbeddingService(EmbeddingRepository embeddingRepository) {
        this.embeddingRepository = embeddingRepository;
    }



    private final EmbeddingRepository embeddingRepository;

    @Transactional
    public void generateOrUpdateEmbedding(Right right) {
        log.info("Generating RAG embeddings for Right id={}: {}", right.getId(), right.getTitle());

        String chunk = String.format(
                "%s under category %s. %s. %s. Applicable law: %s. Relevant authority: %s.",
                right.getTitle(),
                right.getCategory() != null ? right.getCategory().getName() : "General",
                right.getShortDescription(),
                right.getSimpleExplanation(),
                right.getLaws() != null && !right.getLaws().isEmpty() ? right.getLaws().iterator().next().getTitle() : "Statutory Provisions",
                right.getRelevantAuthority() != null ? right.getRelevantAuthority() : "Competent Authority"
        );

        String metadataJson = String.format(
                "{\"rightId\": %d, \"slug\": \"%s\", \"category\": \"%s\", \"title\": \"%s\"}",
                right.getId(),
                right.getSlug(),
                right.getCategory() != null ? right.getCategory().getName() : "",
                right.getTitle().replace("\"", "'")
        );

        Embedding embedding = Embedding.builder()
                .right(right)
                .sourceType("RIGHT")
                .contentChunk(chunk)
                .metadataJson(metadataJson)
                .status("EMBEDDED")
                .dimension(768)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        embeddingRepository.save(embedding);
    }

    @Transactional
    public int reindexAllEmbeddings(List<Right> rights) {
        log.info("Re-indexing {} knowledge chunks in pgvector/embeddings repository", rights.size());
        embeddingRepository.deleteAll();
        for (Right right : rights) {
            if ("VERIFIED".equalsIgnoreCase(right.getVerificationStatus())) {
                generateOrUpdateEmbedding(right);
            }
        }
        return (int) embeddingRepository.count();
    }
}
