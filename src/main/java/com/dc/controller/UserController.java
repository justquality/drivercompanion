package com.dc.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.dc.service.SecurityService;
import com.dc.service.UserService;
import com.dc.validator.UserValidator;

@Controller
public class UserController {
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DriverRepository driverRepository;
    
    @Autowired
    private CompanionRepository companionRepository;
    
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

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
        roles.add(roleRepository.findOne((long) 4));	// ROLE_USER
        if (userForm.getUserType().equals("driver")) {
    		roles.add(roleRepository.findOne((long) 2));	// ROLE_DRIVER
    		userForm.setRoles(roles);
    		
    		Driver driver = new Driver();
    		driver.setUser(userForm);
    		driverRepository.save(driver);
    	} else if (userForm.getUserType().equals("companion")) {
    		roles.add(roleRepository.findOne((long) 3));	// ROLE_COMPANION
    		userForm.setRoles(roles);
    		
    		Companion companion = new Companion();
    		companion.setUser(userForm);
    		companionRepository.save(companion);
    	}
        
        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }
    
}
