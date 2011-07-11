package edu.columbia.cs.psl.halo.client.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class DashboardView extends ViewPart {

	public static final String ID = "edu.columbia.cs.psl.halo.client.views.DashboardView";
	public DashboardView() {
		// TODO Auto-generated constructor stub
	}
	LoginComposite loginComposite;
	DashboardComposite dashboardComposite;
	Composite parent;
	
	public DashboardComposite getDashboardComposite() {
		return dashboardComposite;
	}
	
	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		loginComposite = new LoginComposite(parent, SWT.NONE,this);
	}

	void loggedIn(){
		loginComposite.dispose();
		dashboardComposite = new DashboardComposite(parent, SWT.NONE, this);
	}
	
	void loggedOut()
	{
		dashboardComposite.dispose();
		loginComposite = new LoginComposite(parent, SWT.NONE, this);
	}
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
