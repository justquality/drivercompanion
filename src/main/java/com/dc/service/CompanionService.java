package com.dc.service;

import com.dc.model.Companion;
import com.dc.model.User;

public interface CompanionService {
	void save(Companion companion);
	Companion findOne(Long id);
	Companion findByUser(User user);
	Companion findByUsername(String username);
}
