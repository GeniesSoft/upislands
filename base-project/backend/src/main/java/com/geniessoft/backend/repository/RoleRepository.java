package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.Role;
import com.geniessoft.backend.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query
    Role findFirstByRoleNameEquals(@Param(value = "roleName") Roles roleName);
}
