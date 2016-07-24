package com.dc.service;

import java.util.Set;

import com.dc.model.Companion;
import com.dc.model.Driver;
import com.dc.model.Trip;

public interface TripService {
	void save(Trip trip);
	Set<Trip> findByDriver(Driver driver);
	Set<Trip> findByCompanions(Companion companion);
}
