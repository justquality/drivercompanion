package com.dc.service;

import java.util.List;
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
	public Trip findOne(Long id) {
		return tripRepository.findOne(id);
	}
	
	@Override
	public Trip findByDriverAndCompanions(Driver driver, Companion companion) {
		return tripRepository.findByDriverAndCompanions(driver, companion);
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
	public Set<Trip> findByDriverAndClosed(Driver driver, Boolean bool) {
		return tripRepository.findByDriverAndClosed(driver, bool);
	}

	@Override
	public Set<Trip> findByCompanionsAndClosed(Companion companion, Boolean bool) {
		return tripRepository.findByCompanionsAndClosed(companion, bool);
	}
	
	@Override
	public List<Trip> findFirst10ByClosed(Boolean bool) {
		return tripRepository.findFirst10ByClosed(bool);
	}
	
	@Override
	public List<Trip> findFirst20ByClosed(Boolean bool) {
		return tripRepository.findFirst20ByClosed(bool);
	}
	
	@Override
	public void delete(Long id) {
		tripRepository.delete(id);
	}

}
