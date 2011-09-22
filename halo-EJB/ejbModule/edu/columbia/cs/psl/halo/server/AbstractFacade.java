package edu.columbia.cs.psl.halo.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import edu.columbia.cs.psl.halo.entity.LogAction;
import edu.columbia.cs.psl.halo.entity.LogEntry;
import edu.columbia.cs.psl.halo.entity.User;


/**
 * Borrowed from Netbeans wizard :)
 * @author jon
 *
 * @param <T>
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass; 
    @PersistenceContext(unitName="halo_persist") private EntityManager em;

	 public static Object readObject(byte[] data)
	    {
	    	ObjectInputStream is;
			try {
				is = new ObjectInputStream(new ByteArrayInputStream(data));
					return is.readObject(); 
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	    }
	 public static byte[] writeObject(Object o)
	    {
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(bos);
		    	oos.writeObject(o);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return bos.toByteArray();
	    }
    protected void invalidateCache()
    {
//    	cachedUser = null;
    }
//    private User cachedUser = null;
    @Resource
    SessionContext ctx;
    protected User getUser()
    {
//    	if(cachedUser != null)
//    		return cachedUser;
//    	System.out.println("Req from " + ctx.getCallerPrincipal().getName());
    	if(ctx.getCallerPrincipal().getName().equalsIgnoreCase("anonymous"))
    		return null;
    	Query q = getEntityManager().createQuery("select object(c) from User as c where c.email=:user");
		q.setParameter("user", ctx.getCallerPrincipal().getName());
		User r = null;
		try
		{
		r = (User) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			System.err.println("Unable to find user record for logged in user " +  ctx.getCallerPrincipal().getName());
		}
		return r;
    }
  
    protected User findByUserName(String username)
	{
		Query q = getEntityManager().createQuery("select object(c) from User as c where c.username=:user");
		q.setParameter("user", username);
		User r = null;
		try
		{
		r = (User) q.getSingleResult();
		}
		catch(NoResultException e) 
		{
			
		}
		return r;
	}
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager()
    {
    	return em;
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

	protected void log(String action, String params)
	{
		LogEntry e = new LogEntry();
		e.setAction(getAction(action));
		e.setParams(params);
		e.setCreatedBy(getUser());
		e.setIp(getRemoteIP());
		e.setCreatedAt(new Date());
		getEntityManager().persist(e);
	}
	
	private LogAction getAction(String action)
	{
		Query cq = getEntityManager().createNativeQuery("SELECT * FROM LogAction where log_key=? LIMIT 1", LogAction.class);
		cq.setParameter(1, action);
		LogAction ret = null;
		try
		{
			ret = (LogAction) cq.getSingleResult();
		}
		catch(Exception ex)
		{
			ret = new LogAction();
			ret.setKey(action);
			getEntityManager().persist(ret);
		}
		return ret;
	}
	
	@Resource
	WebServiceContext wsContex;
	protected long getRemoteIP()
	{
		if(wsContex == null)
			return 0;

		try
		{
		MessageContext mc = wsContex.getMessageContext();
		HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 
		String[] addrArray = req.getRemoteAddr().split("\\.");

		long num = 0;
		for (int i=0;i<addrArray.length;i++) {
			int power = 3-i;

			num += ((Integer.parseInt(addrArray[i])%256 * Math.pow(256,power)));
		}
		return num;
		}
		catch(Exception ex)
		{
			return 0;
		}
	}
}
