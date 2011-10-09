package edu.columbia.cs.psl.halo.client;

import java.awt.Toolkit;
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
		
		timer.schedule(new FBCheckTask(), 0 ,seconds * 1000);
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
					try {
						HALOServiceFactory.getInstance().refreshMe();
						u= HALOServiceFactory.getInstance().getMe();
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