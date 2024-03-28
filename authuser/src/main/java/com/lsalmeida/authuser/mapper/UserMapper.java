package com.lsalmeida.authuser.mapper;

import com.lsalmeida.authuser.enums.UserStatus;
import com.lsalmeida.authuser.enums.UserType;
import com.lsalmeida.authuser.model.UserModel;
import com.lsalmeida.authuser.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring",
        imports = {UserStatus.class, UserType.class, LocalDateTime.class, ZoneId.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDto toDto(UserModel userModel);

    @Mapping(target = "userStatus", expression = "java(UserStatus.ACTIVE)")
    @Mapping(target = "userType", expression = "java(UserType.STUDENT)")
    @Mapping(target = "creationDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    UserModel fromDto(UserDto userDto);

    default LocalDateTime localDataTimeNow() {
        return LocalDateTime.now(ZoneId.of("UTC"));
    }

    default UserModel updateUser(UserModel user, UserDto dto) {
        user.setFullName(dto.fullName());
        user.setPhoneNumber(dto.phoneNumber());
        user.setCpf(dto.cpf());
        user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return user;
    }

    default UserModel updatePassword(UserModel user, UserDto dto) {
        user.setPassword(dto.password());
        user.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return user;
    }

}
