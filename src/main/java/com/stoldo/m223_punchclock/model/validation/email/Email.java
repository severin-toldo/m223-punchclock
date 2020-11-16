package com.stoldo.m223_punchclock.model.validation.email;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
@Constraint(validatedBy = EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
	
	String message() default "E1008";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
	
}
