package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
	Brand findByBrand(String brand);
}
