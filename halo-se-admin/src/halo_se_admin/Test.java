package halo_se_admin;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.ws.BindingProvider;

import org.apache.axis.client.Stub;
import org.apache.axis.message.PrefixedQName;
import org.apache.axis.message.SOAPHeaderElement;

import edu.columbia.cs.psl.halo.server.UserService;
import edu.columbia.cs.psl.halo.server.UserServiceServiceLocator;

public class Test {
	public static void main(String[] args) {
		System.out.println("Starting to connect to server");
		UserService svc;
		try {
			UserServiceServiceLocator locator = new UserServiceServiceLocator();
			svc = locator.getUserServicePort();

			((Stub) svc).setUsername("jon");
			((Stub) svc).setPassword("test");
//			((BindingProvider)svc).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "jon");
//			((BindingProvider)svc).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "test");
			System.out.println(svc.test());

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
