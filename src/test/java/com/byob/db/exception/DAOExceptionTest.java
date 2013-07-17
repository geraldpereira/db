package com.byob.db.exception;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

import org.junit.Test;


public class DAOExceptionTest {

	private static final String MESSAGE = "test"; 
	
	@Test
	public void testString() {
		final DAOException exception = new DAOException(MESSAGE);
		assertEquals(MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testException() {
		final DAOException exception = new DAOException(new Exception(MESSAGE));
		assertEquals(MESSAGE, exception.getCause().getMessage());
	}
	
	@Test
	public void testExceptionString() {
		final DAOException exception = new DAOException(MESSAGE, new Exception(MESSAGE));
		assertEquals(MESSAGE, exception.getMessage());
		assertEquals(MESSAGE, exception.getCause().getMessage());
	}

	
	@Test
	public void testValidation() {
		final DAOException exception = new DAOException(new ConstraintViolation<String>() {
			@Override
			public String getMessage() {
				return MESSAGE;
			}

			@Override
			public String getMessageTemplate() {
				return MESSAGE;
			}

			@Override
			public String getRootBean() {
				return MESSAGE;
			}

			@Override
			public Class<String> getRootBeanClass() {
				return String.class;
			}

			@Override
			public Object getLeafBean() {
				return MESSAGE;
			}

			@Override
			public Path getPropertyPath() {
				return new Path(){
					@Override
					public Iterator<Node> iterator() {
						return null;
					}
					public String toString(){
						return MESSAGE;
					}
				};
			}
				

			@Override
			public Object getInvalidValue() {
				return null;
			}

			@Override
			public ConstraintDescriptor<?> getConstraintDescriptor() {
				return null;
			}
		});
		assertEquals(MESSAGE+": "+MESSAGE, exception.getMessage());
	}

}
