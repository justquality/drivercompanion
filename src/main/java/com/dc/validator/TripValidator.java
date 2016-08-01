package com.dc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dc.model.Trip;

@Component
public class TripValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Trip.class.equals(aClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Trip trip = (Trip) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departure", "NotEmpty");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "arrival", "NotEmpty");
		if (null != trip.getDeparture() && trip.getDeparture().equals(trip.getArrival()))
			errors.rejectValue("arrival", "Same.trip.arrival");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");
		if (trip.getPrice() == null || trip.getPrice() < 1 || trip.getPrice() > 500)
			errors.rejectValue("price", "Invalid.trip.price");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
	}

}
