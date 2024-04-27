package com.lsalmeida.authuser.repository;

import com.lsalmeida.authuser.enums.RoleType;
import com.lsalmeida.authuser.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
    Optional<RoleModel> findByRoleName(RoleType type);
}
