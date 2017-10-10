package io.manco.maxim.sbmm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.domain.UserRoles;



public interface UserRoleRepository extends JpaRepository<UserRoles, Integer> {

  String role = "ROLE_ADMIN";
  
  UserRoles findByrole(String role);
}
