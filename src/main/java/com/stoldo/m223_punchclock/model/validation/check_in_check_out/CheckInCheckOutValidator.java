package com.stoldo.m223_punchclock.model.validation.check_in_check_out;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.stoldo.m223_punchclock.model.entity.TimeEntryEntity;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
public class CheckInCheckOutValidator implements ConstraintValidator<ValidCheckInCheckOut, TimeEntryEntity> {

	@Override
	public boolean isValid(TimeEntryEntity bean, ConstraintValidatorContext context) {
		if (bean.getCheckIn() != null && bean.getCheckOut() != null && bean.getCheckOut().before(bean.getCheckIn())) {
			return false;
		}
		
		return true;
	}
	
}

