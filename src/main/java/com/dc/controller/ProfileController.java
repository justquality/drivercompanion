package com.dc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dc.model.Companion;
import com.dc.model.Driver;
import com.dc.model.Profile;
import com.dc.model.User;
import com.dc.service.CompanionService;
import com.dc.service.DriverService;
import com.dc.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired private UserService userService;
	@Autowired private DriverService driverService;
    @Autowired private CompanionService companionService;
	
	@RequestMapping(value = "/my-profile", method = RequestMethod.GET)
    public String myProfile(Model model) {
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	User user = userService.findByUsername(username);
    	Driver driver = driverService.findByUser(user);
    	Companion companion = companionService.findByUser(user);
    	
    	model.addAttribute("user", user);
    	if (null != driver) 
    	{
    		model.addAttribute("driver", driver);
    		model.addAttribute("profile", new Profile(user, driver));
    	}
    	else if (null != companion) 
    	{
    		model.addAttribute("companion", companion);
    		model.addAttribute("profile", new Profile(user, companion));
    	}
    	else
    		return "redirect:/admin";
    	
    	return "my-profile";
    }
	
}
