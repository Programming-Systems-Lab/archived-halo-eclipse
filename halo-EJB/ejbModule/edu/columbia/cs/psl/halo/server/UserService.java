package edu.columbia.cs.psl.halo.server;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

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

/**
 * Session Bean implementation class UserService
 */
@Stateless
@WebService
@RolesAllowed("USER") 
public class UserService extends AbstractFacade<User>  {
	
    /**
     * Default constructor. 
     */
    public UserService() {
        super(User.class);
    }

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
    public void markTaskCompleted(Task pT)
    {
    	Task t = getEntityManager().find(Task.class, pT.getId());
    	QuestProgress qp = getMyProgressFor(t);
    	qp.setUpdated(new Date());
    	qp.setCompleted(true);
    	getEntityManager().merge(qp);
    	handleResults(t.getResultsIn());
    	User me = getUser();
    	me.setXp(me.getXp() + t.getQuest().getExperiencePoints());
    	if(me.getLevel().getXpMax() <= me.getXp())
    	{
    		me.setLevel(getLevel(me.getLevel().getLevel() + 1));
    	}
    	getEntityManager().merge(me);
    }
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
    private void handleResults(List<CausualRelation> relations)
    {
    	for(CausualRelation r : relations)
    	{
    		if(r.getAchievementResult() != null)
    		{
    			AchievementRecord rec = new AchievementRecord();
    			rec.setAchievement(r.getAchievementResult());
    			rec.setUser(getUser());
    			rec.setCompletionTime(new Date());
    			getEntityManager().persist(rec);

    			User me = getUser();
    			if(r.getAchievementResult().getResultTitle() != null)
    			{
    				me.getTitles().add(r.getAchievementResult().getResultTitle());
    			}
    			me.setAchievementPoints(me.getAchievementPoints() + r.getAchievementResult().getPoints());
    			getEntityManager().merge(me);
    		}
    		if(r.getTaskResult() != null)
    		{
    			getMyProgressFor(r.getTaskResult());
    		}
    	}
    }

    public boolean setDefaultTitle(Title t)
    {
    	User u = getUser();
    	if(u.getTitles().contains(t))
    	{
    		u.setActiveTitle(t);
    		getEntityManager().merge(u);
    		return true;
    	}
    	return false;
    }
    public Byte[] getMyProfileImage()
    {
    	return null; //TODO
    }
    public Byte[] getProfileImage(User u)
    {
    	return null; //TODO
    }
    public void setProfileImage(Byte[] img)
    {
    	//TODO
    }
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
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Achievement> getAllAchievements()
    {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Achievement.class));
        return getEntityManager().createQuery(cq).getResultList();
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Quest> getAllQuests()
    {
    	CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Quest.class));
        return getEntityManager().createQuery(cq).getResultList();
    }
    public List<Title> getMyTitles()
    {
    	User u = getUser();
    	return u.getTitles();
    }
    public List<AchievementRecord> getMyAchievements()
    {
    	User u = getUser();
    	return u.getAchievements();
    }
    public List<Assignment> getAssignmentsFor(Course c)
    {
		getEntityManager().getEntityManagerFactory().getCache().evictAll();

    	c = getEntityManager().find(Course.class, c.getId());
    	return c.getAssignments();
    }
    @SuppressWarnings("unchecked")
	public List<Quest> getQuestsFor(Assignment a)
    {
    	getEntityManager().getEntityManagerFactory().getCache().evictAll();
    	Query q = getEntityManager().createNativeQuery("select q.* FROM quest q inner join questprogress qp on qp.quest_id=q.id where qp.user_id=? and q.assignment_id = ? group by q.id", Quest.class);
    	q.setParameter(1, getUser().getId());
    	q.setParameter(2, a.getId());
    	return q.getResultList();
    }
    @SuppressWarnings("unchecked")
   	public List<Quest> getAllQuestsFor(Assignment a)
    {
       	getEntityManager().getEntityManagerFactory().getCache().evictAll();
       	Query q = getEntityManager().createNativeQuery("select q.* FROM quest q where q.assignment_id = ?", Quest.class);
       	q.setParameter(1, a.getId());
       	return q.getResultList();
    }
    public int getMaxAchievementPts()
    {
    	Query q= getEntityManager().createNativeQuery("SELECT sum(points) from achievement");
    	return Integer.parseInt(((java.math.BigDecimal) q.getSingleResult()).toBigInteger().toString());
    }
    public List<QuestProgress> getMyProgress()
    {
    	User u = getUser();
    	return u.getProgress();
    }
    
    
    public List<Enrollment> getEnrollments()
    {
    	User u = getUser();
    	return u.getEnrollments();
    }    

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
}
