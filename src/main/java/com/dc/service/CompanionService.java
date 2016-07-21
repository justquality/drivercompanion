package com.dc.service;

import com.dc.model.Companion;
import com.dc.model.User;

public interface CompanionService {
	void save(Companion companion);
	Companion findByUser(User user);
}
