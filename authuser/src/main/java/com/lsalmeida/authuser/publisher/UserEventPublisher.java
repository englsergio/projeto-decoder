package com.lsalmeida.authuser.publisher;

import com.lsalmeida.authuser.enums.ActionType;
import com.lsalmeida.authuser.model.dto.UserEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    @Value("${api.broker.exchange.userEvent}")
    private String exchangeUserEvent;

    public void publishUserEvent(UserEventDto dto, ActionType type) {
        dto.setActionType(ActionType.CREATE.name());
        rabbitTemplate.convertAndSend(exchangeUserEvent, "", dto);
    }
}
