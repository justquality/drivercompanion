package com.dc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.firstName", "NotEmpty");
		if (driver.getUser().getFirstName().length() < 2 || driver.getUser().getFirstName().length() > 32)
			errors.rejectValue("user.firstName", "Size.userForm.firstName");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.lastName", "NotEmpty");
		if (driver.getUser().getLastName().length() < 2 || driver.getUser().getLastName().length() > 32)
			errors.rejectValue("user.lastName", "Size.userForm.lastName");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.phone", "NotEmpty");
        Pattern pattern = Pattern.compile("\\b[0][67][0-9]{1}[-][0-9]{3}[-][0-9]{3}\\b");
        Matcher matcher = pattern.matcher(driver.getUser().getPhone());
        if (!matcher.matches())
        	errors.rejectValue("user.phone", "Format.userForm.phone");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.passwordConfirm", "NotEmpty");
		if (!BCrypt.checkpw(driver.getUser().getPasswordConfirm(), driver.getUser().getPassword()))
			errors.rejectValue("user.passwordConfirm", "Check.userForm.passwordConfirm");
		
	}

}
