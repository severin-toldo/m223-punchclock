package com.stoldo.m223_punchclock.model.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CheckInCheckOutValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCheckInCheckOut {
	
	String message() default "checkOut cannot be before checkIn!";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
	
}
