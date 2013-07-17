package com.byob.db.validation;

import javax.validation.Validation;

import com.google.inject.AbstractModule;

/**
 * Validation guice module 
 * 
 * @author Gerald PEREIRA
 * 
 */
public final class ValidationModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ValidationUtils.class).toInstance(newValidator());
	}
	
	/**
	 * Validator factory
	 * @return the created Validator instance
	 */
	public static ValidationUtils newValidator(){
		return new ValidationUtils(Validation.buildDefaultValidatorFactory().getValidator());
	}
}
