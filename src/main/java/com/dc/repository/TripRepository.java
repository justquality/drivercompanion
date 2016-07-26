package com.dc.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Companion;
import com.dc.model.Driver;
import com.dc.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
	Trip findByIdAndDriver(Long id, Driver driver);
	Trip findByIdAndCompanions(Long id, Companion companion);
	Set<Trip> findByDriver(Driver driver);
	Set<Trip> findByCompanions(Companion companion);
	Set<Trip> findByDriverAndCompanions(Driver driver, Companion companion);
}
