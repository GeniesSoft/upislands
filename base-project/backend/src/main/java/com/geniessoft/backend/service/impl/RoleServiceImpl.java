package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.Role;
import com.geniessoft.backend.model.Roles;
import com.geniessoft.backend.repository.RoleRepository;
import com.geniessoft.backend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(Roles roleName) {
        return roleRepository.findFirstByRoleNameEquals(roleName);
    }

    @Override
    public Role findRoleById(int roleId) {
        return roleRepository.findFirstByRoleId(roleId);
    }
}
