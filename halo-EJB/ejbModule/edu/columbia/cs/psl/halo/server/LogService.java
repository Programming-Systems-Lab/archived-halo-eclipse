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
		super.log(action, params);
	}
}
