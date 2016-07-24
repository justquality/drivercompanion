package com.dc.service;

import com.dc.model.Driver;
import com.dc.model.User;

public interface DriverService {
	void save(Driver driver);
	Driver findOne(Long id);
	Driver findByUser(User user);
	Driver findByUsername(String username);
}
