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
import com.dc.model.Driver;
import com.dc.model.User;
import com.dc.service.CompanionService;
import com.dc.service.DriverService;
import com.dc.service.SecurityService;
import com.dc.service.UserService;
import com.dc.validator.UserValidator;

@Controller
public class UserController {
	
    @Autowired private UserService userService;
    @Autowired private SecurityService securityService;
    @Autowired private DriverService driverService;
	@Autowired private CompanionService companionService;
    @Autowired private UserValidator userValidator;
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors())
            return "registration";

        if (userForm.getUserType().equals("driver")) 
    		userService.saveDriverUser(userForm);
        else if (userForm.getUserType().equals("companion")) 
    		userService.saveCompanionUser(userForm);
        
        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        
        return "login";
    }
    
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
