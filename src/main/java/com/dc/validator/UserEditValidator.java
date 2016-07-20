package com.dc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dc.model.User;

@Component
public class UserEditValidator  implements Validator {
	
	@Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        
        ValidationUtils.rejectIfEmpty(errors, "firstName", "NotEmpty");
        if (user.getFirstName().length() < 2 || user.getFirstName().length() > 32)
        	errors.rejectValue("firstName", "Size.userForm.firstName");
        
        ValidationUtils.rejectIfEmpty(errors, "lastName", "NotEmpty");
        if (user.getLastName().length() < 2 || user.getLastName().length() > 32)
        	errors.rejectValue("lastName", "Size.userForm.lastName");
    }
}
