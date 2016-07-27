package com.dc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.model.Brand;
import com.dc.repository.BrandRepository;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public void save(Brand brand) {
		brandRepository.save(brand);
	}
	
	@Override
	public void save(List<Brand> brands) {
		brandRepository.save(brands);
	}
	
	@Override
	public Brand findByBrand(String brand) {
		return brandRepository.findByBrand(brand);
	}

	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

}
