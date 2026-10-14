package com.rightsflow.service;

import com.rightsflow.dto.AnalyticsDto;
import com.rightsflow.entity.AuditLog;
import com.rightsflow.entity.User;
import com.rightsflow.repository.AuditLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuditLogService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuditLogService.class);

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }



    private final AuditLogRepository auditLogRepository;

    @Transactional
    public void logAction(User actor, String action, String entityName, String entityId, String details) {
        try {
            AuditLog auditLog = AuditLog.builder()
                    .actor(actor)
                    .actorEmail(actor != null ? actor.getEmail() : "SYSTEM")
                    .action(action)
                    .entityName(entityName)
                    .entityId(entityId)
                    .details(details)
                    .createdAt(LocalDateTime.now())
                    .build();
            auditLogRepository.save(auditLog);
        } catch (Exception e) {
            log.error("Failed to write audit log: {}", e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Page<AnalyticsDto.AuditLogItemDto> getAuditLogs(Pageable pageable) {
        return auditLogRepository.findAllByOrderByCreatedAtDesc(pageable)
                .map(log -> AnalyticsDto.AuditLogItemDto.builder()
                        .id(log.getId())
                        .actorEmail(log.getActorEmail())
                        .action(log.getAction())
                        .entityName(log.getEntityName())
                        .entityId(log.getEntityId())
                        .details(log.getDetails())
                        .ipAddress(log.getIpAddress())
                        .createdAt(log.getCreatedAt())
                        .build());
    }
}
