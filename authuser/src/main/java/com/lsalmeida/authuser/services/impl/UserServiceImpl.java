package com.lsalmeida.authuser.services.impl;

import com.lsalmeida.authuser.enums.ActionType;
import com.lsalmeida.authuser.exception.IncorrectUserPasswordException;
import com.lsalmeida.authuser.exception.UserAlreadyRegisteredException;
import com.lsalmeida.authuser.exception.UserNotFoundException;
import com.lsalmeida.authuser.mapper.UserMapper;
import com.lsalmeida.authuser.model.UserModel;
import com.lsalmeida.authuser.model.dto.UserDto;
import com.lsalmeida.authuser.publisher.UserEventPublisher;
import com.lsalmeida.authuser.repository.UserRepository;
import com.lsalmeida.authuser.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final UserEventPublisher userEventPublisher;

    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);
    }

    @Override
    public UserModel findById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
    }

    @Override
    public UserDto save(UserDto dto) {
        UserModel userModel = mapper.fromDto(dto);
        UserModel savedUser = userRepository.save(userModel);
        return mapper.toDto(savedUser);
    }

    @Override
    public UserDto save(UserModel userModel) {
        UserModel savedUser = userRepository.save(userModel);
        return mapper.toDto(savedUser);
    }

    @Transactional
    @Override
    public void delete(UserModel user) {
        userRepository.delete(user);
    }

    @Override
    public void existsByUsername(String username) {
        if (userRepository.findByUsername(username).isPresent())
            throw new UserAlreadyRegisteredException("Existe um usuário com esse nome já cadastrado.");
    }

    @Override
    public void existsByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new UserAlreadyRegisteredException("Existe um usuário com este email já cadastrado.");
    }

    @Override
    public UserDto updateUser(UUID id, UserDto dto) {
        UserModel user = userRepository.findById(id)
                .map(u -> mapper.updateUser(u, dto))
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        UserModel saved = userRepository.save(user);
        return mapper.toDto(saved);
    }

    @Override
    public UserDto updateImage(UUID id, UserDto dto) {
        UserModel user = userRepository.findById(id)
                .map(u -> u.withImageUrl(dto.imageUrl()))
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
        UserModel updated = userRepository.save(user);
        return mapper.toDto(updated);
    }

    @Transactional
    @Override
    public UserDto saveUserAndPublish(UserDto dto) {
        UserModel userModel = mapper.fromDto(dto);
        UserModel savedUser = userRepository.save(userModel);
        userEventPublisher.publishUserEvent(mapper.toUserEventDto(savedUser), ActionType.CREATE);
        return mapper.toDto(savedUser);
    }

    @Transactional
    @Override
    public void deleteUser(UserModel userModel) {
        delete(userModel);
        userEventPublisher.publishUserEvent(mapper.toUserEventDto(userModel), ActionType.DELETE);
    }

    @Transactional
    @Override
    public UserModel updateUser(UserModel userModel) {
        userModel = userRepository.save(userModel);
        userEventPublisher.publishUserEvent(mapper.toUserEventDto(userModel),ActionType.UPDATE);
        return userModel;
    }

    @Override
    public void updatePassword(UUID id, UserDto dto) {
        UserModel user = userRepository.findById(id)
                .map(u -> checksPassword(u, dto))
                .map(u -> mapper.updatePassword(u, dto))
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
        userRepository.save(user);
    }

    private UserModel checksPassword(UserModel user, UserDto dto) {
        if (!user.getPassword().equals(dto.oldPassword()))
            throw new IncorrectUserPasswordException("Senha incorreta!");
        return user;
    }

}