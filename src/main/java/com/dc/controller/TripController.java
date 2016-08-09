package com.dc.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = { "/my-driver/new-trip", "/my-companion/new-trip" }, method = RequestMethod.GET)
	public String newTrip(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Driver driver = driverService.findByUsername(username);

		if (null != driver)
			model.addAttribute("driver", driver);

		model.addAttribute("trip", new Trip());
		return "new-trip";
	}

	@RequestMapping(value = { "/my-driver/new-trip" }, method = RequestMethod.POST)
	public String newDriverTrip(@ModelAttribute("trip") Trip trip, BindingResult bindingResult, Model model) {
		tripValidator.validate(trip, bindingResult);

		if (bindingResult.hasErrors())
			return "new-trip";

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Driver driver = driverService.findByUsername(username);
		trip.setDriver(driver);
		trip.setClosed(false);
		tripService.save(trip);

		return "redirect:/my-driver";
	}

	@RequestMapping(value = { "/my-companion/new-trip" }, method = RequestMethod.POST)
	public String newCompanionTrip(@ModelAttribute("trip") Trip trip, BindingResult bindingResult, Model model) {
		tripValidator.validate(trip, bindingResult);

		if (bindingResult.hasErrors())
			return "new-trip";

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		@SuppressWarnings("serial")
		Set<Companion> companions = new HashSet<Companion>() {
			{
				add(companionService.findByUsername(username));
			}
		};
		trip.setCompanions(companions);
		trip.setClosed(false);
		tripService.save(trip);

		return "redirect:/my-companion";
	}

	@RequestMapping(value = { "/my-driver/delete-trip-{id}",
			"/my-companion/delete-trip-{id}" }, method = RequestMethod.POST)
	public String deleteDriverTrip(@PathVariable("id") Long id, Model model) {
		tripService.delete(id);
		return "redirect:/my-driver";
	}

	@RequestMapping(value = { "/my-driver/close-trip-{id}" }, method = RequestMethod.POST)
	public String closeDriverTrip(@PathVariable("id") Long id, Model model) {
		Trip trip = tripService.findOne(id);
		trip.setClosed(true);
		tripService.save(trip);

		return "redirect:/my-driver";
	}

	@RequestMapping(value = { "/companion-{username}/become-driver-trip-{id}" }, method = RequestMethod.POST)
	public String becomeDriver(@PathVariable("id") Long id, Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Trip trip = tripService.findOne(id);
		trip.setDriver(driverService.findByUsername(username));
		tripService.save(trip);
		
		return "redirect:/my-driver";
	}
	
	@RequestMapping(value = { "/driver-{username}/become-companion-trip-{id}" }, method = RequestMethod.POST)
	public String becomeCompanion(@PathVariable("id") Long id, Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Trip trip = tripService.findOne(id);
		trip.getCompanions().add(companionService.findByUsername(username));
		tripService.save(trip);
		
		return "redirect:/my-companion";
	}

}
