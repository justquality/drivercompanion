package com.dc.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dc.model.Companion;
import com.dc.model.Driver;
import com.dc.model.Trip;
import com.dc.service.CompanionService;
import com.dc.service.DriverService;
import com.dc.service.TripService;
import com.dc.validator.TripValidator;

@Controller
public class TripController {
	
	@Autowired private TripService tripService;
	@Autowired private DriverService driverService;
	@Autowired private CompanionService companionService;
	@Autowired private TripValidator tripValidator; 

	@RequestMapping(value = {"/my-driver/new-trip", "/my-companion/new-trip"}, method = RequestMethod.GET)
	public String newDriverTrip(Model model) {
		model.addAttribute("trip", new Trip());
		return "new-trip";
	}
	
//	@RequestMapping(value = {"/my-companion/new-trip"}, method = RequestMethod.GET)
//	public String newCompanionTrip(Model model) {
//		model.addAttribute("trip", new Trip());
//		return "my-companion/new-trip";
//	}
	
	@RequestMapping(value = {"/my-driver/new-trip"}, method = RequestMethod.POST)
	public String newDriverTrip(@ModelAttribute("trip") Trip trip, BindingResult bindingResult, Model model) {
		tripValidator.validate(trip, bindingResult);
		
		if (bindingResult.hasErrors())
			return "new-trip";
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Driver driver = driverService.findByUsername(username);
		trip.setDriver(driver);
		tripService.save(trip);
		
		return "redirect:/my-driver";
	}
	
	@RequestMapping(value = {"/my-companion/new-trip"}, method = RequestMethod.POST)
	public String newCompanionTrip(@ModelAttribute("trip") Trip trip, BindingResult bindingResult, Model model) {
		tripValidator.validate(trip, bindingResult);
		
		if (bindingResult.hasErrors())
			return "new-trip";
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		@SuppressWarnings("serial")
		Set<Companion> companions = new HashSet<Companion>() {{
			add(companionService.findByUsername(username));
		}};
		trip.setCompanions(companions);
		tripService.save(trip);
		
		return "redirect:/my-companion";
	}
	
}
