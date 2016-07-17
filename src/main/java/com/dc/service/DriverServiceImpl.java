package com.dc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.model.Driver;
import com.dc.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	@Override
	public void save(Driver driver) {
		driverRepository.save(driver);
	}

}
