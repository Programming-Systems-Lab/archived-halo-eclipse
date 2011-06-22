package edu.columbia.cs.psl.halo.server;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@WebService
public class UserService implements UserServiceRemote {

    /**
     * Default constructor. 
     */
    public UserService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String test() {
		
		return "Foobar";
	}

}
