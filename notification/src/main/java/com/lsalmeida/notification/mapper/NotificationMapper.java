package com.lsalmeida.notification.mapper;

import com.lsalmeida.notification.enums.NotificationStatus;
import com.lsalmeida.notification.model.NotificationModel;
import com.lsalmeida.notification.model.dto.NotificationCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring",
        imports = NotificationStatus.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NotificationMapper {

    @Mapping(target = "notificationStatus", expression = "java(NotificationStatus.CREATED)")
    NotificationModel toNotificationModel(NotificationCommand command);

    @Named("localDataTimeNow")
    default LocalDateTime localDataTimeNow() {
        return LocalDateTime.now(ZoneId.of("UTC"));
    }

}
