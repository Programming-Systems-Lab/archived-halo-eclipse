package edu.columbia.cs.psl.halo.server.wrapper;

import java.security.NoSuchAlgorithmException;

import edu.columbia.cs.psl.halo.server.stubs.User;

public class UserWrapper {
	User u;
	protected final static String HEX_DIGITS = "0123456789abcdef";

	public static String getEncryptedPassword(String plaintext) {
		
		java.security.MessageDigest d =null;
				try {
					d = java.security.MessageDigest.getInstance("SHA-1");
				} catch (NoSuchAlgorithmException e) {
				}
				d.reset();
				d.update(plaintext.getBytes());
				byte[] hashedBytes =  d.digest();
				StringBuffer sb = new StringBuffer(hashedBytes.length * 2);
		        for (int i = 0; i < hashedBytes.length; i++) {
		             int b = hashedBytes[i] & 0xFF;
		             sb.append(HEX_DIGITS.charAt(b >>> 4)).append(HEX_DIGITS.charAt(b & 0xF));
		        }
		        return sb.toString();	
	}
}
