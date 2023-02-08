package com.bezkoder.springjwt.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.account.models.RoleEnum;
import com.bezkoder.springjwt.account.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByRoleEnum(RoleEnum name);
}
