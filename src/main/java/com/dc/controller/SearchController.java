package com.dc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dc.model.Driver;
import com.dc.model.Trip;
import com.dc.model.TripForm;
import com.dc.service.DriverService;
import com.dc.service.TripService;

@Controller
public class SearchController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private TripService tripService;

	@RequestMapping(value = "/my-companion/search", method = RequestMethod.GET)
	public String searchForDrivers(Model model) {
		model.addAttribute("tripForm", new TripForm());
		return "search-for-drivers";
	}

	@RequestMapping(value = "/my-driver/search", method = RequestMethod.GET)
	public String searchForCompanions(Model model) {
		model.addAttribute("tripForm", new TripForm());
		return "search-for-companions";
	}

	@RequestMapping(value = "/my-companion/search", method = RequestMethod.POST)
	public String searchForDrivers(@ModelAttribute("tripForm") TripForm tripForm, Model model) {
		List<Driver> drivers = driverService.findByExperienceGreaterThanEqualAndExperienceLessThanEqual(
				tripForm.getMinExperience(), tripForm.getMaxExperience());

		List<Trip> trips = null;
		if (!tripForm.getDeparture().isEmpty()) {
			if (!tripForm.getArrival().isEmpty()) {
				System.out.println("NotNull arrival");
				trips = tripService.findByDepartureAndArrivalAndDateAndPriceLessThanEqualAndDriverIn(
						tripForm.getDeparture(), tripForm.getArrival(), tripForm.getDate(), tripForm.getMaxPrice(),
						drivers);
			}
			trips = tripService.findByDepartureAndDateAndPriceLessThanEqualAndDriverIn(tripForm.getDeparture(),
					tripForm.getDate(), tripForm.getMaxPrice(), drivers);
		} else if (!tripForm.getArrival().isEmpty()) {
			trips = tripService.findByArrivalAndDateAndPriceLessThanEqualAndDriverIn(tripForm.getArrival(),
					tripForm.getDate(), tripForm.getMaxPrice(), drivers);
		} else {
			trips = tripService.findByDateAndPriceLessThanEqualAndDriverIn(tripForm.getDate(), tripForm.getMaxPrice(),
					drivers);
		}

		model.addAttribute("trips", trips);

		return "search-for-drivers";
	}

	@RequestMapping(value = "/my-driver/search", method = RequestMethod.POST)
	public String searchForCompanions(@ModelAttribute("tripForm") TripForm tripForm, Model model) {
		List<Trip> trips = null;

		if (!tripForm.getDeparture().isEmpty()) {
			if (!tripForm.getArrival().isEmpty()) {
				System.out.println("NotNull arrival");
				trips = tripService.findByDepartureAndArrivalAndDateAndPriceLessThanEqualAndDriverIsNull(
						tripForm.getDeparture(), tripForm.getArrival(), tripForm.getDate(), tripForm.getMaxPrice());
			}
			trips = tripService.findByDepartureAndDateAndPriceLessThanEqualAndDriverIsNull(tripForm.getDeparture(),
					tripForm.getDate(), tripForm.getMaxPrice());
		} else if (!tripForm.getArrival().isEmpty()) {
			trips = tripService.findByArrivalAndDateAndPriceLessThanEqualAndDriverIsNull(tripForm.getArrival(),
					tripForm.getDate(), tripForm.getMaxPrice());
		} else {
			trips = tripService.findByDateAndPriceLessThanEqualAndDriverIsNull(tripForm.getDate(),
					tripForm.getMaxPrice());
		}

		model.addAttribute("trips", trips);
		
		return "search-for-companions";
	}

}
