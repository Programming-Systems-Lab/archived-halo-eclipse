package edu.columbia.cs.psl.halo.client.views;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.ViewPart;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.client.Activator;
import edu.columbia.cs.psl.halo.client.FBTokenChecker;

public class DashboardView extends ViewPart {

	public static final String ID = "edu.columbia.cs.psl.halo.client.views.DashboardView";
	private boolean wasLoggedIn = false;
	public void facebookLoginUpdated(boolean nowLoggedIn)
	{
		if ((wasLoggedIn == true) && (nowLoggedIn == false)) {
			HALOServiceFactory.getInstance().getUserSvc().logoutOfFacebook();
		}
		wasLoggedIn = nowLoggedIn;
		dashboardComposite.setFBButtonText(nowLoggedIn);
	}
	
	public DashboardView() {
		// TODO Auto-generated constructor stub
	}

	LoginComposite loginComposite;
	DashboardComposite dashboardComposite;
	ScrolledComposite dashboardScroller;
	StackLayout parentLayout;
	Composite parent;

	public DashboardComposite getDashboardComposite() {
		return dashboardComposite;
	}

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
        parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		parentLayout = new StackLayout();
		parent.setLayout(parentLayout);
		loginComposite = new LoginComposite(parent, SWT.NONE, this);

		dashboardScroller = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		dashboardComposite = new DashboardComposite(dashboardScroller,
				SWT.NONE, this);
		
		dashboardScroller.setContent(dashboardComposite);
		dashboardScroller.setExpandHorizontal(true);
		dashboardScroller.setExpandVertical(true);

		dashboardScroller.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle r = dashboardScroller.getClientArea();
				dashboardScroller.setMinSize(dashboardComposite.computeSize(
						r.width, SWT.DEFAULT));
			}
		});

		parentLayout.topControl = loginComposite;
		parent.layout(true);
	}
	private Timer refreshTimer;
	void loggedIn() {
		parentLayout.topControl = dashboardScroller;
		parent.layout();
		dashboardComposite.updateWindow();
		dashboardScroller.layout(true);
		Rectangle r = dashboardScroller.getClientArea();
		dashboardScroller.setMinSize(dashboardComposite.computeSize(
				r.width, SWT.DEFAULT));
		refreshTimer = new Timer(true);
		refreshTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				updateWindow();
				IWorkbenchPage page = getSite()
						.getPage();
				QuestHUD view = (QuestHUD) page
						.findView(QuestHUD.ID);
				view.updateQuests();
			}
		}, 30000, 30000);
	}

	void loggedOut() {
		refreshTimer.cancel();
		refreshTimer = null;
		parentLayout.topControl = loginComposite;
		parent.layout(true);
	}

	public void updateWindow()
	{
		dashboardComposite.updateWindow();
	}
	@Override
	public void setFocus() {
		Activator.logBackground("DashboardViewSetFocus", null);
	}

	public static void main(String[] args) {
//		HALOServiceFactory.getInstance().login("jon", "test123");
		
		
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setBounds(200, 200, 1000, 300);
		shell.open();
		
		DashboardView s = new DashboardView();
		s.createPartControl(shell);
		
//		s.loggedIn();


		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}
}
