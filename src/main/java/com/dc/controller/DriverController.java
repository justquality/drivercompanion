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

import com.dc.model.Driver;
import com.dc.model.Profile;
import com.dc.model.User;
import com.dc.repository.UserRepository;
import com.dc.service.DriverService;
import com.dc.service.TripService;
import com.dc.service.UserService;
import com.dc.validator.ProfileValidator;

@Controller
public class DriverController {
	
	@Autowired private UserService userService;
	@Autowired private UserRepository userRepository;
	@Autowired private DriverService driverService;
	@Autowired private TripService tripService;
	@Autowired private ProfileValidator profileValidator;
	
	@RequestMapping(value = "/my-driver", method = RequestMethod.GET)
	public String myDriver(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
    	User user = userService.findByUsername(username);
		Driver driver = driverService.findByUsername(username);
		
		model.addAttribute("user", user);
		model.addAttribute("driver", driver);
		model.addAttribute("profile", new Profile(user, driver));
		model.addAttribute("trips", tripService.findByDriver(driver));
		
		return "my-driver";
	}
	
	@RequestMapping(value = "/my-driver", method = RequestMethod.POST)
    public String myDriver(@ModelAttribute("profile") Profile profile, BindingResult bindingResult, Model model) {
		profileValidator.validate(profile, bindingResult);
    	
    	if (bindingResult.hasErrors())
            return "my-driver";
    	
    	User user = userService.findByUsername(profile.getUser().getUsername());
    	user.setFirstName(profile.getUser().getFirstName());
    	user.setLastName(profile.getUser().getLastName());
    	user.setEmail(profile.getUser().getEmail());
    	user.setPasswordConfirm(profile.getUser().getPasswordConfirm());
    	
    	Driver driver = driverService.findByUser(user);
		driver.setExperience(profile.getDriver().getExperience());
		driverService.save(driver);
    	
		if (profile.getNewPassword().length() != 0 && profile.getNewPassword() != null) 
    	{	// Encode new password
    		user.setPassword(profile.getNewPassword());
    		userService.save(user);
    	} 
    	else
    		userRepository.save(user); // Save without encoding
    	
    	return "redirect:/my-driver";
    }
	
	@RequestMapping(value = "/driver-{trip.driver.id}", method = RequestMethod.GET)
	public String driverId(@PathVariable("trip.driver.id") String id, Model model) {
		return "driver-" + id;
	}
	
}
