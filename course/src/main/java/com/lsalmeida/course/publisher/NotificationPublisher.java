package com.lsalmeida.course.publisher;

import com.lsalmeida.course.model.dto.NotificationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;
    @Value(value = "${api.broker.exchange.notificationCommandExchange}")
    private String notificationCommandExchange;
    @Value(value = "${api.broker.key.notificationCommandKey}")
    private String notificationCommandKey;

    public void publishNotificationCommand(NotificationCommand command) {
        rabbitTemplate.convertAndSend(
                notificationCommandExchange,
                notificationCommandKey,
                command);
    }


}
