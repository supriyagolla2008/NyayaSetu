package com.rightsflow.service;

import com.rightsflow.dto.AnalyticsDto;
import com.rightsflow.entity.Feedback;
import com.rightsflow.entity.User;
import com.rightsflow.repository.FeedbackRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class FeedbackService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FeedbackService.class);

    public FeedbackService(FeedbackRepository feedbackRepository, AuditLogService auditLogService) {
        this.feedbackRepository = feedbackRepository;
        this.auditLogService = auditLogService;
    }



    private final FeedbackRepository feedbackRepository;
    private final AuditLogService auditLogService;

    @Transactional
    public AnalyticsDto.FeedbackResponse submitFeedback(AnalyticsDto.FeedbackRequest request, User user) {
        Feedback feedback = Feedback.builder()
                .user(user)
                .feedbackType(request.getFeedbackType() != null ? request.getFeedbackType() : "GENERAL")
                .rating(request.getRating())
                .comments(request.getComments())
                .createdAt(LocalDateTime.now())
                .build();

        Feedback saved = feedbackRepository.save(feedback);
        if (user != null) {
            auditLogService.logAction(user, "FEEDBACK_SUBMITTED", "FEEDBACK", saved.getId().toString(), "Rating: " + request.getRating());
        }

        return AnalyticsDto.FeedbackResponse.builder()
                .id(saved.getId())
                .userEmail(user != null ? user.getEmail() : "Anonymous")
                .feedbackType(saved.getFeedbackType())
                .rating(saved.getRating())
                .comments(saved.getComments())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<AnalyticsDto.FeedbackResponse> getAllFeedback(Pageable pageable) {
        return feedbackRepository.findAllByOrderByCreatedAtDesc(pageable)
                .map(f -> AnalyticsDto.FeedbackResponse.builder()
                        .id(f.getId())
                        .userEmail(f.getUser() != null ? f.getUser().getEmail() : "Anonymous")
                        .feedbackType(f.getFeedbackType())
                        .rating(f.getRating())
                        .comments(f.getComments())
                        .createdAt(f.getCreatedAt())
                        .build());
    }
}
