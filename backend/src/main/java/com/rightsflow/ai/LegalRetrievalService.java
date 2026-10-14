package com.rightsflow.ai;

import com.rightsflow.entity.Law;
import com.rightsflow.entity.Right;
import com.rightsflow.repository.RightRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LegalRetrievalService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LegalRetrievalService.class);

    public LegalRetrievalService(RightRepository rightRepository) {
        this.rightRepository = rightRepository;
    }



    private final RightRepository rightRepository;

    @Transactional(readOnly = true)
    public Optional<Right> retrieveBestMatchingRight(String problemText, String categoryName) {
        log.info("Retrieving verified legal right for category: {}", categoryName);

        // Find verified & published rights
        List<Right> allRights = rightRepository.findAll();
        Right bestMatch = null;
        double maxScore = -1.0;

        String query = (problemText != null ? problemText : "").toLowerCase(Locale.ROOT);
        String[] queryWords = query.split("\\s+");

        for (Right right : allRights) {
            if (!"PUBLISHED".equalsIgnoreCase(right.getContentStatus()) ||
                !"VERIFIED".equalsIgnoreCase(right.getVerificationStatus())) {
                continue;
            }

            double score = 0.0;
            if (categoryName != null && right.getCategory() != null &&
                right.getCategory().getName().equalsIgnoreCase(categoryName)) {
                score += 10.0;
            }

            String searchCorp = (right.getTitle() + " " +
                                 right.getShortDescription() + " " +
                                 right.getSimpleExplanation() + " " +
                                 (right.getWhoItAppliesTo() != null ? right.getWhoItAppliesTo() : "")).toLowerCase(Locale.ROOT);

            for (String word : queryWords) {
                if (word.length() > 3 && searchCorp.contains(word)) {
                    score += 2.0;
                }
            }

            if (score > maxScore) {
                maxScore = score;
                bestMatch = right;
            }
        }

        if (bestMatch != null && maxScore > 0) {
            return Optional.of(bestMatch);
        }

        // Fallback to top verified right in category if found
        if (categoryName != null) {
            for (Right right : allRights) {
                if (right.getCategory() != null && right.getCategory().getName().equalsIgnoreCase(categoryName)) {
                    return Optional.of(right);
                }
            }
        }

        return allRights.stream()
                .filter(r -> "VERIFIED".equalsIgnoreCase(r.getVerificationStatus()))
                .findFirst();
    }
}
