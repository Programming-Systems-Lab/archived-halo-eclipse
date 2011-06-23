package halo_se_admin;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.WebServiceRef;

import edu.columbia.cs.psl.halo.server.stubs.Enrollment;
import edu.columbia.cs.psl.halo.server.stubs.TestService;
import edu.columbia.cs.psl.halo.server.stubs.TestServiceService;
import edu.columbia.cs.psl.halo.server.stubs.User;




public class Test {
	static TestServiceService svcsvc;
	public Test() {
		// TODO Auto-generated constructor stub
	}
	public void doTest()
	{
			svcsvc = new TestServiceService();
		
		TestService svc = svcsvc.getTestServicePort();
		User u = svc.doTest();
		

		System.out.println("User: " + u.getFirstName() + " " + u.getId());
		for(Enrollment e : u.getEnrollments())
		{
			System.out.println("Enrollment: " + e.getId() + " " + e.getType() + " " + e.getUser().getFirstName() + " " + e.getCourse().getName());
			System.out.println(e.getUser().getRef());
		}
		System.out.println(u);
	}
	public static void main(String[] args) {
		System.out.println("Starting to connect to server");

//			((Stub) svc).setUsername("jon");
//			((Stub) svc).setPassword("test");
//			((BindingProvider)svc).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "jon");
//			((BindingProvider)svc).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "test");
		Test t = new Test();
		t.doTest();

	}
}
