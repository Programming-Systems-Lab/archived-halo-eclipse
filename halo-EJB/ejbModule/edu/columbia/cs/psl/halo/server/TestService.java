package edu.columbia.cs.psl.halo.server;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import edu.columbia.cs.psl.halo.entity.Class;
import edu.columbia.cs.psl.halo.entity.Enrollment;
import edu.columbia.cs.psl.halo.entity.EnrollmentType;
import edu.columbia.cs.psl.halo.entity.User;

@WebService
@Stateless
public class TestService extends AbstractFacade<User> {

	public TestService() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}
	@PersistenceContext(unitName="halo_persist") 
	EntityManager em;
	public User doTest()
	{
//		ArrayList<Class> classes = new ArrayList<Class>();
//		ArrayList<User> users = new ArrayList<User>();
//		for(int i = 0; i< 3;i++)
//		{
//			User b = new User();
//			b.setFirstName("User " + i);
//			em.persist(b);
//			Class d = new Class();
//			d.setName("Class " + d);
//			em.persist(d);
//			classes.add(d);
//			users.add(b);
//		}
//		
//		for(User u : users)
//		{
//			ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
//			for(Class c : classes)
//			{
//				Enrollment e = new Enrollment();
//				e.setCourse(c);
//				e.setUser(u);
//				e.setType(EnrollmentType.STUDENT);
//				enrollments.add(e);
//			}
//			u.setEnrollments(enrollments);
//			em.merge(u);
//		}
		User u = em.find(User.class, 50);
		String r = "";
		u.getEnrollments().size();
		for(Enrollment e : u.getEnrollments())
		{
			e.getCourse();
		}
		
		return u;
	}
}
