package edu.columbia.cs.psl.halo.server;

import javax.ejb.Remote;

@Remote
public interface UserServiceRemote {
	public void setFBToken(int userid, String token, String webSecret);
}
