package com.dc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dc.model.Driver;
import com.dc.model.User;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	Driver findByUser(User user);
	@Query(value = "select * from DRIVERS order by RATING desc limit 10", nativeQuery = true)
	List<Driver> findTop10ByRating();
}
