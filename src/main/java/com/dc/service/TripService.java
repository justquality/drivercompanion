package com.dc.service;

import java.util.List;
import java.util.Set;

import com.dc.model.Companion;
import com.dc.model.Driver;
import com.dc.model.Trip;

public interface TripService {
	void save(Trip trip);
	Trip findOne(Long id);
	Trip findByDriverAndCompanions(Driver driver, Companion companion);
	Set<Trip> findByDriver(Driver driver);
	Set<Trip> findByCompanions(Companion companion);
	Set<Trip> findByDriverAndClosed(Driver driver, Boolean bool);
	Set<Trip> findByCompanionsAndClosed(Companion companion, Boolean bool);
	List<Trip> findFirst10ByClosed(Boolean bool);
	List<Trip> findFirst20ByClosed(Boolean bool);
	void delete(Long id);
}
