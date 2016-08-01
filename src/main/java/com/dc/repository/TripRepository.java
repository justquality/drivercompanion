package com.dc.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Companion;
import com.dc.model.Driver;
import com.dc.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
	Trip findByDriverAndCompanions(Driver driver, Companion companion);
	Set<Trip> findByDriver(Driver driver);
	Set<Trip> findByCompanions(Companion companion);
	Set<Trip> findByDriverAndClosed(Driver driver, Boolean bool);
	Set<Trip> findByCompanionsAndClosed(Companion companion, Boolean bool);
	List<Trip> findFirst10ByClosed(Boolean bool);
	List<Trip> findFirst20ByClosed(Boolean bool);
	List<Trip> findByDateAndPriceLessThanEqualAndDriverIn(Date date, Double price, List<Driver> drivers);
	List<Trip> findByDepartureAndDateAndPriceLessThanEqualAndDriverIn(String departure, Date date, Double price,
			List<Driver> drivers);
	List<Trip> findByArrivalAndDateAndPriceLessThanEqualAndDriverIn(String arrival, Date date, Double price,
			List<Driver> drivers);
	List<Trip> findByDepartureAndArrivalAndDateAndPriceLessThanEqualAndDriverIn(String departure, String arrival,
			Date date, Double price, List<Driver> drivers);
	List<Trip> findByDateAndPriceLessThanEqualAndDriverIsNull(Date date, Double price);
	List<Trip> findByDepartureAndDateAndPriceLessThanEqualAndDriverIsNull(String departure, Date date, Double price);
	List<Trip> findByArrivalAndDateAndPriceLessThanEqualAndDriverIsNull(String arrival, Date date, Double price);
	List<Trip> findByDepartureAndArrivalAndDateAndPriceLessThanEqualAndDriverIsNull(String departure, String arrival, 
			Date date, Double price);
}
