package edu.columbia.cs.psl.halo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.columbia.cs.psl.halo.server.UserServiceRemote;


/**
 * Servlet implementation class FacebookCallback
 */
@WebServlet("/FacebookCallback")
public class FacebookCallback extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private UserServiceRemote userSvc;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacebookCallback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String sess = request.getParameter("session");
		String user = request.getParameter("uid");
		Pattern p = Pattern.compile("\\{\"session_key\":\"([^\"]+)\"(.*)\"secret\":\"([^\"]+)\"(.*)\"sig\":\"([^\"]+)\"(.*)");
		System.out.println(sess);
		Matcher m = p.matcher(sess);

		if(m.matches())
		{
			String session_key = m.group(1);
			String secret = m.group(3);
			String sig = m.group(5);
			userSvc.setFBToken(Integer.parseInt(user), session_key, "WrAse3RAFRa86zUdafRupRatEwUBecEzadUpRenuMAXebrubuphaCeCHuGed5eru");	
		}
//		out.println("\\{\"session_key\":\"([^\"]+)\"(.*)\"secret\":\"([^\"]+)\"(.*)");
		out.println("Thanks, you're all set!");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
