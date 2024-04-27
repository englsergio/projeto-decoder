package com.lsalmeida.authuser.config.security;

import com.lsalmeida.authuser.exception.UserNotFoundException;
import com.lsalmeida.authuser.model.UserModel;
import com.lsalmeida.authuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return UserDetailsImpl.build(userModel);
    }

}
