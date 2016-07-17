package com.dc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.model.Car;
import com.dc.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Override
	public void save(Car car) {
		carRepository.save(car);
	}

}
