package com.dc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dc.model.Review;

@Component
public class ReviewValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Review.class.equals(aClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Review review = (Review) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", "NotEmpty");
		if(review.getComment().length() < 10 || review.getComment().length() > 500)
			errors.rejectValue("comment", "Size.review.comment");
		
		if(review.getMark() == 0)
			errors.rejectValue("mark", "Invalid.review.mark");
	}

}
