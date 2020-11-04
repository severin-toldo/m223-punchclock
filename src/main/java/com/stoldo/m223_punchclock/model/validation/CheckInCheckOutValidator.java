package com.stoldo.m223_punchclock.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.stoldo.m223_punchclock.model.entity.EntryEntity;

public class CheckInCheckOutValidator implements ConstraintValidator<ValidCheckInCheckOut, EntryEntity> {

	@Override
	public boolean isValid(EntryEntity bean, ConstraintValidatorContext context) {
		if (bean.getCheckOut().before(bean.getCheckIn())) {
			return false;
		}
		
		return true;
	}
	
}

