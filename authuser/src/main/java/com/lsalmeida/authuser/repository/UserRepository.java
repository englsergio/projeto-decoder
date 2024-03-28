package com.lsalmeida.authuser.repository;

import com.lsalmeida.authuser.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel> {
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);

}
