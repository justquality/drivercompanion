package com.dc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dc.model.Companion;
import com.dc.model.Driver;
import com.dc.service.CompanionService;
import com.dc.service.DriverService;

@Controller
public class ProfileController {
	
	@Autowired private DriverService driverService;
	@Autowired private CompanionService companionService;
	
	@RequestMapping(value = "/my-profile", method = RequestMethod.GET)
    public String myProfile(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	Driver driver = driverService.findByUsername(username);
    	Companion companion = companionService.findByUsername(username);
    	
		if (null != driver)
			return "redirect:/my-driver";
		else if (null != companion)
			return "redirect:/my-companion";
    	return "redirect:/admin";
    }
	
}
