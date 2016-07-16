package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
