package com.dc.validator;

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
		
		ValidationUtils.rejectIfEmpty(errors, "user.firstName", "NotEmpty");
		if (companion.getUser().getFirstName().length() < 2 || companion.getUser().getFirstName().length() > 32)
			errors.rejectValue("user.firstName", "Size.userForm.firstName");

		ValidationUtils.rejectIfEmpty(errors, "user.lastName", "NotEmpty");
		if (companion.getUser().getLastName().length() < 2 || companion.getUser().getLastName().length() > 32)
			errors.rejectValue("user.lastName", "Size.userForm.lastName");
		
		ValidationUtils.rejectIfEmpty(errors, "user.passwordConfirm", "NotEmpty");
		if (!BCrypt.checkpw(companion.getUser().getPasswordConfirm(), companion.getUser().getPassword()))
			errors.rejectValue("user.passwordConfirm", "Check.userForm.passwordConfirm");
	}

}
