package com.byob.db.validation;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.junit.Test;

import com.byob.db.exception.DAOException;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ValidationUtilsTest {

	private static class BeanTest{
		
		private Integer id;
		
		@NotNull
		public Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		
	}
	@Test
	public void testValidation() throws DAOException {
		final Injector injector = Guice.createInjector(new ValidationModule());
		final ValidationUtils validator = injector.getInstance(ValidationUtils.class);
		final BeanTest bean = new BeanTest();
		bean.setId(2);
		validator.validate(bean, Default.class);
	}

	@Test(expected=DAOException.class)
	public void testValidation2() throws DAOException {
		final Injector injector = Guice.createInjector(new ValidationModule());
		final ValidationUtils validator = injector.getInstance(ValidationUtils.class);
		final BeanTest bean = new BeanTest();
		validator.validate(bean, Default.class);
	}
}
