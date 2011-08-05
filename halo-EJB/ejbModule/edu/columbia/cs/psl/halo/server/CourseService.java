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
@RolesAllowed("ADMIN") 
public class CourseService extends AbstractFacade<Course>{
	public CourseService()
	{
		super(Course.class);
	}
	
	public List<Enrollment> getEnrollmentsForCourse(Course c)
	{
		return c.getEnrollments();
	}
}
