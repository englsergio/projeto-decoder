package com.lsalmeida.course.mapper;

import com.lsalmeida.course.model.UserModel;
import com.lsalmeida.course.model.dto.UserEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserModel fromUserEventDto(UserEventDto dto);

}
