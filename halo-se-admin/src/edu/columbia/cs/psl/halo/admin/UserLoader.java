package edu.columbia.cs.psl.halo.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.server.stubs.AdminService;
import edu.columbia.cs.psl.halo.server.stubs.AdminServiceService;

public class UserLoader {
	public static void main(String[] args) throws Exception{
//		HALOServiceFactory.getInstance().login("jon", "test123");
		File f = new File("students.csv");
		Scanner s = new Scanner(f);
		while(s.hasNextLine())
		{
			String l = s.nextLine();
			String[] a = l.split(",");
//			HALOServiceFactory.getInstance().getAdminSvc().addUser(a[1],a[0],a[2],a[3]);			
			String msg = "Dear FIRST_N LAST_N,\n"+
"Please find your login for HALO-SE below, as well as a link to installation instructions. While it is not mandatory that you use HALO to help with your upcoming assignment, we would encourage you to give it a try - it is designed to help you ensure that you fully accomplish the requirements of the assignment and receive full credit.\n\n"+

"Your username is: UNI\n"+
"Your password is: PASSWORD\n"+
"(Note that once you login, you can change your password to whatever you like)\n\n"+

"You can find detailed installation instructions here:Êhttp://ase.cs.columbia.edu/halo-update/install.html\n\n"+

"If you have any problems using HALO-SE, please do not hesitate to contact me directly.\n\n"+

"Best,\nJon Bell";
			msg  = msg.replace("FIRST_N", a[1]).replace("LAST_N", a[0]).replace("UNI", a[2]).replace("PASSWORD", a[3]);
//			System.out.println(msg);
			sendEmail("jbell@cs.columbia.edu", a[2]+"@columbia.edu", "COMS 3134: HALO Installation Info & Login Credentials", msg);
//			System.exit(0);
		}
	}
	  private static Properties fMailServerConfig = new Properties();

	  static {
		    fetchConfig();
		  }

		  /**
		  * Open a specific text file containing mail server
		  * parameters, and populate a corresponding Properties object.
		  */
		  private static void fetchConfig() {
		    InputStream input = null;
		    try {
		      //If possible, one should try to avoid hard-coding a path in this
		      //manner; in a web application, one should place such a file in
		      //WEB-INF, and access it using ServletContext.getResourceAsStream.
		      //Another alternative is Class.getResourceAsStream.
		      //This file contains the javax.mail config properties mentioned above.
		      input = new FileInputStream( "/Users/jon/Documents/PSL/Projects/halo-eclipse/halo-se-admin/MyMailServer.txt" );
		      fMailServerConfig.load( input );
		    }
		    catch ( IOException ex ){
		      System.err.println("Cannot open and load mail server properties file.");
		    }
		    finally {
		      try {
		        if ( input != null ) input.close();
		      }
		      catch ( IOException ex ){
		        System.err.println( "Cannot close mail server properties file." );
		      }
		    }
		  }
	public static void sendEmail(
		    String aFromEmailAddr, String aToEmailAddr,
		    String aSubject, String aBody
		  ){
		    //Here, no Authenticator argument is used (it is null).
		    //Authenticators are used to prompt the user for user
		    //name and password.
		    Session session = Session.getDefaultInstance( fMailServerConfig, null );
		    MimeMessage message = new MimeMessage( session );
		    try {
		      //the "from" address may be set in code, or set in the
		      //config file under "mail.from" ; here, the latter style is used
		      //message.setFrom( new InternetAddress(aFromEmailAddr) );
		      message.addRecipient(
		        Message.RecipientType.TO, new InternetAddress(aToEmailAddr)
		      );
		      message.addRecipient(Message.RecipientType.BCC, new InternetAddress("jbell@cs.columbia.edu"));
		      message.setSubject( aSubject );
		      message.setText( aBody );
		      Transport.send( message );
		    }
		    catch (MessagingException ex){
		      System.err.println("Cannot send email. " + ex);
		    }
		  }
}
