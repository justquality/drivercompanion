package com.dc.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.model.Companion;
import com.dc.model.Driver;
import com.dc.model.Trip;
import com.dc.repository.TripRepository;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private TripRepository tripRepository;
	
	@Override
	public void save(Trip trip) {
		tripRepository.save(trip);
	}

	@Override
	public Set<Trip> findByDriver(Driver driver) {
		return tripRepository.findByDriver(driver);
	}

	@Override
	public Set<Trip> findByCompanions(Companion companion) {
		return tripRepository.findByCompanions(companion);
	}

	@Override
	public Set<Trip> findByDriverAndCompanions(Driver driver, Companion companion) {
		return tripRepository.findByDriverAndCompanions(driver, companion);
	}

}
