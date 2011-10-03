package edu.columbia.cs.psl.halo.client;

import java.awt.Toolkit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.widgets.Display;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.client.views.DashboardView;
import edu.columbia.cs.psl.halo.server.stubs.User;

public class FBTokenChecker {
	Toolkit toolkit;
	Timer timer;
	User u;
	DashboardView parentView;
	
	public FBTokenChecker(int seconds, DashboardView parentView) {
		toolkit = Toolkit.getDefaultToolkit();
		timer = new Timer();
		seconds = 2;
		u= HALOServiceFactory.getInstance().getMe();
		timer.schedule(new FBCheckTask(), seconds * 1000);
		this.parentView=parentView;
	}
	public void stop()
	{
		timer.cancel();
	}
	class FBCheckTask extends TimerTask {
		public void run() {
			Display.getDefault().syncExec(new Runnable() {
				
				@Override
				public void run() {
					Date now = new Date();
					System.out.println("running FBCheckTask at "+ now.toString());
					try {
						if (u.isFBKeyFlag())
							parentView.facebookLoginUpdated(true);
						else
							parentView.facebookLoginUpdated(false);
					} catch (NullPointerException npe) {
						npe.printStackTrace();
					}
				}
			});
		}
	}
}