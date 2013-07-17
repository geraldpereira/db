package com.byob.db.crypto;

import java.nio.charset.Charset;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * 
 * Crypto utilities
 * 
 * @author Gerald Pereira
 *
 */
public final class CryptoUtils {

	private static final String CHARSET = "UTF-8";

	protected CryptoUtils(){
	}
	
	/**
	 * Encypts a String using MD5 (SHA 256 is not consistent !)
	 * @param str the String to encrypt
	 * @return the encrypted String
	 */
	public String encrypt(final String str){
		HashFunction hf = Hashing.md5();
		HashCode hc = hf.newHasher().putString(str,Charset.forName(CHARSET)).hash();
		return hc.toString();
	}
	
//	public static void main(String[] args) {
//		System.out.println(new CryptoUtils().encrypt("password"));
//	}
}

