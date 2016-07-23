package com.dc.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Driver;
import com.dc.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
	Set<Trip> findByDriver(Driver driver);
}
