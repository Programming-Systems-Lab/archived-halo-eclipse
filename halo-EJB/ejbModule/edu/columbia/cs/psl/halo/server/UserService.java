package edu.columbia.cs.psl.halo.server;

import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.columbia.cs.psl.halo.entity.Class;
import edu.columbia.cs.psl.halo.entity.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@WebService
//@RolesAllowed("users") 

public class UserService extends AbstractFacade<User>  {
	@PersistenceContext(unitName="halo_persist") 
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UserService() {
        super(User.class);
    }

	public User test() {
//		User u = new User();
//		Class d = new Class();
//		u.setFirst_name("Jon Bell");
//		d.setName("Fake class");
//		em.persist(d);
//		d.setStudents(new ArrayList<User>());
//		u.setStudentClasses(new ArrayList<Class>());
//		d.getStudents().add(u);
//		u.getStudentClasses().add(d);
//		em.persist(u);
//		em.merge(d);
//		em.merge(u);
//		return u;
		return null;
	}

}
