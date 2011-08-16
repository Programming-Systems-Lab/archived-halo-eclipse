package edu.columbia.cs.psl.halo.server.auth;

import javax.security.auth.login.LoginException;

import com.sun.appserv.security.AppservPasswordLoginModule;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;




public class HALOLoginModule extends AppservPasswordLoginModule {

    private Connection conn = null;
    
    private Connection getConnection() throws LoginException {
    	try {
			if(conn == null || conn.isClosed())
			{
				String userName = "halo";
			    String password = "h4l0";
			    String url = "jdbc:mysql://localhost/halo";
			    Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			    conn = DriverManager.getConnection (url, userName, password);
			}
		} catch (Exception e) {
			LoginException ex = new LoginException("Unable to connect to backing datastore");
			throw ex;
		}
		return conn;
	}

	
	private boolean loginByRememberMe() throws LoginException
	{
		if(_password.substring(0, 1).equals("<"))
		{
			Connection conn = getConnection();
			PreparedStatement s;
			try {
				s = conn.prepareStatement("select u.id,if(stud.id>0,1,0) as stud, if(prof.id>0,1,0) as prof, if(ta.id>0,1,0) as ta, if(admin.id>0,1,0) as admin from h_user u " +
						" left join enrollment stud on stud.user_id=u.id and stud.type=\"STUDENT\" " +
						"left join enrollment prof on prof.user_id=u.id and prof.type=\"PROFESSOR\" " +
						"left join enrollment ta on ta.user_id=u.id and ta.type=\"TA\" " +
						"left join enrollment admin on admin.user_id=u.id and admin.type=\"ADMIN\" " +
						"where u.email=? and u.REMEMBERME=? and u.remembermeexp > date_add(current_timestamp(),interval -10 day) group by u.id");
				s.setString(1, _username);
				s.setString(2, _password);
				s.execute();
				ResultSet rs = s.getResultSet();
				if(rs.next())
				{
					LinkedList<String> groups = new LinkedList<String>();
					groups.add("USER");
					if(rs.getInt("stud") == 1)
						groups.add("STUDENT");
					if(rs.getInt("prof") == 1)
						groups.add("PROFESSOR");
					if(rs.getInt("ta") == 1)
						groups.add("TA");
					if(rs.getInt("admin") == 1)
						groups.add("ADMIN");
					String[] gret = new String[groups.size()];
					commitUserAuthentication(groups.toArray(gret));
					conn.close();
					return true;
				}
			} catch (SQLException e) {
				LoginException ex = new LoginException("Unable to execute SQL commands to log you in");
				ex.initCause(e);
				throw ex;
			}
			
		}
		return false;
	}
	
	private boolean loginByPassword() throws LoginException
	{
			try
			{
			Connection conn = getConnection();
			PreparedStatement s = conn.prepareStatement("select u.id,if(stud.id>0,1,0) as stud, if(prof.id>0,1,0) as prof, if(ta.id>0,1,0) as ta, if(admin.id>0,1,0) as admin from h_user u " +
					" left join enrollment stud on stud.user_id=u.id and stud.type=\"STUDENT\" " +
					"left join enrollment prof on prof.user_id=u.id and prof.type=\"PROFESSOR\" " +
					"left join enrollment ta on ta.user_id=u.id and ta.type=\"TA\" " +
					"left join enrollment admin on admin.user_id=u.id and admin.type=\"ADMIN\" " +
					"where u.email=? and u.password=? group by u.id");
			s.setString(1, _username);
			s.setString(2, _password);
			s.execute();
			ResultSet rs = s.getResultSet();
			if(rs.next())
			{
				LinkedList<String> groups = new LinkedList<String>();
				groups.add("USER");
				if(rs.getInt("stud") == 1)
					groups.add("STUDENT");
				if(rs.getInt("prof") == 1)
					groups.add("PROFESSOR");
				if(rs.getInt("ta") == 1)
					groups.add("TA");
				if(rs.getInt("admin") == 1)
					groups.add("ADMIN");
				String[] gret = new String[groups.size()];
				System.out.println(_username + " " + gret);
				commitUserAuthentication(groups.toArray(gret));
				conn.close();
				return true;
			}
			}
			catch (SQLException e) {
				LoginException ex = new LoginException("Unable to execute SQL commands to log you in");
				ex.initCause(e);
				throw ex;
			}

		return false;
	}
	
	protected void authenticateUser() throws LoginException {
            if(!loginByRememberMe())
            	if(!loginByPassword())
                    throw new LoginException("Login Failed for user " + _username);

	}

	 
}
