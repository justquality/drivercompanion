package com.dc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dc.model.User;
import com.dc.service.UserService;

@Component
public class UserValidator implements Validator {
	
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) 
            errors.rejectValue("username", "Size.userForm.username");
        
        if (userService.findByUsername(user.getUsername()) != null)
            errors.rejectValue("username", "Duplicate.userForm.username");
        
        ValidationUtils.rejectIfEmpty(errors, "firstName", "NotEmpty");
        if (user.getFirstName().length() < 2 || user.getFirstName().length() > 32)
        	errors.rejectValue("firstName", "Size.userForm.firstName");
        
        ValidationUtils.rejectIfEmpty(errors, "lastName", "NotEmpty");
        if (user.getLastName().length() < 2 || user.getLastName().length() > 32)
        	errors.rejectValue("lastName", "Size.userForm.lastName");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
            errors.rejectValue("password", "Size.userForm.password");

        if (!user.getPasswordConfirm().equals(user.getPassword()))
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
    }
}
