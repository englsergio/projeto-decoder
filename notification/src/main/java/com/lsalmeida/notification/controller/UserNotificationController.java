package com.lsalmeida.notification.controller;

import com.lsalmeida.notification.enums.NotificationStatus;
import com.lsalmeida.notification.exception.NotificationNotFoundException;
import com.lsalmeida.notification.model.NotificationModel;
import com.lsalmeida.notification.model.dto.NotificationDto;
import com.lsalmeida.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserNotificationController {

    private final NotificationService notificationService;

    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<Page<NotificationModel>> getAllNotificationsByUser(@PathVariable UUID userId,
                                                                             @PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.findAllNotificationsByUser(userId, pageable));
    }

    @PutMapping("/users/{userId}/notifications/{notificationId}")
    public ResponseEntity<NotificationModel> updateNotification(@PathVariable UUID userId,
                                                                @PathVariable UUID notificationId,
                                                                @RequestBody @Valid NotificationDto dto) {
        NotificationModel updated = notificationService.updateNotification(notificationId, userId, dto);
        return ResponseEntity.ok(updated);
    }

}
