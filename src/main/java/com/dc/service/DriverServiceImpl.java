package com.dc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.model.Driver;
import com.dc.model.User;
import com.dc.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired private DriverRepository driverRepository;
	@Autowired private UserService userService;
	
	@Override
	public void save(Driver driver) {
		driverRepository.save(driver);
	}

	@Override
	public Driver findOne(Long id) {
		return driverRepository.findOne(id);
	}
	
	@Override
	public Driver findByUser(User user) {
		return driverRepository.findByUser(user);
	}

	@Override
	public Driver findByUsername(String username) {
		return findByUser(userService.findByUsername(username));
	}

}
