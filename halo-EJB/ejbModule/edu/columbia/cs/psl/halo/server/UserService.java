package edu.columbia.cs.psl.halo.server;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.columbia.cs.psl.halo.entity.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@WebService
@RolesAllowed("users") 

public class UserService implements UserServiceRemote {
	@PersistenceContext(unitName="halo_persist") 
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UserService() {
        // TODO Auto-generated constructor stub 
    }

	public String test() {
		User u = new User();
		return "Foobar";
	}

}
