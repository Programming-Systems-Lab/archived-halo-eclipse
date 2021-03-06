package edu.columbia.cs.psl.halo;

import java.io.IOException;
import java.security.AccessController;

import javassist.expr.Handler;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.xml.ws.BindingProvider;

import edu.columbia.cs.psl.halo.server.stubs.AdminService;
import edu.columbia.cs.psl.halo.server.stubs.AdminServiceService;
import edu.columbia.cs.psl.halo.server.stubs.CourseService;
import edu.columbia.cs.psl.halo.server.stubs.CourseServiceService;
import edu.columbia.cs.psl.halo.server.stubs.LogService;
import edu.columbia.cs.psl.halo.server.stubs.LogServiceService;
import edu.columbia.cs.psl.halo.server.stubs.User;
import edu.columbia.cs.psl.halo.server.stubs.UserService;
import edu.columbia.cs.psl.halo.server.stubs.UserServiceService;
import edu.columbia.cs.psl.halo.server.wrapper.UserWrapper;

public class HALOServiceFactory {

	
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
	public void refreshMe()
	{
		me = getUserSvc().getMe();
	}
	public boolean login(String username, String password)
	{
		this.username = username;
		this.password = password;
		clearCache();
		try
		{
			me = getUserSvc().getMe();
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public boolean loginFromRememberMe(String username, String password)
	{
		this.username = username;
		this.password = password;
		clearCache();
		try
		{
			me = getUserSvc().getMe();
		}
		catch(Exception e)
		{
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
		UserService	userSvc = (new UserServiceService()).getUserServicePort();
			addCrendentials((BindingProvider) userSvc);
		return userSvc;
	}
	
	public CourseService getCourseSvc() {
		CourseService courseSvc = (new CourseServiceService()).getCourseServicePort();
			addCrendentials((BindingProvider) courseSvc);
		return courseSvc;
	}
	
	public AdminService getAdminSvc() {
			AdminService adminSvc = (new AdminServiceService()).getAdminServicePort();
			addCrendentials((BindingProvider) adminSvc);
		return adminSvc;
	}
	public void log(String action, String params)
	{
		LogService logSvc = (new LogServiceService()).getLogServicePort();
		addCrendentials((BindingProvider) logSvc);
		logSvc.log(action, params);
	}
}
