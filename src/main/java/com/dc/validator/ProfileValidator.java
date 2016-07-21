package com.dc.validator;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dc.model.Profile;

@Component
public class ProfileValidator implements Validator {
	
	@Override
    public boolean supports(Class<?> aClass) {
        return Profile.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
    	Profile profile = (Profile) object;
    	
    	ValidationUtils.rejectIfEmpty(errors, "user.firstName", "NotEmpty");
        if (profile.getUser().getFirstName().length() < 2 || profile.getUser().getFirstName().length() > 32)
        	errors.rejectValue("user.firstName", "Size.userForm.firstName");
        
        ValidationUtils.rejectIfEmpty(errors, "user.lastName", "NotEmpty");
        if (profile.getUser().getLastName().length() < 2 || profile.getUser().getLastName().length() > 32)
        	errors.rejectValue("user.lastName", "Size.userForm.lastName");
        
        if (!BCrypt.checkpw(profile.getUser().getPasswordConfirm(), profile.getUser().getPassword()))
            errors.rejectValue("user.passwordConfirm", "Diff.userForm.passwordConfirm");
    }
}
