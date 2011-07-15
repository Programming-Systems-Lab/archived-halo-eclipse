package edu.columbia.cs.psl.halo.client.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.client.wrapper.QuestWrapper;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Enrollment;
import edu.columbia.cs.psl.halo.server.stubs.EnrollmentType;
import edu.columbia.cs.psl.halo.server.stubs.Quest;
import edu.columbia.cs.psl.halo.server.stubs.QuestProgress;
import edu.columbia.cs.psl.halo.server.stubs.Task;

public class QuestHUD extends ViewPart {
	public static final String ID = "edu.columbia.cs.psl.halo.client.views.QuestHUD";

	class ViewContentProvider implements IStructuredContentProvider, 
										   ITreeContentProvider {
		private Set<QuestWrapper> invisibleRoot;

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			if (parent.equals("root")) {
				initialize();
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}
		public Object getParent(Object child) {
			if (child instanceof QuestWrapper) {
				return invisibleRoot;
			}
			return null;
		}
		public Object [] getChildren(Object parent) {
			if (parent instanceof QuestWrapper) {
				return ((QuestWrapper)parent).getQuest().getTasks().toArray();
			}
			else if(parent == invisibleRoot)
				return invisibleRoot.toArray();
			return new Object[0];
		}
		public boolean hasChildren(Object parent) {
			if (parent instanceof QuestWrapper)
				return ((QuestWrapper)parent).getQuest().getTasks().size() > 0;
			else if(parent == invisibleRoot)
				return invisibleRoot.size() > 0;
			return false;
		}
		
		private void initialize() {
			invisibleRoot = new HashSet<QuestWrapper>();
			if(quests != null)
				for(QuestWrapper w : quests)
				{
					System.out.println(w.getQuest().getName());
					invisibleRoot.add(w);
				}

		}
	}
	class ViewLabelProvider extends LabelProvider {
		public String getText(Object obj) {
			if(obj instanceof QuestWrapper)
			{
				QuestWrapper w = (QuestWrapper) obj;
				return w.getQuest().getName() +" (due " + w.getDueStrHuman() +")";
			}
			else if(obj instanceof Task)
			{
				Task t = (Task) obj;
				return t.getName();
			}
			return obj.toString();
		}
		public Image getImage(Object obj) {
			return null;
//			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
//			if (obj instanceof TreeParent)
//			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
//			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}
	class NameSorter extends ViewerSorter {
	}
	
	Composite needToLogin;
	Composite loggedInPanel;

	Tree questsTree;
	TreeViewer questsViewer;
	
	Composite parent;
	public QuestHUD() {
		
	}
	
	public static void main(String[] args) {
		HALOServiceFactory.getInstance().login("jon", "test123");

		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setBounds(200, 200, 300, 600);
		QuestHUD s = new QuestHUD();
		s.createPartControl(shell);
		s.loggedIn();
		shell.open ();

		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
		
	}
	private void createNeedLoginPanel()
	{
		needToLogin = new Composite(parent, SWT.NONE);
		needToLogin.setLayout(new FillLayout());
		Label goLogin = new Label(needToLogin, SWT.NONE);
		goLogin.setText("Please login to utilize HALO-SE");
	}
	private StyledText questsDetails;
	private void showQuestDetails(QuestWrapper w)
	{
		System.out.println("SHowing details");
		questsDetails.setText("<html><b>Test</b></html>");
		System.out.println(w.getQuest().getName());
	}
	private void createQuestsHUDPanel()
	{
		loggedInPanel = new Composite(parent,SWT.BORDER);
		loggedInPanel.setLayout(new FillLayout());
		SashForm sash  = new SashForm(loggedInPanel, SWT.VERTICAL);

		questsViewer = new CheckboxTreeViewer(sash,SWT.H_SCROLL | SWT.V_SCROLL);
		questsViewer.setContentProvider(new ViewContentProvider());
		questsViewer.setLabelProvider(new ViewLabelProvider());
		questsViewer.setSorter(new NameSorter());
		questsViewer.setInput("root");
		
		questsDetails = new StyledText(sash, SWT.None);
		questsDetails.setText("Select a quest to view its details");
		
		
		questsViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
		       if(event.getSelection() instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				if((selection.getFirstElement()) instanceof QuestWrapper)
					showQuestDetails((QuestWrapper) selection.getFirstElement());
		       }

//				else
//					System.out.println(questsViewer.getSelection().);
			}
		});

		updateQuests();
	}
	List<QuestWrapper> quests;
	
	private Boolean updatingFlag = Boolean.FALSE;
	
	private void updateQuests()
	{
		synchronized (updatingFlag) {
			if(updatingFlag)
				return;
			updatingFlag = true;
		
		if(!HALOServiceFactory.getInstance().isLoggedIn())
			System.out.println("Not logged in yet");
		if(HALOServiceFactory.getInstance().isLoggedIn() && questsViewer != null)
		{

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
									System.out.println(qw);
									quests.add(qw);
								}
							}
						}
					}
					Display.getDefault().syncExec(new Runnable() {
						
						@Override
						public void run() {
							System.out.println(quests);
							questsViewer.refresh();
							questsViewer.expandAll();
							System.out.println("Refreshed");
							updatingFlag = false;
						}
					});
					return Status.OK_STATUS;
				}
			};
			j.schedule();
		}
		else
			updatingFlag = false;
		}
	}
	private StackLayout layout;
	
	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		this.layout = new StackLayout();
		parent.setLayout(this.layout);
		

		createNeedLoginPanel();
		this.layout.topControl = needToLogin;
		createQuestsHUDPanel();
//		this.layout.topControl = loggedInPanel;
	}
	

	@Override
	public void setFocus() {
		updateQuests();
	}
	void loggedIn(){
		System.out.println("Logging in");
		this.layout.topControl = loggedInPanel;

		updateQuests();
		parent.layout();
		updateQuests();
	}
	
	void loggedOut()
	{
		questsTree.dispose();
		createNeedLoginPanel();
	}
}
