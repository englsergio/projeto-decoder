package com.lsalmeida.authuser.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lsalmeida.authuser.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    UUID userId;
    String fullName;
    String username;
    @JsonIgnore
    String password;
    String email;
    Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(UserModel userModel) {
        List<SimpleGrantedAuthority> authorities = userModel.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .toList();
        return new UserDetailsImpl(
                userModel.getUserId(),
                userModel.getFullName(),
                userModel.getUsername(),
                userModel.getPassword(),
                userModel.getEmail(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
