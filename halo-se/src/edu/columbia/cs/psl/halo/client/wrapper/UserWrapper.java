package edu.columbia.cs.psl.halo.client.wrapper;


import java.security.NoSuchAlgorithmException;

import edu.columbia.cs.psl.halo.server.stubs.Level;
import edu.columbia.cs.psl.halo.server.stubs.Title;
import edu.columbia.cs.psl.halo.server.stubs.TitlePosition;
import edu.columbia.cs.psl.halo.server.stubs.User;
import edu.columbia.cs.psl.halo.server.stubs.UserStatus;

public class UserWrapper {
	User u;
	private final static String HEX_DIGITS = "0123456789abcdef";

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
	public Title getActiveTitle() {
		return u.getActiveTitle();
	}

	public void setActiveTitle(Title value) {
		u.setActiveTitle(value);
	}

	public int getAchievementPoints() {
		return u.getAchievementPoints();
	}

	public Level getLevel() {
		return u.getLevel();
	}

	public int getXp() {
		return u.getXp();
	}

	public void setAchievementPoints(int value) {
		u.setAchievementPoints(value);
	}

	public void setLevel(Level value) {
		u.setLevel(value);
	}

	public void setXp(int value) {
		u.setXp(value);
	}

	
	public String getFullName()
	{
		if(getActiveTitle() != null)
		{
			if(getActiveTitle().getPosition().equals(TitlePosition.PREFIX))
				return getActiveTitle().getTitle() + " " + getFirstName() + " " + getLastName();
			else if(getActiveTitle().getPosition().equals(TitlePosition.INFIX))
				return getFirstName() + " " +  getActiveTitle().getTitle()  + " " + getLastName();
			else if(getActiveTitle().getPosition().equals(TitlePosition.SUFIX))
				return getFirstName() + " " + getLastName() + " " +  getActiveTitle().getTitle() ;
		}
		return getFirstName() + " " + getLastName();
	}
	
	
	public UserWrapper(User u)
	{
		this.u=u;
	}
	
	public boolean equals(Object arg0) {
		return u.equals(arg0);
	}

	public String getEmail() {
		return u.getEmail();
	}

	public String getFirstName() {
		return u.getFirstName();
	}

	public int getId() {
		return u.getId();
	}

	public String getIdstr() {
		return u.getIdstr();
	}

	public String getLastName() {
		return u.getLastName();
	}

	public String getPassword() {
		return u.getPassword();
	}

	public Object getRef() {
		return u.getRef();
	}

	public UserStatus getStatus() {
		return u.getStatus();
	}

	public int hashCode() {
		return u.hashCode();
	}

	public void setEmail(String value) {
		u.setEmail(value);
	}

	public void setFirstName(String value) {
		u.setFirstName(value);
	}

	public void setId(int value) {
		u.setId(value);
	}

	public void setIdstr(String value) {
		u.setIdstr(value);
	}

	public void setLastName(String value) {
		u.setLastName(value);
	}

	public void setPassword(String value) {
		u.setPassword(value);
	}

	public void setRef(Object value) {
		u.setRef(value);
	}

	public void setStatus(UserStatus value) {
		u.setStatus(value);
	}

	public String toString() {
		return u.toString();
	}
	
}
