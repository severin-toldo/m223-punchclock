package com.stoldo.m223_punchclock.model.validation.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class EmailValidator implements ConstraintValidator<Email, String> {

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email);
	}
	
}

