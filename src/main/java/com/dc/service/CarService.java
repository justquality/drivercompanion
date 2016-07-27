package com.dc.service;

import java.util.List;

import com.dc.model.Car;

public interface CarService {
	void save(Car car);
	Car findOne(Long id);
	List<Car> findAll();
	Car contains(Car car);
}
