package edu.columbia.cs.psl.halo.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.columbia.cs.psl.halo.entity.Course;
import edu.columbia.cs.psl.halo.entity.Enrollment;
import edu.columbia.cs.psl.halo.entity.QuestProgress;
import edu.columbia.cs.psl.halo.entity.User;
import edu.columbia.cs.psl.halo.entity.UserStatus;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@WebService
@RolesAllowed("USER") 
public class UserService extends AbstractFacade<User>  {
	
    /**
     * Default constructor. 
     */
    public UserService() {
        super(User.class);
    }

    public String sayHello()
    {
    	return "Hello";
    }
    public List<QuestProgress> getMyProgress()
    {
    	return null; //TODO
    }
    
    public List<QuestProgress> getMyProgressFor(Course c)
    {
    	return null; //TODO
    }
    
    public List<Enrollment> getEnrollments()
    {
    	User u = getUser();
    	return u.getEnrollments();
    }    

}
