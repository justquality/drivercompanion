package com.dc.service;

import java.util.List;

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
	
	@Override
	public Car findOne(Long id) {
		return carRepository.findOne(id);
	}

	@Override
	public List<Car> findAll() {
		return carRepository.findAll();
	}

	@Override
	public Car contains(Car car) {
		List<Car> cars = findAll();

		for (Car c : cars)
			if (car.getBrand().getBrand().equals(c.getBrand().getBrand()) && car.getYear().equals(c.getYear())
					&& car.getType().equals(c.getType()) && car.getPlaces().equals(c.getPlaces()))
				return c;

		return null;
	}

}
