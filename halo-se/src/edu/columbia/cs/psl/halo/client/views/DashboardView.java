package edu.columbia.cs.psl.halo.client.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import edu.columbia.cs.psl.halo.HALOServiceFactory;

public class DashboardView extends ViewPart {

	public static final String ID = "edu.columbia.cs.psl.halo.client.views.DashboardView";
	public DashboardView() {
		// TODO Auto-generated constructor stub
	}
	LoginComposite loginComposite;
	DashboardComposite dashboardComposite;
	StackLayout parentLayout;
	Composite parent;
	
	public DashboardComposite getDashboardComposite() {
		return dashboardComposite;
	}
	
	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		parentLayout = new StackLayout();
		parent.setLayout(parentLayout);
		loginComposite = new LoginComposite(parent, SWT.NONE,this);
		dashboardComposite = new DashboardComposite(parent, SWT.NONE, this);
		parentLayout.topControl=loginComposite;
		parent.layout(true);
	}

	void loggedIn(){
		parentLayout.topControl = dashboardComposite;
		parent.pack();
		dashboardComposite.updateWindow();
		parent.layout(true);
	}
	
	void loggedOut()
	{
		parentLayout.topControl = loginComposite;
		parent.layout(true);
	}
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		HALOServiceFactory.getInstance().login("jon", "test123");

		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setBounds(200, 200, 1000, 300);
		DashboardView s = new DashboardView();
		s.createPartControl(shell);
		s.loggedIn();

		shell.open ();

		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
		
	}
}
