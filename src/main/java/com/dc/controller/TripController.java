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

	@Autowired
	private TripService tripService;
	@Autowired
	private DriverService driverService;
	@Autowired
	private CompanionService companionService;
	@Autowired
	private TripValidator tripValidator;

	@RequestMapping(value = { "/my-driver/new-trip", "/my-companion/new-trip" }, method = RequestMethod.GET)
	public String newTrip(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Driver driver = driverService.findByUsername(username);
		if (null != driver)
			model.addAttribute("driver", driver);

		model.addAttribute("formHeader", "Create new trip");
		model.addAttribute("trip", new Trip());
		return "trip-page";
	}

	@RequestMapping(value = { "/my-driver/new-trip" }, method = RequestMethod.POST)
	public String newDriverTrip(@ModelAttribute("trip") Trip trip, BindingResult bindingResult, Model model) {
		model.addAttribute("formHeader", "Create new trip");
		tripValidator.validate(trip, bindingResult);

		if (bindingResult.hasErrors())
			return "trip-page";

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Driver driver = driverService.findByUsername(username);
		trip.setDriver(driver);
		tripService.save(trip);

		return "redirect:/my-driver";
	}

	@RequestMapping(value = { "/my-companion/new-trip" }, method = RequestMethod.POST)
	public String newCompanionTrip(@ModelAttribute("trip") Trip trip, BindingResult bindingResult, Model model) {
		model.addAttribute("formHeader", "Create new trip");
		tripValidator.validate(trip, bindingResult);

		if (bindingResult.hasErrors())
			return "trip-page";

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		@SuppressWarnings("serial")
		Set<Companion> companions = new HashSet<Companion>() {
			{
				add(companionService.findByUsername(username));
			}
		};
		trip.setCompanions(companions);
		tripService.save(trip);

		return "redirect:/my-companion";
	}

	@RequestMapping(value = { "/my-driver/edit-trip-{id}" }, method = RequestMethod.GET)
	public String editDriverTrip(@PathVariable("id") Long id, Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Driver driver = driverService.findByUsername(username);
		Trip trip = tripService.findByIdAndDriver(id, driver);
	
		if (null == trip)
			return "redirect:/my-driver";

		model.addAttribute("driver", driver);
		model.addAttribute("formHeader", "Edit trip");
		model.addAttribute("trip", trip);
		return "trip-page";
	}

	@RequestMapping(value = { "/my-companion/edit-trip-{id}" }, method = RequestMethod.GET)
	public String editCompanionTrip(@PathVariable("id") Long id, Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Companion companion = companionService.findByUsername(username);
		Trip trip = tripService.findByIdAndCompanions(id, companion);

		if (null == trip)
			return "redirect:/my-profile";

		model.addAttribute("formHeader", "Edit trip");
		model.addAttribute("trip", trip);
		return "trip-page";
	}

	@RequestMapping(value = { "/my-driver/edit-trip-{id}",
			"/my-companion/edit-trip-{id}" }, method = RequestMethod.POST)
	public String editTrip(@PathVariable("id") Long id, @ModelAttribute("trip") Trip trip,
			BindingResult bindingResult, Model model) {
		model.addAttribute("formHeader", "Edit trip");
		tripValidator.validate(trip, bindingResult);

		if (bindingResult.hasErrors())
			return "trip-page";

		Trip updateTrip = tripService.findOne(id);
		updateTrip.setDeparture(trip.getDeparture());
		updateTrip.setArrival(trip.getArrival());
		updateTrip.setPrice(trip.getPrice());
		updateTrip.setDate(trip.getDate());
		tripService.save(updateTrip);

		return "redirect:/my-profile";
	}

	@RequestMapping(value = { "/my-driver/delete-trip-{id}"}, method = RequestMethod.GET)
	public String deleteDriverTrip(@PathVariable("id") Long id, Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Driver driver = driverService.findByUsername(username);
		Trip trip = tripService.findByIdAndDriver(id, driver);
		
		if (null == trip || !trip.getCompanions().isEmpty())
			return "redirect:/my-profile";
		
		tripService.delete(id);
		 
		return "redirect:/my-driver";
	}
	
	@RequestMapping(value = { "/my-companion/delete-trip-{id}"}, method = RequestMethod.GET)
	public String deleteCompanionTrip(@PathVariable("id") Long id, Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Companion companion = companionService.findByUsername(username);
		Trip trip = tripService.findByIdAndCompanions(id, companion);
		
		if (null == trip || trip.getDriver() != null)
			return "redirect:/my-profile";
		
		tripService.delete(id);
		 
		return "redirect:/my-companion";
	}

}
