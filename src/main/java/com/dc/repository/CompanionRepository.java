package com.dc.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Companion;
import com.dc.model.Trip;
import com.dc.model.User;

public interface CompanionRepository extends JpaRepository<Companion, Long> {
	Companion findByUser(User user);
	Set<Companion> findByTrips(Trip trip);
}
