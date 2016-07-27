package com.dc.service;

import java.util.List;

import com.dc.model.Brand;

public interface BrandService {
	void save(Brand brand);
	void save(List<Brand> brands);
	Brand findByBrand(String brand);
	List<Brand> findAll();
}
