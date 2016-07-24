package com.dc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.model.Companion;
import com.dc.model.User;
import com.dc.repository.CompanionRepository;

@Service
public class CompanionServiceImpl implements CompanionService {

	@Autowired private CompanionRepository companionRepository;
	@Autowired private UserService userService;
	
	@Override
	public void save(Companion companion) {
		companionRepository.save(companion);
	}

	@Override
	public Companion findOne(Long id) {
		return companionRepository.findOne(id);
	}
	
	@Override
	public Companion findByUser(User user) {
		return companionRepository.findByUser(user);
	}

	@Override
	public Companion findByUsername(String username) {
		return findByUser(userService.findByUsername(username));
	}

}
