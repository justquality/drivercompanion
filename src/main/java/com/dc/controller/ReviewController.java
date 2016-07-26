package com.dc.controller;

import java.util.Date;
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
import com.dc.model.Review;
import com.dc.model.Trip;
import com.dc.model.User;
import com.dc.service.CompanionService;
import com.dc.service.DriverService;
import com.dc.service.ReviewService;
import com.dc.service.TripService;
import com.dc.service.UserService;
import com.dc.validator.ReviewValidator;

@Controller
public class ReviewController {

	@Autowired private UserService userService;
	@Autowired private DriverService driverService;
	@Autowired private CompanionService companionService;
	@Autowired private ReviewService reviewService;
	@Autowired private TripService tripService;
	@Autowired private ReviewValidator reviewValidator;

	@RequestMapping(value = "/user-{username}/add-review", method = RequestMethod.GET)
	public String addReview(@PathVariable("username") String username, Model model) {
		String current = SecurityContextHolder.getContext().getAuthentication().getName();
		if (current.equals(username))
			return "redirect:/my-profile";

		Driver driver = driverService.findByUsername(username);
		Companion companion = companionService.findByUsername(username);

		Set<Trip> trips = null;
		if (null != driver) {
			Companion author = companionService.findByUsername(current);
			trips = tripService.findByDriverAndCompanions(driver, author);
		} else if (null != companion) {
			Driver author = driverService.findByUsername(current);
			trips = tripService.findByDriverAndCompanions(author, companion);
		}

		if (!trips.isEmpty()) {
			model.addAttribute("review", new Review());
			model.addAttribute("author", userService.findByUsername(current));
		} else
			model.addAttribute("error", "You cannot write reviews about this user. No common trips found!");

		return "add-review";
	}

	@RequestMapping(value = "/user-{username}/add-review", method = RequestMethod.POST)
	public String addReview(@ModelAttribute("review") Review review, @PathVariable("username") String username,
			BindingResult bindingResult, Model model) {
		reviewValidator.validate(review, bindingResult);
		
		if (bindingResult.hasErrors())
			return "add-review";
		
		// Save review
		String current = SecurityContextHolder.getContext().getAuthentication().getName();
		User author = userService.findByUsername(current);
		User target = userService.findByUsername(username);
		Date date = new Date();
		review.setUserAuthor(author);
		review.setUserTarget(target);
		review.setDate(date);
		reviewService.save(review);
		
		// Recalculate user's rating based on review marks
		Set<Review> reviews = reviewService.findByUserTarget(target);
		Byte stars = (byte) 0;
		for (Review r : reviews)
			stars = (byte) (stars + r.getMark());
		
		// Set new rating and update driver/companion
		Float rating = stars.floatValue() / reviews.size();
		Driver driver = driverService.findByUser(target);
		Companion companion = companionService.findByUser(target);
		if (null != driver)
		{
			driver.setRating(rating);
			driverService.save(driver);
		}
		else if (null != companion)
		{
			companion.setRating(rating);
			companionService.save(companion);
		}

		return "redirect:/my-profile";
	}

}
