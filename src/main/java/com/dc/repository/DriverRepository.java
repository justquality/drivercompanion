package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Driver;
import com.dc.model.User;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	Driver findByUser(User user);
}
