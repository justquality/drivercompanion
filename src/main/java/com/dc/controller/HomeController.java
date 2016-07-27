package com.dc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dc.model.Driver;
import com.dc.model.Trip;
import com.dc.service.DriverService;
import com.dc.service.TripService;

@Controller
public class HomeController {
	
	@Autowired DriverService driverService;
	@Autowired TripService tripService;

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
		List<Trip> trips = tripService.findFirst10ByClosed(false);
		model.addAttribute("trips", trips);
		
        return "home";
    }
	
	@RequestMapping(value = { "/trips" }, method = RequestMethod.GET)
    public String trips(Model model) {
		List<Trip> trips = tripService.findFirst20ByClosed(false);
		model.addAttribute("trips", trips);
		
        return "trips";
    }
	
	@RequestMapping(value = { "/drivers" }, method = RequestMethod.GET)
    public String drivers(Model model) {
		List<Driver> drivers = driverService.findAll();
		model.addAttribute("drivers", drivers);
		
        return "drivers";
    }
	
	@RequestMapping(value = { "/about" }, method = RequestMethod.GET)
    public String about(Model model) {
        return "about";
    }
	
}
