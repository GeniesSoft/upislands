package com.geniessoft.backend.service;

import com.geniessoft.backend.model.Role;
import com.geniessoft.backend.model.Roles;

public interface RoleService {

    Role findRoleByName(Roles roleName);
    Role findRoleById(int roleId);
}
