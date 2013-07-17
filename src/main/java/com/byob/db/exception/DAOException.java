package com.byob.db.exception;

import javax.validation.ConstraintViolation;

/**
 * DAOException : issue related to the database
 * 
 * @author Gerald PEREIRA
 *
 */
public class DAOException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param message 
	 */
	public DAOException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * @param cause
	 */
	public DAOException(final Throwable cause){
		super(cause);
	}
	

	/**
	 * Constructor
	 * @param cause
	 */
	public DAOException(final String message, final Throwable cause){
		super(message, cause);
	}
	
	/**
	 * Constructor
	 * @param constraint
	 */
	public DAOException(final ConstraintViolation<?> constraint) {
		super(constraint.getPropertyPath() + ": " + constraint.getMessage());
	}

}
