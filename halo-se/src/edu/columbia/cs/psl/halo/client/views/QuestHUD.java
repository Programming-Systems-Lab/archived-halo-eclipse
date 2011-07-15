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
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
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

	class QuestDetails extends Composite
	{

		Label questTitle;
		Label questDueDate;
		Label backgroundHeader;
		Label questBackground;
		Label objectivesHeader;
		Label objectivesBody;
		public void setQuest(QuestWrapper w)
		{
			questTitle.setText(w.getQuest().getName());
			questDueDate.setText("(Part of " + w.getAssignment().getTitle() + ", due " + w.getDueStrHuman() + ")");
			questBackground.setText(w.getQuest().getDescription());
			
			String objectives = "";
			for(Task t : w.getQuest().getTasks())
			{
				objectives += t.getName() + "\n";
			}
			objectivesBody.setText(objectives);
		}
		public QuestDetails(Composite parent, int style) {
			super(parent, style);
			
			this.setLayout(new GridLayout(1,true));
			questTitle = new Label(this, SWT.NONE);
			questTitle.setText("Batman's Quest");
			FontData[] fD = questTitle.getFont().getFontData();
			fD[0].setHeight(30);
			fD[0].setStyle(SWT.BOLD);
			questTitle.setFont( new Font(parent.getDisplay(),fD[0]));
			
			this.pack();
			questDueDate = new Label(this, SWT.NONE);
			questDueDate.setText("For Assignment 1, due in 3 days");
			fD[0].setHeight(20);
			fD[0].setStyle(SWT.BOLD);
			questDueDate.setFont( new Font(parent.getDisplay(),fD[0]));
			
			backgroundHeader = new Label(this, SWT.NONE);
			backgroundHeader.setText("Background");
			fD[0].setHeight(24);
			fD[0].setStyle(SWT.BOLD);
			backgroundHeader.setFont( new Font(parent.getDisplay(),fD[0]));
			
			questBackground = new Label(this, SWT.WRAP);
			questBackground.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget libero urna, eu rhoncus nisi. Suspendisse vel tortor in nibh accumsan facilisis et a diam. Suspendisse in lacus eget turpis dignissim rutrum nec sit amet mi. Praesent neque dui, lobortis vel dapibus ac, rhoncus in quam. Proin tempus volutpat imperdiet. Ut sit amet tortor consectetur velit condimentum sollicitudin. In tempus fringilla augue. Nulla facilisi. Sed et augue sit amet ante facilisis porttitor. Phasellus sed velit nibh. Integer eget metus quam, at dictum mi. Etiam fermentum vulputate tellus vel faucibus. In non quam eget augue luctus fermentum in sed nunc. Sed eget neque neque, quis tristique tortor. Mauris ultricies dignissim justo quis condimentum. Cras pellentesque, sapien et tincidunt bibendum, elit elit vehicula dolor, a ultricies turpis mauris iaculis nisl. ");
			GridData data = new GridData();
			data.widthHint=this.getBounds().width - 20;
			fD[0].setHeight(12);
			questBackground.setLayoutData(data);
			fD[0].setStyle(SWT.NORMAL);
			questBackground.setFont( new Font(parent.getDisplay(),fD[0]));
			
			objectivesHeader = new Label(this, SWT.NONE);
			objectivesHeader.setText("Objectives");
			fD[0].setHeight(24);
			fD[0].setStyle(SWT.BOLD);
			objectivesHeader.setFont( new Font(parent.getDisplay(),fD[0]));
			
			objectivesBody = new Label(this, SWT.WRAP);
			objectivesBody.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget libero urna, eu rhoncus nisi. Suspendisse vel tortor in nibh accumsan facilisis et a diam. Suspendisse in lacus eget turpis dignissim rutrum nec sit amet mi. Praesent neque dui, lobortis vel dapibus ac, rhoncus in quam. Proin tempus volutpat imperdiet. Ut sit amet tortor consectetur velit condimentum sollicitudin. In tempus fringilla augue. Nulla facilisi. Sed et augue sit amet ante facilisis porttitor. Phasellus sed velit nibh. Integer eget metus quam, at dictum mi. Etiam fermentum vulputate tellus vel faucibus. In non quam eget augue luctus fermentum in sed nunc. Sed eget neque neque, quis tristique tortor. Mauris ultricies dignissim justo quis condimentum. Cras pellentesque, sapien et tincidunt bibendum, elit elit vehicula dolor, a ultricies turpis mauris iaculis nisl. ");
			data = new GridData();
			data.widthHint=this.getBounds().width - 20;
			fD[0].setHeight(12);
			objectivesBody.setLayoutData(data);
			fD[0].setStyle(SWT.NORMAL);
			objectivesBody.setFont( new Font(parent.getDisplay(),fD[0]));
			
			parent.addListener(SWT.Resize, new Listener() {
				public void handleEvent(Event event) {
					int curWidth = questBackground.getBounds().width;
					int prefWidth = getBounds().width - 20;
					System.out.println(prefWidth);
					if (prefWidth != curWidth) {
						GridData data = (GridData)questBackground.getLayoutData();
						data.widthHint = prefWidth;
						questBackground.setLayoutData(data);
						objectivesBody.setLayoutData(data);

						pack();
					}
				}
			});
		}

		
	}
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
	private void showQuestDetails(QuestWrapper w)
	{
		System.out.println("SHowing details");
		System.out.println(w.getQuest().getName());
	}
	private QuestDetails questDetails;
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
			
		questDetails = new QuestDetails(sash, SWT.NONE);
		
		questsViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
		       if(event.getSelection() instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				if((selection.getFirstElement()) instanceof QuestWrapper)
					questDetails.setQuest((QuestWrapper) selection.getFirstElement());
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
