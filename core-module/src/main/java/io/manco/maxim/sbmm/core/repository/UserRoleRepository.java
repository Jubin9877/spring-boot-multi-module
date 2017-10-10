package io.manco.maxim.sbmm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.core.domain.UserRoles;



public interface UserRoleRepository extends JpaRepository<UserRoles, Integer> {

  String role = "ROLE_ADMIN";
  
  UserRoles findByrole(String role);
}
