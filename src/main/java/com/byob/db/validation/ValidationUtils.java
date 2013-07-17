package com.byob.db.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.byob.db.exception.DAOException;

/**
 * Validation tools
 * 
 * @author Gerald PEREIRA
 *
 */
public final class ValidationUtils {

	// Thread safe
	private final Validator validator;
	
	/**
	 * Constructor
	 * @param validator the bean validator
	 */
	protected ValidationUtils(final Validator validator){
		this.validator = validator;
	}
	
	/**
	 * Validates the bean using the rules tagged with the groups marker interfaces 
	 * @param bean
	 * @param groups
	 * @throws DAOException
	 */
	public <T> void  validate(final T bean, final Class<?>... groups) throws DAOException{
		final Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean,groups);
		if (!constraintViolations.isEmpty()) {
			throw new DAOException(constraintViolations.iterator().next());
		}
	}
	
}
