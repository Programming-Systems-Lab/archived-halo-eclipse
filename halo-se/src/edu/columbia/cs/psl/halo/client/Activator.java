package edu.columbia.cs.psl.halo.client;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

import javax.security.auth.login.LoginContext;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import edu.columbia.cs.psl.halo.HALOServiceFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "edu.columbia.cs.psl.halo.client"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}
//	private ILoginContext secureCtx;
	
//	public ILoginContext getSecureCtx() {
//		return secureCtx;
//	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		   URL configUrl = getBundle().getEntry("login.conf");
		   URL fileURL = FileLocator.toFileURL(configUrl);

//		secureCtx = LoginContextFactory.createContext("HALOLogin",fileURL, new LoginCallbackHandler());
//		secureCtx.login();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	public static void logBackground(final String action, final String params)
	{
		Job j = new Job("") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				HALOServiceFactory.getInstance().log(action, params);
					return Status.OK_STATUS;
			}
		};
		j.setUser(false);
		j.setSystem(true);
		j.schedule();
	}
}
