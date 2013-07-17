package com.byob.db.crypto;

import static junit.framework.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.byob.db.exception.DAOException;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class CryptoUtilsTest {

	private static final String STR_1 = "dfvsgdlfjshgkjfdshlkfdg";
	private static final String STR_1_HASH = "36f58b23aa4692ff2f59cf7151bd5cf6";
	private static final String STR_2 = "dsfkMQSsdfq2454LdVQdPKQDSLDSQFLK";
	private static final String STR_2_HASH = "1f3eaaa0ef2365fb752afc979e40536e";

	private AtomicInteger faults = new AtomicInteger(0);

	@Test
	public void testValidation() throws DAOException, InterruptedException {
		final Injector injector = Guice.createInjector(new CryptoModule());
		final CryptoUtils crypto = injector.getInstance(CryptoUtils.class);
		// Is it thread safe ?
		Thread t1 = new Thread() {
			public void run() {
				for (int i = 0; i < 300; i++) {
					final String cr = crypto.encrypt(STR_1);
					if (!STR_1_HASH.equals(cr)) {
						System.out.println("STR1 " + i + "should be "
								+ STR_1_HASH + " but is " + cr);
						faults.incrementAndGet();
					}
				}
			};
		};

		Thread t2 = new Thread() {
			public void run() {
				for (int i = 0; i < 300; i++) {
					final String cr = crypto.encrypt(STR_2);
					if (!STR_2_HASH.equals(cr)) {
						System.out.println("STR2 " + i + " should be "
								+ STR_2_HASH + " but is " + cr);
						faults.incrementAndGet();
					}
				}
			};
		};
		t1.start();
		t2.start();
		t1.join();
		t2.join();

		assertTrue(faults.get() == 0);
	}

}
