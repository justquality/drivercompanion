package com.dc.controller;

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
import com.dc.model.User;
import com.dc.service.CompanionService;
import com.dc.service.TripService;
import com.dc.validator.CompanionValidator;

@Controller
public class CompanionController {

	@Autowired private CompanionService companionService;
	@Autowired private TripService tripService;
	@Autowired private CompanionValidator companionValidator;
	
	@RequestMapping(value = "/my-companion", method = RequestMethod.GET)
	public String myCompanion(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Companion companion = companionService.findByUsername(username);
		model.addAttribute("companion", companion);
		model.addAttribute("openTrips", tripService.findByCompanionsAndClosed(companion, false));
		model.addAttribute("closedTrips", tripService.findByCompanionsAndClosed(companion, true));
		
		return "my-companion";
	}
	
	@RequestMapping(value = "/my-companion", method = RequestMethod.POST)
	public String myCompanion(@ModelAttribute("companion") Companion companion, BindingResult bindingResult, Model model) {
		companionValidator.validate(companion, bindingResult);
		
		if (bindingResult.hasErrors())
            return "my-companion";

		User user = companion.getUser();
		Companion updateCompanion = companionService.findByUser(user);
		
		User updateUser = updateCompanion.getUser();
    	updateUser.setFirstName(user.getFirstName());
    	updateUser.setLastName(user.getLastName());
    	updateUser.setEmail(user.getEmail());
    	updateUser.setPasswordConfirm(user.getPasswordConfirm());
		
    	companionService.save(updateCompanion);
		
		return "redirect:/my-companion";
	}

	@RequestMapping(value = "/companion-{username}", method = RequestMethod.GET)
	public String companionId(@PathVariable("username") String username, Model model) {
		if (SecurityContextHolder.getContext().getAuthentication().getName().equals(username))
			return "redirect:/my-profile";
		
		Companion companion = companionService.findByUsername(username);
		model.addAttribute("companion", companion);
		model.addAttribute("openTrips", tripService.findByCompanionsAndClosed(companion, false));
		model.addAttribute("closedTrips", tripService.findByCompanionsAndClosed(companion, true));
		
		return "companion-page";
	}
	
}
