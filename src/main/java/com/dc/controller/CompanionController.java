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
import com.dc.model.Profile;
import com.dc.model.User;
import com.dc.repository.UserRepository;
import com.dc.service.CompanionService;
import com.dc.service.UserService;
import com.dc.validator.ProfileValidator;

@Controller
public class CompanionController {

	@Autowired private UserService userService;
	@Autowired private CompanionService companionService;
	@Autowired private UserRepository userRepository;
	@Autowired private ProfileValidator profileValidator;
	
	@RequestMapping(value = "/my-companion", method = RequestMethod.GET)
	public String myCompanion(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.findByUsername(username);
		Companion companion = companionService.findByUsername(username);
		
		model.addAttribute("user", user);
		model.addAttribute("companion", companion);
		model.addAttribute("profile", new Profile(user, companion));
		
		return "my-companion";
	}
	
	@RequestMapping(value = "/my-companion", method = RequestMethod.POST)
	public String myCompanion(@ModelAttribute("profile") Profile profile, BindingResult bindingResult, Model model) {
		profileValidator.validate(profile, bindingResult);
		
		if (bindingResult.hasErrors())
            return "my-companion";

    	User user = userService.findByUsername(profile.getUser().getUsername());
    	user.setFirstName(profile.getUser().getFirstName());
    	user.setLastName(profile.getUser().getLastName());
    	user.setEmail(profile.getUser().getEmail());
    	user.setPasswordConfirm(profile.getUser().getPasswordConfirm());
    	
    	if (profile.getNewPassword().length() != 0 && profile.getNewPassword() != null) 
    	{	// Encode new password
    		user.setPassword(profile.getNewPassword());
    		userService.save(user);
    	} 
    	else
    		userRepository.save(user); // Save without encoding
		
		return "redirect:/my-companion";
	}
	
}
