package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	
}
