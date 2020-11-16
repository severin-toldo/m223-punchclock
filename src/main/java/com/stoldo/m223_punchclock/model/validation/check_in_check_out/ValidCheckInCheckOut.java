package com.stoldo.m223_punchclock.model.validation.check_in_check_out;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
@Constraint(validatedBy = CheckInCheckOutValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCheckInCheckOut {
	
	String message() default "E1009";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
	
}
