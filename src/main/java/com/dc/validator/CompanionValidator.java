package com.dc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dc.model.Companion;

@Component
public class CompanionValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Companion.class.equals(aClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Companion companion = (Companion) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.firstName", "NotEmpty");
		if (companion.getUser().getFirstName().length() < 2 || companion.getUser().getFirstName().length() > 32)
			errors.rejectValue("user.firstName", "Size.userForm.firstName");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.lastName", "NotEmpty");
		if (companion.getUser().getLastName().length() < 2 || companion.getUser().getLastName().length() > 32)
			errors.rejectValue("user.lastName", "Size.userForm.lastName");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.phone", "NotEmpty");
        Pattern pattern = Pattern.compile("\\b[0][67][0-9]{1}[-][0-9]{3}[-][0-9]{3}\\b");
        Matcher matcher = pattern.matcher(companion.getUser().getPhone());
        if (!matcher.matches())
        	errors.rejectValue("user.phone", "Format.userForm.phone");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.passwordConfirm", "NotEmpty");
		if (!BCrypt.checkpw(companion.getUser().getPasswordConfirm(), companion.getUser().getPassword()))
			errors.rejectValue("user.passwordConfirm", "Check.userForm.passwordConfirm");
	}

}
