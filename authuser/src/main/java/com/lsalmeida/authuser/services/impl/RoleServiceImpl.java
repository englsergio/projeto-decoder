package com.lsalmeida.authuser.services.impl;

import com.lsalmeida.authuser.enums.RoleType;
import com.lsalmeida.authuser.exception.RoleNotFoundException;
import com.lsalmeida.authuser.model.RoleModel;
import com.lsalmeida.authuser.repository.RoleRepository;
import com.lsalmeida.authuser.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

   private final RoleRepository roleRepository;

   @Override
   public RoleModel findByRoleName(RoleType type) {
      return roleRepository.findByRoleName(type).orElseThrow(RoleNotFoundException::new);
   }

}
