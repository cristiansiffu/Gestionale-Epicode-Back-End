package com.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.model.auth.Role;
import com.crm.model.auth.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(Roles role);

}
