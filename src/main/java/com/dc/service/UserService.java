package com.dc.service;

import com.dc.model.User;

public interface UserService {
	void save(User user);
	void saveDriverUser(User user);
	void saveCompanionUser(User user);
	User findByUsername(String username);
}
