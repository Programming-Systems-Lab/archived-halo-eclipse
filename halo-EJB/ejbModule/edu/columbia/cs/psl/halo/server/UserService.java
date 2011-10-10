package edu.columbia.cs.psl.halo.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.rpc.ServiceException;

import org.eclipse.persistence.internal.sessions.UnitOfWorkImpl;

import com.crowsoftware.jira.soap.JiraSoapService;
import com.crowsoftware.jira.soap.JiraSoapServiceServiceLocator;
import com.crowsoftware.jira.soap.RemoteAuthenticationException;
import com.crowsoftware.jira.soap.RemoteComponent;
import com.crowsoftware.jira.soap.RemoteIssue;
import com.google.code.facebookapi.FacebookException;
import com.google.code.facebookapi.FacebookJsonRestClient;

import edu.columbia.cs.psl.halo.entity.Achievement;
import edu.columbia.cs.psl.halo.entity.AchievementRecord;
import edu.columbia.cs.psl.halo.entity.Assignment;
import edu.columbia.cs.psl.halo.entity.CausualRelation;
import edu.columbia.cs.psl.halo.entity.Course;
import edu.columbia.cs.psl.halo.entity.Enrollment;
import edu.columbia.cs.psl.halo.entity.Level;
import edu.columbia.cs.psl.halo.entity.Quest;
import edu.columbia.cs.psl.halo.entity.QuestProgress;
import edu.columbia.cs.psl.halo.entity.Task;
import edu.columbia.cs.psl.halo.entity.Title;
import edu.columbia.cs.psl.halo.entity.User;
import com.crowsoftware.jira.soap.RemoteException;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@WebService
@RolesAllowed("USER") 
public class UserService extends AbstractFacade<User> implements UserServiceRemote {
	
    /**
     * Default constructor. 
     */
    public UserService() {
        super(User.class);
    }
    @WebMethod
    public void setPassword(String s)
    {
    	User me = getUser();
    	me.setPassword(s);
    	getEntityManager().merge(me);
    }
    
    /**
     * If completing this task completes a quest, this returns any results
     * @param t
     * @return
     */
    @WebMethod
    public String markTaskCompleted(Task pT)
    {
    	Task t = getEntityManager().find(Task.class, pT.getId());
    	QuestProgress qp = getMyProgressFor(t);
    	qp.setUpdated(new Date());
    	qp.setCompleted(true);
    	getEntityManager().merge(qp);
    	String ret = handleResults(t.getResultsIn());
    	User me = getUser();
    	me.setXp(me.getXp() + t.getQuest().getExperiencePoints());
    	if(t.getQuest().getExperiencePoints() > 0)
    		ret += "You've received " + t.getQuest().getExperiencePoints() + " XP\n";
    	if(me.getLevel().getXpMax() <= me.getXp())
    	{
    		me.setLevel(getLevel(me.getLevel().getLevel() + 1));
    		ret += "You've reached level " + me.getLevel().getLevel()+", hooray!\n";
    	}
    	getEntityManager().merge(me);
    	
    	return ret;
    }
    
    @WebMethod
    public Set<QuestProgress> getMyProgressFor(Quest q)
    {
    	User me = getUser();
    	Query qu = getEntityManager().createNativeQuery("select * FROM questprogress where quest_id=? and user_id=?",QuestProgress.class);
    	qu.setParameter(1, q.getId());
    	qu.setParameter(2, getUser().getId());

    	HashSet<QuestProgress> progress = new HashSet<QuestProgress>();
    	try{
    		List<QuestProgress> res = qu.getResultList();
    		for(QuestProgress qp : res)
    		{
    			progress.add(qp);
    		}
    	}
    	catch(NoResultException ex)
    	{
    		
    	}
    	
    	return progress;
    }
    
