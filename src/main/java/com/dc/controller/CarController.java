package com.dc.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dc.model.Brand;
import com.dc.model.Car;
import com.dc.model.Driver;
import com.dc.service.BrandService;
import com.dc.service.CarService;
import com.dc.service.DriverService;

@Controller
public class CarController {
	
	@Autowired private CarService carService;
	@Autowired private DriverService driverService;
	@Autowired private BrandService brandService;
	
	@RequestMapping(value = { "/my-driver/add-car" }, method = RequestMethod.GET)
    public String addCar(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Driver driver = driverService.findByUsername(username);
		Car car = driver.getCar();
		if (null != car)
			return "redirect:/my-driver";
		
		model.addAttribute("carTitle", "Add Car");
		model.addAttribute("car", new Car());
		
		List<Brand> brands = brandService.findAll();
		List<String> carBrands = new LinkedList<>();
		for (Brand b : brands)
			carBrands.add(b.getBrand());
		
		model.addAttribute("carBrands", carBrands);
		
        return "manage-car";
    }
	
	@RequestMapping(value = { "/my-driver/edit-car" }, method = RequestMethod.GET)
    public String editCar(Model model) {
		model.addAttribute("carTitle", "Edit Car");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Driver driver = driverService.findByUsername(username);
		Car car = driver.getCar();
		if (null == car)
			return "redirect:/my-driver";
		
		model.addAttribute("car", car);
		
		List<Brand> brands = brandService.findAll();
		List<String> carBrands = new LinkedList<>();
		for (Brand b : brands)
			carBrands.add(b.getBrand());
		
		model.addAttribute("carBrands", carBrands);
		
		return "manage-car";
	}
	
	@RequestMapping(value = { "/my-driver/add-car", "/my-driver/edit-car" }, method = RequestMethod.POST)
    public String addCar(@ModelAttribute("car") Car car, Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Driver driver = driverService.findByUsername(username);
		
		Car existing = carService.contains(car);
		if (null != existing) 
			driver.setCar(existing); 
		else {
			System.out.println("Car doesn't exist!");
			car.setBrand(brandService.findByBrand(car.getBrand().getBrand()));
			carService.save(car);
			driver.setCar(car);
		}
		driverService.save(driver);
		
        return "redirect:/my-driver";
    }
	
}
