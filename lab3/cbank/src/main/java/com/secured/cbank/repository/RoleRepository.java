package com.secured.cbank.repository;

import com.secured.cbank.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByRoleId(Long roleId);
}
