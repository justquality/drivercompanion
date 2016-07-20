package com.dc.controller;

import java.util.HashSet;
import java.util.Set;

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
import com.dc.model.Role;
import com.dc.model.User;
import com.dc.repository.CompanionRepository;
import com.dc.repository.DriverRepository;
import com.dc.repository.RoleRepository;
import com.dc.repository.UserRepository;
import com.dc.service.SecurityService;
import com.dc.service.UserService;
import com.dc.validator.UserEditValidator;
import com.dc.validator.UserValidator;

@Controller
public class UserController {
	
    @Autowired private UserService 			userService;
    @Autowired private SecurityService 		securityService;
    @Autowired private UserValidator 		userValidator;
    @Autowired private UserEditValidator 	userEditValidator;
    @Autowired private UserRepository 		userRepository;
    @Autowired private RoleRepository 		roleRepository;
    @Autowired private DriverRepository 	driverRepository;
    @Autowired private CompanionRepository 	companionRepository;

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

        Set<Role> roles = new HashSet<>();
        Object client = null;
        
        roles.add(roleRepository.findOne((long) 4));		// Add ROLE_USER
        if (userForm.getUserType().equals("driver")) 
        {
    		roles.add(roleRepository.findOne((long) 2));	// Add ROLE_DRIVER
    		userForm.setRoles(roles);
    		client = new Driver();
    		((Driver) client).setUser(userForm);
    		driverRepository.save((Driver) client);
    	} 
        else if (userForm.getUserType().equals("companion")) 
    	{
    		roles.add(roleRepository.findOne((long) 3));	// Add ROLE_COMPANION
    		userForm.setRoles(roles);
    		client = new Companion();
    		((Companion) client).setUser(userForm);
    		companionRepository.save((Companion) client);
    	}	
        
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
    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }
    
    @RequestMapping(value = "/my-profile", method = RequestMethod.GET)
    public String myProfile(Model model) {
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	User user = userRepository.findByUsername(username);
    	Driver driver = driverRepository.findByUser(user);
    	Companion companion = companionRepository.findByUser(user);
    	
    	if (null != driver)
    		model.addAttribute("driver", driver);
    	else if (null != companion)
    		model.addAttribute("companion", companion);
    	else
    		return "redirect:/admin";
    	
    	model.addAttribute("user", user);
    	return "my-profile";
    }
    
    @RequestMapping(value = "/my-profile", method = RequestMethod.POST)
    public String editMyProfile(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
    	userEditValidator.validate(user, bindingResult);
    	
    	if (bindingResult.hasErrors())
            return "redirect:/my-profile";
    	
    	User updateUser = userService.findByUsername(user.getUsername());
    	updateUser.setFirstName(user.getFirstName());
    	updateUser.setLastName(user.getLastName());
    	updateUser.setEmail(user.getEmail());
    	userRepository.save(updateUser);	// save without bcrypting
    	
    	return "redirect:/my-profile";
    }
}