    private QuestProgress getMyProgressForNoCreate(Task t)
    {
    	User me = getUser();
    	Query qu = getEntityManager().createNativeQuery("select * FROM questprogress where task_id=? and user_id=?",QuestProgress.class);
    	qu.setParameter(1, t.getId());
    	qu.setParameter(2, getUser().getId());

    	try{
    		return (QuestProgress) qu.getSingleResult();
    	}
    	catch(NoResultException ex)
    	{
    		return null;
    	}
    	
    }
    private QuestProgress getMyProgressFor(Task t)
    {
    	User me = getUser();
    	Query qu = getEntityManager().createNativeQuery("select * FROM questprogress where task_id=? and user_id=?",QuestProgress.class);
    	qu.setParameter(1, t.getId());
    	qu.setParameter(2, getUser().getId());

    	try{
    		return (QuestProgress) qu.getSingleResult();
    	}
    	catch(NoResultException ex)
    	{
    		QuestProgress qp = new QuestProgress();
    		qp.setUser(me);
    		qp.setQuest(t.getQuest());
    		qp.setTask(t);
    		qp.setUpdated(new Date());
    		qp.setCompleted(false);
    		getEntityManager().persist(qp);
    		return qp;
    	}
    	
    }
    private String handleResults(List<CausualRelation> relations)
    {
    	String ret ="";
    	for(CausualRelation r : relations)
    	{
    		if(r.getAchievementResult() != null)
    		{
    			AchievementRecord rec = new AchievementRecord();
    			rec.setAchievement(r.getAchievementResult());
    			rec.setUser(getUser());
    			rec.setCompletionTime(new Date());
    			getEntityManager().persist(rec);
    			ret = ret + "Congratulations, you've unlocked the achievement \""+r.getAchievementResult().getName()+"\"!\n";
    			User me = getUser();
    			if(r.getAchievementResult().getResultTitle() != null)
    			{
    				me.getTitles().add(r.getAchievementResult().getResultTitle());
    				if(me.getActiveTitle() == null)
    					me.setActiveTitle(r.getAchievementResult().getResultTitle());
    				ret = ret + "Congratulations, you've unlocked the title \""+r.getAchievementResult().getResultTitle().getTitle()+"\"!\n";
    			}
    			me.setAchievementPoints(me.getAchievementPoints() + r.getAchievementResult().getPoints());
    			getEntityManager().merge(me);
    		}
    		if(r.getTaskResult() != null)
    		{
    			if(getMyProgressForNoCreate(r.getTaskResult()) == null)
    				ret = ret + "You've unlocked the task \""+r.getTaskResult().getName()+"\", in the quest \""+r.getTaskResult().getQuest().getName()+"\"!\n";
    			getMyProgressFor(r.getTaskResult());

    		}
    	}
    	return ret;
    }

    @WebMethod
    public boolean setDefaultTitle(Title t)
    {
    	User u = getUser();
		u.setActiveTitle(t);
		getEntityManager().merge(u);
    	return true;
    }
    
    @WebMethod
    public Byte[] getMyProfileImage()
    {
    	return null; //TODO
    }
    
    @WebMethod
    public Byte[] getProfileImage(User u)
    {
    	return null; //TODO
    }
    
    @WebMethod
    public void setProfileImage(Byte[] img)
    {
    	//TODO
    }
    
    @WebMethod
    public Level getLevel(int i)
    {
    	Query q = getEntityManager().createNativeQuery("select * FROM level where level=?", Level.class);
    	q.setParameter(1, i);
    	try{
    		return (Level) q.getSingleResult();    		
    	}
    	catch(NoResultException ex)
    	{
    		return null;
    	}
    }
    
