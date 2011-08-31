package edu.columbia.cs.psl.halo.server;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import edu.columbia.cs.psl.halo.entity.Course;
import edu.columbia.cs.psl.halo.entity.LogAction;
import edu.columbia.cs.psl.halo.entity.LogEntry;

@Stateless
@WebService
public class LogService extends AbstractFacade<LogEntry> {

	public LogService() {
		super(LogEntry.class);
		// TODO Auto-generated constructor stub
	}
	@WebMethod
	public void log(String action, String params)
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
