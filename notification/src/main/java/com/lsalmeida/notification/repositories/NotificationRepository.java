package com.lsalmeida.notification.repositories;

import com.lsalmeida.notification.enums.NotificationStatus;
import com.lsalmeida.notification.model.NotificationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationModel, UUID> {

    Page<NotificationModel> findAllByUserIdAndNotificationStatus(UUID userId, NotificationStatus status, Pageable pageable);
    Optional<NotificationModel> findNotificationModelByNotificationIdAndUserId(UUID notificationId, UUID userId);
}
