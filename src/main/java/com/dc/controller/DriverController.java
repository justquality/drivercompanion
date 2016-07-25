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
import com.dc.model.User;
import com.dc.service.DriverService;
import com.dc.service.TripService;
import com.dc.validator.DriverValidator;

@Controller
public class DriverController {
	
	@Autowired private DriverService driverService;
	@Autowired private TripService tripService;
	@Autowired private DriverValidator driverValidator;
	
	@RequestMapping(value = "/my-driver", method = RequestMethod.GET)
	public String myDriver(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Driver driver = driverService.findByUsername(username);
		model.addAttribute("driver", driver);
		model.addAttribute("trips", tripService.findByDriver(driver));
		
		return "my-driver";
	}
	
	@RequestMapping(value = "/my-driver", method = RequestMethod.POST)
    public String myDriver(@ModelAttribute("driver") Driver driver, BindingResult bindingResult, Model model) {
		driverValidator.validate(driver, bindingResult);
    	
    	if (bindingResult.hasErrors())
            return "my-driver";
    	
    	Driver updateDriver = driverService.findByUsername(driver.getUser().getUsername());
    	updateDriver.setExperience(driver.getExperience());
    	
    	User updateUser = updateDriver.getUser();
    	updateUser.setFirstName(driver.getUser().getFirstName());
    	updateUser.setLastName(driver.getUser().getLastName());
    	updateUser.setEmail(driver.getUser().getEmail());
    	updateUser.setPasswordConfirm(driver.getUser().getPasswordConfirm());
    	
    	driverService.save(updateDriver);
    	
    	return "redirect:/my-driver";
    }
	
	@RequestMapping(value = "/driver-{username}", method = RequestMethod.GET)
	public String driverId(@PathVariable("username") String username, Model model) {
		if (SecurityContextHolder.getContext().getAuthentication().getName().equals(username))
			return "redirect:/my-driver";
		
		Driver driver = driverService.findByUsername(username);
		model.addAttribute("driver", driver);
		model.addAttribute("trips", tripService.findByDriver(driver));
		
		return "driver-page";
	}
	
}
