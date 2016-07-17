package com.dc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.model.Companion;
import com.dc.repository.CompanionRepository;

@Service
public class CompanionServiceImpl implements CompanionService {

	@Autowired
	private CompanionRepository companionRepository;
	
	@Override
	public void save(Companion companion) {
		companionRepository.save(companion);
	}

}
