package com.lsalmeida.authuser.services;

import com.lsalmeida.authuser.enums.RoleType;
import com.lsalmeida.authuser.model.RoleModel;

public interface RoleService {
    RoleModel findByRoleName(RoleType type);
}
