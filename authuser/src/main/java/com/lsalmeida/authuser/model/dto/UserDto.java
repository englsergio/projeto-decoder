package com.lsalmeida.authuser.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.lsalmeida.authuser.enums.UserStatus;
import com.lsalmeida.authuser.enums.UserType;
import com.lsalmeida.authuser.validation.UsernameConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto (
        UUID userId,
        @Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)
        @NotBlank(groups = UserView.RegistrationPost.class)
        @UsernameConstraint(groups = UserView.RegistrationPost.class)
        @JsonView(UserView.RegistrationPost.class)
        String username,
        @Email(groups = UserView.RegistrationPost.class)
        @NotBlank(groups = UserView.RegistrationPost.class)
        @JsonView(UserView.RegistrationPost.class)
        String email,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @Size(min = 6, max = 20, groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
        @NotBlank(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
        @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
        String password,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @Size(min = 6, max = 20, groups = UserView.PasswordPut.class)
        @NotBlank(groups = UserView.PasswordPut.class)
        @JsonView(UserView.PasswordPut.class)
        String oldPassword,
        @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
        String fullName,
        UserStatus userStatus,
        UserType userType,
        @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
        String phoneNumber,
        @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
        String cpf,
        @NotBlank(groups = UserView.ImagePut.class)
        @JsonView(UserView.ImagePut.class)
        String imageUrl
) {
        public interface UserView {
                interface RegistrationPost {}
                interface UserPut {}
                interface PasswordPut {}
                interface ImagePut {}
        }
}
