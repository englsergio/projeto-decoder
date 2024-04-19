package com.lsalmeida.notification.consumer;

import com.lsalmeida.notification.mapper.NotificationMapper;
import com.lsalmeida.notification.model.NotificationModel;
import com.lsalmeida.notification.model.dto.NotificationCommand;
import com.lsalmeida.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationConsumer {

    private final NotificationMapper mapper;
    private final NotificationService notificationService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${api.broker.queue.notificationCommandQueue.name}", durable = "true"),
            exchange = @Exchange(value = "${api.broker.exchange.notificationCommandExchange}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "${api.broker.key.notificationCommandKey}")
    )
    public void listen(@Payload NotificationCommand command) {
        NotificationModel notificationModel = mapper.toNotificationModel(command);
        notificationService.save(notificationModel);
    }
}
