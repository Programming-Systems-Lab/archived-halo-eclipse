package edu.columbia.cs.psl.halo;

import javax.xml.ws.BindingProvider;

import edu.columbia.cs.psl.halo.server.stubs.AdminService;
import edu.columbia.cs.psl.halo.server.stubs.AdminServiceService;
import edu.columbia.cs.psl.halo.server.stubs.CourseService;
import edu.columbia.cs.psl.halo.server.stubs.CourseServiceService;
import edu.columbia.cs.psl.halo.server.stubs.User;
import edu.columbia.cs.psl.halo.server.stubs.UserService;
import edu.columbia.cs.psl.halo.server.stubs.UserServiceService;
import edu.columbia.cs.psl.halo.server.wrapper.UserWrapper;

public class HALOServiceFactory {
	private UserService userSvc = null;
	private CourseService courseSvc = null;
	private AdminService adminSvc = null;
	
	private String username = null;
	private String password = null;
	
	private static HALOServiceFactory instance = null;
	
	public static HALOServiceFactory getInstance() {
		if(instance == null)
			instance = new HALOServiceFactory();
		return instance;
	}
	private void clearCache()
	{
		userSvc  = null;
		courseSvc = null;
		adminSvc= null;
	}
	private void addCrendentials(BindingProvider svc)
	{
		if(username != null && password != null)
		{
			((BindingProvider)svc).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
			((BindingProvider)svc).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
		}
	}
	private User me;
	public User getMe() {
		return me;
	};
	public boolean login(String username, String password)
	{
		this.username = username;
		this.password = UserWrapper.getEncryptedPassword(password);
		clearCache();
		try
		{
			me = getUserSvc().getMe();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean isLoggedIn()
	{
		return me != null;
	}
	public void logout()
	{
		this.username = null;
		this.password = null;
		me = null;
		clearCache();
	}
	
	public UserService getUserSvc() {
		if(userSvc == null)
		{
			userSvc = (new UserServiceService()).getUserServicePort();
			addCrendentials((BindingProvider) userSvc);
		}
		return userSvc;
	}
	
	public CourseService getCourseSvc() {
		if(courseSvc == null)
		{
			courseSvc = (new CourseServiceService()).getCourseServicePort();
			addCrendentials((BindingProvider) courseSvc);
		}
		return courseSvc;
	}
	
	public AdminService getAdminSvc() {
		if(adminSvc == null)
		{
			adminSvc = (new AdminServiceService()).getAdminServicePort();
			addCrendentials((BindingProvider) adminSvc);
		}
		return adminSvc;
	}
}
