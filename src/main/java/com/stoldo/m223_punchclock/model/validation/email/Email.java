package com.stoldo.m223_punchclock.model.validation.email;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
	
	String message() default "Must be a valid E-Mail!";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
	
}
