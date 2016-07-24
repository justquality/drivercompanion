package com.dc.validator;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dc.model.Driver;

@Component
public class DriverValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Driver.class.equals(aClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Driver driver = (Driver) object;
		
		ValidationUtils.rejectIfEmpty(errors, "user.firstName", "NotEmpty");
		if (driver.getUser().getFirstName().length() < 2 || driver.getUser().getFirstName().length() > 32)
			errors.rejectValue("user.firstName", "Size.userForm.firstName");

		ValidationUtils.rejectIfEmpty(errors, "user.lastName", "NotEmpty");
		if (driver.getUser().getLastName().length() < 2 || driver.getUser().getLastName().length() > 32)
			errors.rejectValue("user.lastName", "Size.userForm.lastName");
		
		ValidationUtils.rejectIfEmpty(errors, "user.passwordConfirm", "NotEmpty");
		if (!BCrypt.checkpw(driver.getUser().getPasswordConfirm(), driver.getUser().getPassword()))
			errors.rejectValue("user.passwordConfirm", "Check.userForm.passwordConfirm");
		
	}

}
