package com.rightsflow.service;

import com.rightsflow.dto.UpdateAndSearchDto;
import com.rightsflow.entity.CaseEntity;
import com.rightsflow.entity.Notification;
import com.rightsflow.entity.User;
import com.rightsflow.repository.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }



    private final NotificationRepository notificationRepository;

    @Transactional
    public void createNotification(User user, CaseEntity caseEntity, String title, String message, String type, String actionUrl) {
        Notification notification = Notification.builder()
                .user(user)
                .caseEntity(caseEntity)
                .title(title)
                .message(message)
                .type(type != null ? type : "INFO")
                .isRead(false)
                .actionUrl(actionUrl)
                .createdAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public List<UpdateAndSearchDto.NotificationItemDto> getUserNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<UpdateAndSearchDto.NotificationItemDto> getUserNotificationsPaged(Long userId, Pageable pageable) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable)
                .map(this::mapToDto);
    }

    @Transactional
    public void markAsRead(Long notificationId, Long userId) {
        notificationRepository.findById(notificationId).ifPresent(n -> {
            if (n.getUser().getId().equals(userId)) {
                n.setIsRead(true);
                notificationRepository.save(n);
            }
        });
    }

    @Transactional
    public void markAllAsRead(Long userId) {
        List<Notification> unread = notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
        unread.forEach(n -> n.setIsRead(true));
        notificationRepository.saveAll(unread);
    }

    public long getUnreadCount(Long userId) {
        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

    private UpdateAndSearchDto.NotificationItemDto mapToDto(Notification n) {
        return UpdateAndSearchDto.NotificationItemDto.builder()
                .id(n.getId())
                .userId(n.getUser().getId())
                .caseId(n.getCaseEntity() != null ? n.getCaseEntity().getId() : null)
                .title(n.getTitle())
                .message(n.getMessage())
                .type(n.getType())
                .isRead(n.getIsRead())
                .actionUrl(n.getActionUrl())
                .createdAt(n.getCreatedAt())
                .build();
    }
}
