package com.dc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		model.addAttribute("trips", tripService.findByCompanions(companion));
		
		return "my-companion";
	}
	
	@RequestMapping(value = "/my-companion", method = RequestMethod.POST)
	public String myCompanion(@ModelAttribute("companion") Companion companion, BindingResult bindingResult, Model model) {
		companionValidator.validate(companion, bindingResult);
		
		if (bindingResult.hasErrors())
            return "my-companion";

		Companion updateCompanion = companionService.findByUsername(companion.getUser().getUsername());
		
		User updateUser = updateCompanion.getUser();
    	updateUser.setFirstName(companion.getUser().getFirstName());
    	updateUser.setLastName(companion.getUser().getLastName());
    	updateUser.setEmail(companion.getUser().getEmail());
    	updateUser.setPasswordConfirm(companion.getUser().getPasswordConfirm());
		
    	companionService.save(updateCompanion);
		
		return "redirect:/my-companion";
	}
	
}
