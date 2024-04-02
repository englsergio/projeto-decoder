package com.lsalmeida.course.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.lsalmeida.course.enums.UserStatus;
import com.lsalmeida.course.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Transient;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        UUID userId,
        @Size(min = 4, max = 50)
        @NotBlank
        @JsonView
        String username,
        @Email
        @NotBlank
        String email,
        @Transient
        @Size(min = 6, max = 20)
        @NotBlank
        String password,
        @Transient
        @Size(min = 6, max = 20)
        @NotBlank
        String oldPassword,
        String fullName,
        UserStatus userStatus,
        UserType userType,
        String phoneNumber,
        String cpf,
        @NotBlank
        String imageUrl
) {
}
