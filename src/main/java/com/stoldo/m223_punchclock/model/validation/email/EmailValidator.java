package com.stoldo.m223_punchclock.model.validation.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
public class EmailValidator implements ConstraintValidator<Email, String> {

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email);
	}
	
}