    @WebMethod
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Achievement> getAllAchievements()
    {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Achievement.class));
        return getEntityManager().createQuery(cq).getResultList();
    }
    @WebMethod
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Quest> getAllQuests()
    {
    	CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Quest.class));
        return getEntityManager().createQuery(cq).getResultList();
    }
    @WebMethod
    public List<Title> getMyTitles()
    {
    	User u = getUser();
    	return u.getTitles();
    }
    @WebMethod
    public List<AchievementRecord> getMyAchievements()
    {
    	User u = getUser();
    	return u.getAchievements();
    }
    @WebMethod
    public List<Assignment> getAssignmentsFor(Course c)
    {
    	getEntityManager().getEntityManagerFactory().getCache().evictAll();
    	System.out.println(c);
    	System.out.println(c.getId());
    	c = getEntityManager().find(Course.class, c.getId());
    	return c.getAssignments();
    }
    @WebMethod
    @SuppressWarnings("unchecked")
	public List<Quest> getQuestsFor(Assignment a)
    {
    	getEntityManager().getEntityManagerFactory().getCache().evictAll();
    	Query q = getEntityManager().createNativeQuery("select q.* FROM quest q inner join questprogress qp on qp.quest_id=q.id where qp.user_id=? and q.assignment_id = ? group by q.id", Quest.class);
    	q.setParameter(1, getUser().getId());
    	q.setParameter(2, a.getId());
    	return q.getResultList();
    }
    @WebMethod
    @SuppressWarnings("unchecked")
   	public List<Quest> getAllQuestsFor(Assignment a)
    {
       	getEntityManager().getEntityManagerFactory().getCache().evictAll();
       	Query q = getEntityManager().createNativeQuery("select q.* FROM quest q where q.assignment_id = ?", Quest.class);
       	q.setParameter(1, a.getId());
       	return q.getResultList();
    }
    @WebMethod
    public int getMaxAchievementPts()
    {
    	Query q= getEntityManager().createNativeQuery("SELECT sum(points) from achievement");
    	return Integer.parseInt(((java.math.BigDecimal) q.getSingleResult()).toBigInteger().toString());
    }
    
    @WebMethod
    public List<QuestProgress> getMyProgress()
    {
    	User u = getUser();
    	return u.getProgress();
    }
    
    @WebMethod
    public List<Enrollment> getEnrollments()
    {
    	User u = getUser();
    	return u.getEnrollments();
    }    

    @PermitAll
    @WebMethod
    public User getMe()
    {
    	return getUser();
    }
    private final static String HEX_DIGITS = "0123456789abcdef";

	private String getEncryptedPassword(String plaintext) {
		
		java.security.MessageDigest d =null;
				try {
					d = java.security.MessageDigest.getInstance("SHA-1");
				} catch (NoSuchAlgorithmException e) {
				}
				d.reset();
				d.update(plaintext.getBytes());
				byte[] hashedBytes =  d.digest();
				StringBuffer sb = new StringBuffer(hashedBytes.length * 2);
		        for (int i = 0; i < hashedBytes.length; i++) {
		             int b = hashedBytes[i] & 0xFF;
		             sb.append(HEX_DIGITS.charAt(b >>> 4)).append(HEX_DIGITS.charAt(b & 0xF));
		        }
		        return sb.toString();	
	}
	private String createNewToken()
	{
		User u = getUser();
    	String r = "<" + getEncryptedPassword(u.getEmail() + System.currentTimeMillis() + "frakuyeDR6p87r");
    	Query q = getEntityManager().createNativeQuery("UPDATE h_user SET REMEMBERME=?, REMEMBERMEEXP=CURRENT_TIMESTAMP() WHERE id=?");
    	q.setParameter(1, r);
    	q.setParameter(2, u.getId());
    	q.executeUpdate();
    	return r;
	}
	private void updateTokenExp()
	{
		User u = getUser();
    	Query q = getEntityManager().createNativeQuery("UPDATE h_user SET REMEMBERMEEXP=CURRENT_TIMESTAMP() WHERE id=?");
    	q.setParameter(1, u.getId());
    	q.executeUpdate();
    	
	}
	
    @WebMethod
    public String getRememberMeToken()
    {
    	String r;
    	User u = getUser();
    	Query q = getEntityManager().createNativeQuery("select REMEMBERME from h_user where remembermeexp > date_add(current_timestamp(),interval -10 day) and id=?");
    	q.setParameter(1, u.getId());
    	try{
    		r = (String) q.getSingleResult();
    		if(r == null)
    			r =createNewToken();
    		updateTokenExp();
    	}
    	catch(NoResultException ex)
    	{
    		r = createNewToken();
    	}
    	
    	return r;
    }
    
    @WebMethod
    public boolean postQuestCompletionToFacebook(Quest q)
    {
    	User me = getUser();
    	
    	 String FB_APP_API_KEY = new String("191177150954478");
    	 String FB_APP_SECRET = new String("ecd307ec8c6fc5531b44c4f0d20f00e6");
    	    String FB_SESSION_KEY = new String(me.getFacebookSessionKey());
    	FacebookJsonRestClient facebook = new FacebookJsonRestClient( FB_APP_API_KEY, FB_APP_SECRET, FB_SESSION_KEY );
    	 	        FacebookJsonRestClient facebookClient = (FacebookJsonRestClient)facebook;
    	 	try {
    	 		String msg = "I just completed the " + q.getName() + " Quest on HALO-SE.";
    	 		String fbResult = facebookClient.stream_publish(msg, null, null, null, null);
			} catch (FacebookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return true;
    }
    
    @WebMethod
    public boolean postTaskCompletionToFacebook(Task t)
    {
    	User me = getUser();
    	t=getEntityManager().find(Task.class, t.getId());
    	 String FB_APP_API_KEY = new String("191177150954478");
    	 String FB_APP_SECRET = new String("ecd307ec8c6fc5531b44c4f0d20f00e6");
    	    String FB_SESSION_KEY = new String(me.getFacebookSessionKey());
    	FacebookJsonRestClient facebook = new FacebookJsonRestClient( FB_APP_API_KEY, FB_APP_SECRET, FB_SESSION_KEY );
    	 	        FacebookJsonRestClient facebookClient = (FacebookJsonRestClient)facebook;
    	 	try {
    	 		String msg = "I just completed the task \"" + t.getName() + "\" (part of quest \""+t.getQuest().getName()+"\") on HALO-SE.";
    	 		String fbResult = facebookClient.stream_publish(msg, null, null, null, null);
			} catch (FacebookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return true;
    }
    
    @WebMethod
    public void logoutOfFacebook()
    {
    	User me = getUser();
    	int uid = me.getId();
    	User u = getEntityManager().find(User.class, uid);
    	u.setFacebookSessionKey(null);
    	getEntityManager().merge(me);
    }
    
    @PermitAll
	@Override
	public void setFBToken(int userid, String token, String webSecret) {
		if(webSecret.equals("WrAse3RAFRa86zUdafRupRatEwUBecEzadUpRenuMAXebrubuphaCeCHuGed5eru"))
		{
			User u = getEntityManager().find(User.class, userid);
			u.setFacebookSessionKey(token);
			getEntityManager().merge(u);
		}
	}
    

    @WebMethod(exclude=true)
    @AroundInvoke
    public Object handleException(InvocationContext ctx) throws Exception {
    	try{
          return ctx.proceed( );
    	}
    	catch(Exception ex)
    	{
    		reportError(ex);
    		throw ex;
    	}
    }
    public static void reportError(Exception ex)
	{
		reportError(ex,null);
	}
	private static JiraSoapService svc = null;

	@WebMethod
	public String getLeadersStr()
	{
		return "(By XP points; top 10)\n1. Jon Bell (300) \n2. Swapneel Sheth (200) \n3. Nipun Arora (150)";
	}
	public static void reportError(Exception ex, String message)
	{
		if(svc == null)
		{
			try {
				svc = new JiraSoapServiceServiceLocator().getJirasoapserviceV2(new URL("http://ase.cs.columbia.edu/jira/rpc/soap/jirasoapservice-v2?wsdl"));
			} catch (ServiceException e) {
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			String token = svc.login("rpc", "3uJaMaYefradR6s3T97vare");
			RemoteIssue issue = new RemoteIssue();
			
			StringWriter stackTrace = new StringWriter();
		    ex.printStackTrace(new PrintWriter(stackTrace));
		    issue.setSummary("HALO: " + (ex.getMessage() == null ? "" : ex.getMessage().substring(0, (ex.getMessage().length() > 150 ? 150 : ex.getMessage().length()))));
			issue.setDescription("Error from HALO: " +"\n"+stackTrace.getBuffer() + (message == null ? "" : "\nState: "+message));
			issue.setProject("OPS");
			issue.setType("1");
			RemoteComponent[] p = new RemoteComponent[1];
			p[0] = new RemoteComponent();
			p[0].setId(""+10100);
			issue.setComponents(p);
			svc.createIssue(token, issue);
			svc.logout(token);
		} catch (RemoteAuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.rmi.RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
