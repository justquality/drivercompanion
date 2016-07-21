package com.dc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dc.model.Profile;
import com.dc.model.User;
import com.dc.repository.UserRepository;
import com.dc.service.UserService;
import com.dc.validator.ProfileValidator;

@Controller
public class CompanionController {

	@Autowired private UserService userService;
	@Autowired private UserRepository userRepository;
	@Autowired private ProfileValidator profileValidator;
	
	@RequestMapping(value = "/my-profile/edit-companion-{user.id}", method = RequestMethod.POST)
	public String editCompanion(@ModelAttribute("profile") Profile profile, BindingResult bindingResult, Model model) {
		// Update user's fields
    	User user = userService.findByUsername(profile.getUser().getUsername());
    	user.setFirstName(profile.getUser().getFirstName());
    	user.setLastName(profile.getUser().getLastName());
    	user.setEmail(profile.getUser().getEmail());
    	user.setPasswordConfirm(profile.getUser().getPasswordConfirm());
    	profile.setUser(user);
    	
    	profileValidator.validate(profile, bindingResult);
		
		if (bindingResult.hasErrors())
            return "redirect:/my-profile";
    	
    	// Using repository to avoid bcrypting already crypted password
    	userRepository.save(user);
		
		return "redirect:/my-profile";
	}
	
}
