package com.lsalmeida.notification.service.impl;

import com.lsalmeida.notification.enums.NotificationStatus;
import com.lsalmeida.notification.exception.NotificationNotFoundException;
import com.lsalmeida.notification.model.NotificationModel;
import com.lsalmeida.notification.model.dto.NotificationDto;
import com.lsalmeida.notification.repositories.NotificationRepository;
import com.lsalmeida.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public NotificationModel save(NotificationModel notificationModel) {
        return notificationRepository.save(notificationModel);
    }

    @Override
    public Page<NotificationModel> findAllNotificationsByUser(UUID userId, Pageable pageable) {
        return notificationRepository.findAllByUserIdAndNotificationStatus(
                userId,
                NotificationStatus.CREATED,
                pageable);
    }

    @Override
    public Optional<NotificationModel> findByNotificationIdAndUserId(UUID notificationId, UUID userId) {
        return notificationRepository.findNotificationModelByNotificationIdAndUserId(notificationId, userId);
    }

    @Override
    public NotificationModel updateNotification(UUID notificationId, UUID userId, NotificationDto dto) {
        NotificationModel notificationModel = findByNotificationIdAndUserId(notificationId, userId)
                .map(not -> {
                    not.setNotificationStatus(dto.status());
                    return not;
                })
                .orElseThrow(NotificationNotFoundException::new);
        return save(notificationModel);
    }

}
