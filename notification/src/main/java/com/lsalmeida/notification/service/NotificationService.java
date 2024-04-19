package com.lsalmeida.notification.service;

import com.lsalmeida.notification.model.NotificationModel;
import com.lsalmeida.notification.model.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface NotificationService {
    NotificationModel save(NotificationModel notificationModel);

    Page<NotificationModel> findAllNotificationsByUser(UUID userId, Pageable pageable);
    Optional<NotificationModel> findByNotificationIdAndUserId(UUID notificationId, UUID userId);
    NotificationModel updateNotification(UUID notificationId, UUID userId, NotificationDto dto);
}
