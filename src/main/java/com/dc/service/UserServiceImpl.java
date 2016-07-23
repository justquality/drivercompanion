package com.dc.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dc.model.Companion;
import com.dc.model.Driver;
import com.dc.model.Role;
import com.dc.model.User;
import com.dc.repository.RoleRepository;
import com.dc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private DriverService driverService;
    @Autowired private CompanionService companionService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
    @Override
	public void saveDriverUser(User user) {
    	Set<Role> roles = new HashSet<>();
    	roles.add(roleRepository.findOne((long) 2));	// Add ROLE_DRIVER
    	roles.add(roleRepository.findOne((long) 4));	// Add ROLE_USER
    	user.setRoles(roles);
    	
    	Driver driver = new Driver();
    	driver.setUser(user);
    	driverService.save(driver);
	}

	@Override
	public void saveCompanionUser(User user) {
		Set<Role> roles = new HashSet<>();
    	roles.add(roleRepository.findOne((long) 3));	// Add ROLE_COMPANION
    	roles.add(roleRepository.findOne((long) 4));	// Add ROLE_USER
    	user.setRoles(roles);
    	
    	Companion companion = new Companion();
    	companion.setUser(user);
    	companionService.save(companion);
	}

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
}
