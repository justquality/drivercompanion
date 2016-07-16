package com.dc.service;

import com.dc.model.User;

public interface UserService {
	void save(User user);
	User findByUsername(String username);
}
