package com.byob.db.crypto;

import com.google.inject.AbstractModule;

/**
 * Crypto guice module 
 * 
 * @author Gerald PEREIRA
 * 
 */
public class CryptoModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(CryptoUtils.class).toInstance(newCrypto());
	}
	
	/**
	 * Crypto factory
	 * @return the created CryptoUtils instance
	 */
	public static CryptoUtils newCrypto(){
		return new CryptoUtils();
	}

}
