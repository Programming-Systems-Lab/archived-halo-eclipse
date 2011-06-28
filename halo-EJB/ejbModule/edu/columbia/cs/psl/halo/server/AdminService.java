package edu.columbia.cs.psl.halo.server;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebService;

import edu.columbia.cs.psl.halo.entity.Course;
import edu.columbia.cs.psl.halo.entity.Enrollment;
import edu.columbia.cs.psl.halo.entity.User;

@Stateless
@WebService
@RolesAllowed("ADMIN")
public class AdminService extends AbstractFacade<User> {
    
	public AdminService() {
		super(User.class);
	}
	
	public Course createCourse(Course c)
	{
		getEntityManager().persist(c);
		return c;
	}
	public Course updateCourse(Course c)
	{
		getEntityManager().merge(c);
		return c;
	}
	public boolean deleteCourse(Course c)
	{
		try
		{
			getEntityManager().remove(c);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}
	
	public Enrollment createEnrollment(Enrollment e)
	{
		getEntityManager().persist(e);
		return e;
	}
	public Enrollment updateEnrollment(Enrollment e)
	{
		getEntityManager().merge(e);
		return e;
	}
	public boolean deleteEnrollment(Enrollment e)
	{
		try
		{
			getEntityManager().remove(e);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}
    public User createUser(User u)
    {
    	getEntityManager().persist(u);
    	return u;
    }
    public User updateUser(User u)
    {
    	getEntityManager().merge(u);
    	return u;
    }
    public boolean deleteUser(User u)
    {
    	try
		{
			getEntityManager().remove(u);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
    }
    public List<Enrollment> getEnrollmentsFor(User u)
    {
    	return ((User) getEntityManager().find(User.class, u.getId())).getEnrollments();
    }
    public Course getCourse(int id)
    {
    	return getEntityManager().find(Course.class, id);
    }
    public User getUser(int id)
    {
    	return getEntityManager().find(User.class, id);
    }
}
