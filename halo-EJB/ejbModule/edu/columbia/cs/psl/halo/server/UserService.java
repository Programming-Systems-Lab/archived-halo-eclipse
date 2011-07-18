package edu.columbia.cs.psl.halo.server;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import edu.columbia.cs.psl.halo.entity.Achievement;
import edu.columbia.cs.psl.halo.entity.AchievementRecord;
import edu.columbia.cs.psl.halo.entity.Assignment;
import edu.columbia.cs.psl.halo.entity.Course;
import edu.columbia.cs.psl.halo.entity.Enrollment;
import edu.columbia.cs.psl.halo.entity.Level;
import edu.columbia.cs.psl.halo.entity.Quest;
import edu.columbia.cs.psl.halo.entity.QuestProgress;
import edu.columbia.cs.psl.halo.entity.Title;
import edu.columbia.cs.psl.halo.entity.User;
import edu.columbia.cs.psl.halo.entity.UserStatus;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@WebService
//@RolesAllowed("USER") 
public class UserService extends AbstractFacade<User>  {
	
    /**
     * Default constructor. 
     */
    public UserService() {
        super(User.class);
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
    	c = getEntityManager().find(Course.class, c.getId());
    	return c.getAssignments();
    }
    public List<Quest> getQuestsFor(Assignment a)
    {
    	getEntityManager().getEntityManagerFactory().getCache().evictAll();
    	a = getEntityManager().find(Assignment.class, a.getId());
    	return a.getQuests();
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
    	Query q = getEntityManager().createNativeQuery("UPDATE h_user SET REMEMBERME=? WHERE id=?");
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
    	Query q = getEntityManager().createNativeQuery("select REMEMBERME from h_user where remembermeexp > date_add(current_timestamp(),interval -10 day) and id=?", String.class);
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
