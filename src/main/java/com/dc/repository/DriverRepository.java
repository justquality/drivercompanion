package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}
