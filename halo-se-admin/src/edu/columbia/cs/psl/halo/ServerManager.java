package edu.columbia.cs.psl.halo;

import halo_se_admin.Activator;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServerManager {
	
	private static InitialContext ctx;
	private static void initCtx()
	{
		Properties props = new Properties();
//		props.setProperty("org.omg.CORBA.ORBInitialHost", RuntimeEnvironmentSettings.SERVER);
//		props.setProperty("com.sun.corba.ee.encoding.ORBEnableJavaSerialization","true");
//		props.setProperty("com.sun.CORBA.encoding.ORBEnableJavaSerialization", "true");
		props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
//			props.setProperty("com.sun.CORBA.giop.ORBFragmentSize","1024000");
		props.setProperty("com.sun.CORBA.giop.ORBBufferSize","1024000");
//			props("com.sun.corba.ee.transport.ORBMaximumReadByteBufferSize", "3000000");
		props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
		props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

		InitialContext ctx;
		try {
			ctx = new InitialContext(props);
			ServerManager.ctx = ctx;
		} catch (NamingException e) {
			e.printStackTrace();
			ctx = null;
		}
	}
	private static InitialContext getInitialContext()
	{
		if(ctx == null)
			initCtx();
		return ctx;
	}
	
	@SuppressWarnings("unchecked")	
	public static <T> T lookup(final Class<T> remoteInterface, final String jndiName) {
	final ClassLoader contextFinder = Thread.currentThread().getContextClassLoader(); 

	final ClassLoader ejbUtilsClassLoader = Activator.class.getClassLoader(); 

	 System.out.println("### EJBUtils loader: " + ejbUtilsClassLoader); 

	 System.out.println("### Context loader:" + contextFinder); 

	 

	Thread.currentThread().setContextClassLoader(ejbUtilsClassLoader); 

	 

	try { 

	 

	return (T) getInitialContext().lookup(jndiName);

	} catch (final NamingException e) { 

	throw new RuntimeException("Error looking up " + jndiName, e); 

	} finally { 

	Thread.currentThread().setContextClassLoader(contextFinder); 

	} 

	 

	}
}
