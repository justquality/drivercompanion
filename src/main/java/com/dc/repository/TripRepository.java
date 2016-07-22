package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

}
