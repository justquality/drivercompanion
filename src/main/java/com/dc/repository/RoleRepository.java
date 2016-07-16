package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
