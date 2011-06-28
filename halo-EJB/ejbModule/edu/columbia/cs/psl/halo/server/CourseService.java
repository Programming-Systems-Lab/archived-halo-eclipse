package edu.columbia.cs.psl.halo.server;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebService;

import edu.columbia.cs.psl.halo.entity.Assignment;
import edu.columbia.cs.psl.halo.entity.Course;
import edu.columbia.cs.psl.halo.entity.Enrollment;
import edu.columbia.cs.psl.halo.entity.Quest;
import edu.columbia.cs.psl.halo.entity.Task;

@Stateless
@WebService
@RolesAllowed("USER") 
public class CourseService extends AbstractFacade<Course>{
	public CourseService()
	{
		super(Course.class);
	}
	public Assignment createAssignment(Assignment a)
	{
		getEntityManager().persist(a);
		return a;
	}
	public Assignment updateAssignment(Assignment a)
	{
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
	public Quest createQuest(Quest q)
	{
		for(Task t : q.getTasks())
		{
			getEntityManager().persist(t);
		}
		getEntityManager().persist(q);
		return q;
	}
	public Quest updateQuest(Quest q)
	{
		for(Task t : q.getTasks())
		{
			if(t.getId() > 0)
				getEntityManager().merge(t);
			else
				getEntityManager().persist(t);
		}
		getEntityManager().merge(q);
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
	public List<Enrollment> getEnrollmentsForCourse(Course c)
	{
		return c.getEnrollments();
	}
}
