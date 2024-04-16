package com.lsalmeida.course.consumer;

import com.lsalmeida.course.enums.ActionType;
import com.lsalmeida.course.mapper.UserMapper;
import com.lsalmeida.course.model.UserModel;
import com.lsalmeida.course.model.dto.UserEventDto;
import com.lsalmeida.course.service.UserService;
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
public class UserEventConsumer {

    private final UserMapper userMapper;
    private final UserService userService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    value = "${api.broker.queue.userEventQueue.name}",
                    durable = "true"
            ),
            exchange = @Exchange(
                    value = "${api.broker.exchange.userEvent}",
                    type = ExchangeTypes.FANOUT,
                    ignoreDeclarationExceptions = "true"
            )
    ))
    public void listenUserEvent(@Payload UserEventDto dto) {
        UserModel userModel = userMapper.fromUserEventDto(dto);
        switch (ActionType.valueOf(dto.getActionType())) {
            case CREATE -> userService.save(userModel);
        }
    }
}
