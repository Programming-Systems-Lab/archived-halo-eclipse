package edu.columbia.cs.psl.halo.client.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.ViewPart;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.client.wrapper.QuestWrapper;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Enrollment;
import edu.columbia.cs.psl.halo.server.stubs.EnrollmentType;
import edu.columbia.cs.psl.halo.server.stubs.Quest;
import edu.columbia.cs.psl.halo.server.stubs.QuestProgress;

public class QuestHUD extends ViewPart {
	public static final String ID = "edu.columbia.cs.psl.halo.client.views.QuestHUD";

	Composite needToLogin;

	Tree questsTree;
	Composite parent;
	public QuestHUD() {
		
	}
	private void createNeedLoginPanel()
	{
		needToLogin = new Composite(parent, SWT.NONE);
		needToLogin.setLayout(new FillLayout());
		Label goLogin = new Label(needToLogin, SWT.NONE);
		goLogin.setText("Please login to utilize HALO-SE");
	}
	private void createQuestsHUDPanel()
	{
		System.out.println("Made hud");
		questsTree = new Tree(parent, SWT.NONE);
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.horizontalAlignment=SWT.CENTER;

		questsTree.setData(data);

		updateQuests();
	}
	List<QuestWrapper> quests;
	private void updateQuests()
	{
		if(HALOServiceFactory.getInstance().isLoggedIn() && questsTree != null)
		{
			for(TreeItem i : questsTree.getItems())
				i.dispose();
			Job j = new Job("Updating quests list") {
				
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					quests  = new ArrayList<QuestWrapper>();
					HashMap<Quest, QuestProgress> progress = new HashMap<Quest, QuestProgress>();
					for(QuestProgress p : HALOServiceFactory.getInstance().getUserSvc().getMyProgress())
					{
						progress.put(p.getQuest(), p);
					}
					for(Enrollment e : HALOServiceFactory.getInstance().getUserSvc().getEnrollments())
					{
						if(e.getType().equals(EnrollmentType.STUDENT))
						{
							for(Assignment a : HALOServiceFactory.getInstance().getUserSvc().getAssignmentsFor(e.getCourse()))
							{
								for(Quest q : HALOServiceFactory.getInstance().getUserSvc().getQuestsFor(a))
								{
									QuestWrapper qw = new QuestWrapper(q, a, e.getCourse(), progress.get(q));
									quests.add(qw);
								}
							}
						}
					}
					Display.getDefault().syncExec(new Runnable() {
						
						@Override
						public void run() {
							System.out.println("Refilling");
							for(QuestWrapper w : quests)
							{
								TreeItem tr = new TreeItem(questsTree, SWT.NONE);
								tr.setText(w.getQuest().getName() );
								System.out.println(w.getQuest().getName());
								tr.setData(w);
							}
							questsTree.pack();
						}
					});
					return Status.OK_STATUS;
				}
			};
			j.schedule();
		}
	}
	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		parent.setLayout(new GridLayout());
		createNeedLoginPanel();
	}

	@Override
	public void setFocus() {
		updateQuests();
	}
	void loggedIn(){
		System.out.println("Logging in");
		needToLogin.dispose();
		createQuestsHUDPanel();
		updateQuests();
		parent.getShell().layout(true);
	}
	
	void loggedOut()
	{
		questsTree.dispose();
		createNeedLoginPanel();
	}
}
