package edu.columbia.cs.psl.halo.server;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.criteria.CriteriaQuery;

import edu.columbia.cs.psl.halo.entity.Assignment;
import edu.columbia.cs.psl.halo.entity.Course;
import edu.columbia.cs.psl.halo.entity.Enrollment;
import edu.columbia.cs.psl.halo.entity.EnrollmentType;
import edu.columbia.cs.psl.halo.entity.Quest;
import edu.columbia.cs.psl.halo.entity.QuestProgress;
import edu.columbia.cs.psl.halo.entity.Task;
import edu.columbia.cs.psl.halo.entity.User;

@Stateless
@WebService
@RolesAllowed("ADMIN")
public class AdminService extends AbstractFacade<User> {
    
	public void addUser(String first, String last, String email, String password)
	{
		Course c = getEntityManager().find(Course.class, 1);
        
		User u = new User();
		u.setFirstName(first);
		u.setLastName(last);
		u.setEmail(email);
		u.setPassword(UserService.getEncryptedPassword(password));
		u.setXp(0);
		getEntityManager().persist(u);
		
		Enrollment e = new Enrollment();
		e.setUser(u);
		e.setCourse(c);
		e.setType(EnrollmentType.STUDENT);
		
		getEntityManager().persist(e);
		
		javax.persistence.criteria.CriteriaQuery<Task> cq = getEntityManager().getCriteriaBuilder().createQuery(Task.class);
        cq.select(cq.from(Task.class));
        for(Task t : getEntityManager().createQuery(cq).getResultList())
		{
			QuestProgress p = new QuestProgress();
			p.setUser(u);
			p.setTask(t);
			p.setQuest(t.getQuest());
			p.setUpdated(new Date());
			p.setCompleted(false);
			getEntityManager().persist(p);
			
			
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Course> getCourses()
	{
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Course.class));
        return getEntityManager().createQuery(cq).getResultList();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getUsers()
	{
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        return getEntityManager().createQuery(cq).getResultList();
	}
	
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
    public Assignment createAssignment(Assignment a)
	{
		getEntityManager().persist(a);
//		getEntityManager().getEntityManagerFactory().getCache().evictAll();
		return a;
//		return getEntityManager().find(Assignment.class, a.getId());
	}
	public Assignment updateAssignment(Assignment a)
	{
		if(a.getId() == 0)
			return createAssignment(a);
		getEntityManager().merge(a);
		return a;
	}
	public boolean deleteAssignment(Assignment a)
	{
		try
		{
			getEntityManager().remove(a);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}
	public Task createTask(Task t)
	{
		getEntityManager().persist(t);
		return t;
	}
	public Task updateTask(Task t)
	{
		t.setQuest(getEntityManager().find(Quest.class, t.getQuest().getId()));

		if(t.getId() == 0)
			return createTask(t);
		getEntityManager().merge(t);
		getEntityManager().getEntityManagerFactory().getCache().evictAll();

		return t;
	}
	public boolean deleteTask(Task t)
	{
		try
		{
			getEntityManager().remove(t);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}
	public Quest createQuest(Quest q)
	{
		if(q.getTasks() != null)
		for(Task t : q.getTasks())
		{
			getEntityManager().persist(t);
		}
		getEntityManager().persist(q);
		return q;
	}
	public Quest updateQuest(Quest q)
	{
		q.setAssignment(getEntityManager().find(Assignment.class, q.getAssignment().getId()));
		if(q.getId() == 0)
			return createQuest(q);
		Quest original = getEntityManager().find(Quest.class, q.getId());
		if(q.getTasks() != null)
		for(Task t : q.getTasks())
		{
			t.setQuest(q); 
			if(t.getId() > 0)
				getEntityManager().merge(t);
			else
				getEntityManager().persist(t);
		}
		getEntityManager().merge(q);
		getEntityManager().getEntityManagerFactory().getCache().evictAll();
		return q;
	}
	public boolean deleteQuest(Quest q)
	{
		try
		{
			getEntityManager().remove(q);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}
}
