package edu.columbia.cs.psl.halo.client.views;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
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
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.client.Activator;
import edu.columbia.cs.psl.halo.client.Util;
import edu.columbia.cs.psl.halo.client.wrapper.QuestWrapper;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Enrollment;
import edu.columbia.cs.psl.halo.server.stubs.EnrollmentType;
import edu.columbia.cs.psl.halo.server.stubs.Quest;
import edu.columbia.cs.psl.halo.server.stubs.QuestProgress;
import edu.columbia.cs.psl.halo.server.stubs.Task;
import edu.columbia.cs.psl.halo.server.stubs.UserService;
import edu.columbia.cs.psl.halo.server.stubs.UserServiceService;

public class QuestHUD extends ViewPart {
	private HashMap<Task, QuestProgress> cachedProgress = new HashMap<Task, QuestProgress>();

	class NameSorter extends ViewerSorter {
		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			if(e1 instanceof Assignment)
			{
				Assignment o1 = (Assignment) e1;
				Assignment o2 = (Assignment) e2;
				boolean o1Done = assignmentCompleted(o1);
				boolean o2Done = assignmentCompleted(o2);
				
				if(o1Done && ! o2Done)
					return 1;
				else if(o2Done && ! o1Done)
					return -1;
				else if(o1.getDueOn() != null && o2.getDueOn() != null)
					return o1.getDueOn().compare(o2.getDueOn());
				else
					return (o1.getId() < o2.getId() ? -1 : 1);
			}
			else if(e1 instanceof Quest)
			{
				Quest o1 = (Quest) e1;
				Quest o2 = (Quest) e2;
				boolean o1Done = questCompleted(o1);
				boolean o2Done = questCompleted(o2);
				
				if(o1Done && ! o2Done)
					return 1;
				else if(o2Done && ! o1Done)
					return -1;
				else
					return (o1.getId() < o2.getId() ? -1 : 1);
			}
			else if(e1 instanceof Task)
			{
				Task o1 = (Task) e1;
				Task o2 = (Task) e2;
				if(!cachedProgress.containsKey(o1) || !cachedProgress.containsKey(o2))
					return 0;
				boolean o1Done = cachedProgress.get(o1).isCompleted();
				boolean o2Done = cachedProgress.get(o2).isCompleted();
				
				if(o1Done && ! o2Done)
					return 1;
				else if(o2Done && ! o1Done)
					return -1;
				else
					return (o1.getId() < o2.getId() ? -1 : 1);
			}
			return super.compare(viewer, e1, e2);
		}
		private boolean assignmentCompleted(Assignment o1)
		{
			for(Quest q : o1.getQuests())
			{
				for(Task t : q.getTasks())
				{
					if(!cachedProgress.containsKey(t))
						return false;
					else if(!cachedProgress.get(t).isCompleted())
						return false;
				}
			}
			return true;
		}
		private boolean questCompleted(Quest q)
		{
				for(Task t : q.getTasks())
				{
					if(!cachedProgress.containsKey(t))
						return false;
					else if(!cachedProgress.get(t).isCompleted())
						return false;
				}
			return true;
		}
//		@Override
//		public void sort(Viewer viewer, Object[] elements) {
//			System.out.println("running sort");
//			if(elements.length > 0)
//			{
//				if(elements[0] instanceof Assignment)
//				{
//					ArrayList<Assignment> sorted = new ArrayList<Assignment>(elements.length);
//					for(Object o : elements)
//						sorted.add((Assignment) o);
//					Collections.sort(sorted, new Comparator<Assignment>() {
//
//						@Override
//						public int compare(Assignment o1, Assignment o2) {
//							boolean o1Done = assignmentCompleted(o1);
//							boolean o2Done = assignmentCompleted(o2);
//							
//							if(o1Done && ! o2Done)
//								return 1;
//							else if(o2Done && ! o1Done)
//								return -1;
//							else if(o1.getDueOn() != null && o2.getDueOn() != null)
//								return o1.getDueOn().compare(o2.getDueOn());
//							else
//								return (o1.getId() < o2.getId() ? -1 : 1);
//						}
//					});
//					for(Assignment a : sorted)
//					{
//						System.out.println(a.getTitle());
//					}
//					elements = sorted.toArray();
//				}
//				else if (elements[0] instanceof Quest) {
//					ArrayList<Quest> sorted = new ArrayList<Quest>(elements.length);
//					for(Object o : elements)
//						sorted.add((Quest) o);
//					Collections.sort(sorted, new Comparator<Quest>() {
//
//						@Override
//						public int compare(Quest o1, Quest o2) {
//							boolean o1Done = questCompleted(o1);
//							boolean o2Done = questCompleted(o2);
//							
//							if(o1Done && ! o2Done)
//								return 1;
//							else if(o2Done && ! o1Done)
//								return -1;
//							else
//								return (o1.getId() < o2.getId() ? -1 : 1);
//						}
//					});
//					System.out.println(sorted);
//					elements = sorted.toArray();
//				}
//				else if(elements[0] instanceof Task)
//				{
//					ArrayList<Task> sorted = new ArrayList<Task>(elements.length);
//					for(Object o : elements)
//						sorted.add((Task) o);
//					Collections.sort(sorted, new Comparator<Task>() {
//
//						@Override
//						public int compare(Task o1, Task o2) {
//							if(!cachedProgress.containsKey(o1) || !cachedProgress.containsKey(o2))
//								return 0;
//							boolean o1Done = cachedProgress.get(o1).isCompleted();
//							boolean o2Done = cachedProgress.get(o2).isCompleted();
//							
//							if(o1Done && ! o2Done)
//								return -1;
//							else if(o2Done && ! o1Done)
//								return 1;
//							else
//								return (o1.getId() < o2.getId() ? -1 : 1);
//						}
//					});
//					System.out.println(sorted);
//					elements = sorted.toArray();
//				}
//			}
//		}
	}

	class QuestDetails extends Composite {

		Label backgroundHeader;
		Label objectivesBody;
		Label objectivesHeader;
		Label questBackground;
		Label questDueDate;
		Label questTitle;

		public QuestDetails(Composite parent, int style) {
			super(parent, style);

			this.setLayout(new TableWrapLayout());
			questTitle = new Label(this, SWT.NONE);
			questTitle.setText("Batman's Quest");
			FontData[] fD = questTitle.getFont().getFontData();
			fD[0].setHeight(24);
			fD[0].setStyle(SWT.BOLD);
			questTitle.setFont(new Font(parent.getDisplay(), fD[0]));

			this.pack();
			questDueDate = new Label(this, SWT.WRAP);
			questDueDate.setText("For Assignment 1, due in 3 days");
			TableWrapData data = new TableWrapData();
//			data.widthHint = this.getBounds().width - 20;
			questDueDate.setLayoutData(data);
			fD[0].setHeight(16);
			fD[0].setStyle(SWT.BOLD);
			questDueDate.setFont(new Font(parent.getDisplay(), fD[0]));

			backgroundHeader = new Label(this, SWT.NONE);
			backgroundHeader.setText("Background");
			fD[0].setHeight(16);
			fD[0].setStyle(SWT.BOLD);
			backgroundHeader.setFont(new Font(parent.getDisplay(), fD[0]));

			parent.pack();

			questBackground = new Label(this, SWT.WRAP);
			questBackground
					.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget libero urna, eu rhoncus nisi. Suspendisse vel tortor in nibh accumsan facilisis et a diam. Suspendisse in lacus eget turpis dignissim rutrum nec sit amet mi. Praesent neque dui, lobortis vel dapibus ac, rhoncus in quam. Proin tempus volutpat imperdiet. Ut sit amet tortor consectetur velit condimentum sollicitudin. In tempus fringilla augue. Nulla facilisi. Sed et augue sit amet ante facilisis porttitor. Phasellus sed velit nibh. Integer eget metus quam, at dictum mi. Etiam fermentum vulputate tellus vel faucibus. In non quam eget augue luctus fermentum in sed nunc. Sed eget neque neque, quis tristique tortor. Mauris ultricies dignissim justo quis condimentum. Cras pellentesque, sapien et tincidunt bibendum, elit elit vehicula dolor, a ultricies turpis mauris iaculis nisl. ");
			data = new TableWrapData();
//			data.widthHint = this.getBounds().width - 20;
			fD[0].setHeight(11);
			questBackground.setLayoutData(data);
			fD[0].setStyle(SWT.NORMAL);
			questBackground.setFont(new Font(parent.getDisplay(), fD[0]));

			objectivesHeader = new Label(this, SWT.NONE);
			objectivesHeader.setText("Objectives");
			fD[0].setHeight(16);
			fD[0].setStyle(SWT.BOLD);
			objectivesHeader.setFont(new Font(parent.getDisplay(), fD[0]));

			objectivesBody = new Label(this, SWT.WRAP);
			objectivesBody
					.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget libero urna, eu rhoncus nisi. Suspendisse vel tortor in nibh accumsan facilisis et a diam. Suspendisse in lacus eget turpis dignissim rutrum nec sit amet mi. Praesent neque dui, lobortis vel dapibus ac, rhoncus in quam. Proin tempus volutpat imperdiet. Ut sit amet tortor consectetur velit condimentum sollicitudin. In tempus fringilla augue. Nulla facilisi. Sed et augue sit amet ante facilisis porttitor. Phasellus sed velit nibh. Integer eget metus quam, at dictum mi. Etiam fermentum vulputate tellus vel faucibus. In non quam eget augue luctus fermentum in sed nunc. Sed eget neque neque, quis tristique tortor. Mauris ultricies dignissim justo quis condimentum. Cras pellentesque, sapien et tincidunt bibendum, elit elit vehicula dolor, a ultricies turpis mauris iaculis nisl. ");
			data = new TableWrapData();
//			data.widthHint = this.getBounds().width - 20;
			fD[0].setHeight(11);
			objectivesBody.setLayoutData(data);
			fD[0].setStyle(SWT.NORMAL);
			objectivesBody.setFont(new Font(parent.getDisplay(), fD[0]));

//			parent.addListener(SWT.Resize, new Listener() {
//				public void handleEvent(Event event) {
//					int prefWidth = getShell().getBounds().width - 40;
//					// System.out.println("Pref is " + prefWidth);
////					TableWrapData data = (TableWrapData) questBackground.getLayoutData();
////					data.widthHint = prefWidth;
////					questBackground.setLayoutData(data);
////					objectivesBody.setLayoutData(data);
////					data = (GridData) questDueDate.getLayoutData();
////					data.widthHint = prefWidth;
////					questDueDate.setLayoutData(data);
//					getShell().layout(true);
//				}
//			});
		}

		public void setQuest(QuestWrapper w) {
			questTitle.setText(w.getQuest().getName());
			questDueDate.setText("(Part of " + w.getAssignment().getTitle()
					+ ", due " + w.getDueStrHuman() + ")");
			questBackground.setText(w.getQuest().getDescription());

			String objectives = "";

			for (Task t : w.getQuest().getTasks()) {
				objectives += t.getName() + "\n";
			}
			objectivesHeader.setText("Objectives");
			objectivesBody.setText(objectives);
			objectivesBody.setLayoutData(new TableWrapData());
			setLayout(new TableWrapLayout());
			parent.notifyListeners(SWT.Resize, new Event());
			Rectangle r = questDetailsScroller.getClientArea();
			questDetailsScroller.setMinSize(questDetails.computeSize(r.width,
					SWT.DEFAULT));
			getParent().layout(true);
			
		}
		public void setAssignment(Assignment w) {
			questTitle.setText(w.getTitle());
			questDueDate.setText("Due " + QuestWrapper.getDueStrHuman(w));
			questBackground.setText(w.getDescription());

			String objectives = "";
			objectivesHeader.setText("Quests");
			for (Quest t : w.getQuests()) {
				objectives += t.getName() + "\n";
			}
			objectivesBody.setText(objectives);
			Rectangle r = questDetailsScroller.getClientArea();
			questDetailsScroller.setMinSize(questDetails.computeSize(r.width,
					SWT.DEFAULT));
			parent.notifyListeners(SWT.Resize, new Event());
		}
	}

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {
		private Set<Assignment> invisibleRoot;

		public void dispose() {
		}

		public Object[] getChildren(Object parent) {
			if (parent instanceof Quest)
				return ((Quest) parent).getTasks().toArray();
			else if (parent instanceof Assignment)
				return ((Assignment) parent).getQuests().toArray();
			else if (parent == invisibleRoot)
				return invisibleRoot.toArray();
			return new Object[0];
		}

		public Object[] getElements(Object parent) {
			if (parent.equals("root")) {
				initialize();
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}

		public Object getParent(Object child) {
			if (child instanceof Quest) {
				return ((Quest) child).getAssignment();
			}
			else if(child instanceof Assignment)
				return invisibleRoot;
			return null;
		}

		public boolean hasChildren(Object parent) {
			if (parent instanceof Assignment)
				return ((Assignment) parent).getQuests().size() > 0;
			else if (parent instanceof Quest)
				return ((Quest) parent).getTasks().size() > 0;
			else if (parent == invisibleRoot)
				return invisibleRoot.size() > 0;
			return false;
		}

		private void initialize() {
			invisibleRoot = new HashSet<Assignment>();
			if (quests != null)
				for (QuestWrapper w : quests.values()) {
					invisibleRoot.add(w.getAssignment());
				}

		}

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
	}

	class ViewLabelProvider extends LabelProvider implements IFontProvider,
			IColorProvider {
		private Image checkedImage;
		private Image unCheckedImage;

		public ViewLabelProvider() {
			 ImageDescriptor desc = Activator.getImageDescriptor("icons/checkedBox.png");
//			File f = new File("icons/checkedBox.png");
//			ImageDescriptor desc;
//			try {
//				desc = ImageDescriptor.createFromURL(f.toURL());
				checkedImage = desc.createImage();
//				f = new File("icons/uncheckedBox.png");
//				desc = ImageDescriptor.createFromURL(f.toURL());
				desc = Activator.getImageDescriptor("icons/uncheckedBox.png");
				unCheckedImage = desc.createImage();
//			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

		@Override
		public void dispose() {
			super.dispose();
			checkedImage.dispose();
		}

		public Image getImage(Object obj) {
			if (obj instanceof Task) {
				if (cachedProgress.containsKey(((Task) obj))
						&& cachedProgress.get(((Task) obj)).isCompleted())
					return checkedImage;
				else
					return unCheckedImage;
			}
			return null;
		}

		public String getText(Object obj) {
			if (obj instanceof Quest) {
				Quest w = (Quest) obj;
				return w.getName();
			}
			else if(obj instanceof Assignment)
			{
				Assignment a = (Assignment) obj;
				return a.getTitle() + " (due " + QuestWrapper.getDueStrHuman(a)
						+ ")";
			}
			else if (obj instanceof Task) {
				Task t = (Task) obj;
				return t.getName();
			}
			return obj.toString();
		}

		@Override
		public Color getForeground(Object element) {
			if (element instanceof Task) {
				Task t = (Task) element;
				if (cachedProgress.containsKey(t)
						&& cachedProgress.get(t).isCompleted())
					return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
				else if (((Quest) t.getQuest().getRef()).getAssignment()
						.getDueOn() == null)
					return null;
				else if (Util.daysTillDate(t.getQuest().getAssignment()
						.getDueOn()) < 3)
					return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
				else if (Util.daysTillDate(t.getQuest().getAssignment()
						.getDueOn()) < 7)
					return Display.getCurrent()
							.getSystemColor(SWT.COLOR_YELLOW);
			}
			return null;
		}

		@Override
		public Color getBackground(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Font getFont(Object element) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public static final String ID = "edu.columbia.cs.psl.halo.client.views.QuestHUD";

	public static void main(String[] args) {
		HALOServiceFactory.getInstance().login("jon", "test123");

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setBounds(200, 200, 300, 600);
		QuestHUD s = new QuestHUD();
		s.createPartControl(shell);
		s.loggedIn();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

	private Composite detailsArea;

	private StackLayout layout;
	Composite loggedInPanel;

	Composite needToLogin;
	Composite parent;

	private QuestDetails questDetails;
	private ScrolledComposite questDetailsScroller;

	HashMap<Quest, QuestWrapper> quests;
	Tree questsTree;
	TreeViewer questsViewer;
	private Boolean updatingFlag = Boolean.FALSE;

	public QuestHUD() {

	}

	private void createNeedLoginPanel() {
		needToLogin = new Composite(parent, SWT.NONE);
		needToLogin.setLayout(new FillLayout());
		Label goLogin = new Label(needToLogin, SWT.NONE);
		goLogin.setText("Please login to utilize HALO-SE");
	}

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		this.layout = new StackLayout();
		parent.setLayout(this.layout);

		createNeedLoginPanel();
		this.layout.topControl = needToLogin;
		createQuestsHUDPanel();
	}

	private void completeTask(final Task t)
	{
		MessageDialogWithToggle dlg = MessageDialogWithToggle.openOkCancelConfirm(parent.getShell(), "Confirm completion", "Complete "+ t.getName(), "Post to Facebook", true, null, null);
		System.out.println("Done with box");
		
		if (dlg.getReturnCode() == dlg.OK) {
			if (dlg.getToggleState() == true) {
				HALOServiceFactory.getInstance().getUserSvc().postTaskCompletionToFacebook(t);
			}
			Activator.logBackground("TaskCompleteConfirmed", "" + t.getId());
			Job j = new Job("Marking task completed") {
				
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					HALOServiceFactory.getInstance().getUserSvc().markTaskCompleted(t);
					Display.getDefault().syncExec(new Runnable() {
						
						@Override
						public void run() {
							updateQuests();
							
						}
					});
					return Status.OK_STATUS;
				}
			};
			j.schedule();

		}
	}
	private StackLayout detailsLayout;
	private Label noQuestSelected;

	private void createQuestsHUDPanel() {
		loggedInPanel = new Composite(parent, SWT.BORDER);
		loggedInPanel.setLayout(new FillLayout());
		SashForm sash = new SashForm(loggedInPanel, SWT.VERTICAL);

		questsViewer = new TreeViewer(sash, SWT.H_SCROLL | SWT.V_SCROLL);
		questsViewer.setContentProvider(new ViewContentProvider());
		questsViewer.setLabelProvider(new ViewLabelProvider());
		questsViewer.setSorter(new NameSorter());
		questsViewer.setInput("root");
		questsViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {

					}
				});

		questsViewer.getTree().addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				for (TreeItem item : questsViewer.getTree().getSelection()) {
					if (item.getImage() != null) {
						if ((e.x > item.getImageBounds(0).x)
								&& (e.x < (item.getImageBounds(0).x + item
										.getImage().getBounds().width))) {
							if ((e.y > item.getImageBounds(0).y)
									&& (e.y < (item.getImageBounds(0).y + item
											.getImage().getBounds().height))) {
								if (item.getData() instanceof Task) {
									Task t = (Task) item.getData();
									if(!cachedProgress.containsKey(t))
									{
										completeTask(t);
									}
									else if(!cachedProgress.get(t).isCompleted())
									{
										completeTask(t);
									}
									questsViewer.refresh();
								}
							}
						}
					}
				}
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		detailsArea = new Composite(sash, SWT.BORDER);
		detailsLayout = new StackLayout();
		detailsArea.setLayout(detailsLayout);
		noQuestSelected = new Label(detailsArea, SWT.WRAP);
		noQuestSelected
				.setText("Please select a quest or task to view information about it");
		detailsLayout.topControl = noQuestSelected;
		questDetailsScroller = new ScrolledComposite(detailsArea, SWT.V_SCROLL
				| SWT.H_SCROLL);

		questDetails = new QuestDetails(questDetailsScroller, SWT.NONE);
		questDetailsScroller.setContent(questDetails);
		questDetailsScroller.setExpandHorizontal(true);
		questDetailsScroller.setExpandVertical(true);
		questDetailsScroller.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle r = questDetailsScroller.getClientArea();
				questDetailsScroller.setMinSize(questDetails.computeSize(
						r.width, SWT.DEFAULT));
			}
		});

		questsViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						if (event.getSelection() instanceof IStructuredSelection) {
							IStructuredSelection selection = (IStructuredSelection) event
									.getSelection();
							QuestWrapper qw = null;
							if ((selection.getFirstElement()) instanceof Quest) {
								qw = quests.get(((Quest) selection.getFirstElement()));
							}
							else if((selection.getFirstElement()) instanceof Task) {
								qw = quests.get(((Quest) ((Task) selection.getFirstElement()).getQuest().getRef()));
							}
							else if((selection.getFirstElement()) instanceof Assignment) {

								Assignment a = ((Assignment) selection.getFirstElement());
								Activator.logBackground("QuestHUDAssignmentSelected", "" + a.getId());
								detailsLayout.topControl = questDetailsScroller;
								questDetails.setAssignment(a);
								detailsArea.layout(true);
								parent.notifyListeners(SWT.Resize, new Event());
								parent.layout(true);
								detailsArea.layout(true);
								questDetailsScroller.layout(true);
							}
							if(qw != null){
								Activator.logBackground("QuestHUDQuestOrTaskSelected", "" + qw.getQuest().getId());
								detailsLayout.topControl = questDetailsScroller;
								questDetails.setQuest(qw);
								detailsArea.layout(true);
								parent.notifyListeners(SWT.Resize, new Event());
								parent.layout(true);
								detailsArea.layout(true);
								questDetailsScroller.layout(true);
							}
						}
					}
				});

		updateQuests();
	}

	void loggedIn() {
		this.layout.topControl = loggedInPanel;
		parent.layout(true);

		updateQuests();
		parent.layout(true);
		isShowingLoggedIn=true;
	}

	void loggedOut() {
		this.layout.topControl = needToLogin;
		parent.layout(true);
		isShowingLoggedIn=false;
	}
	private boolean isShowingLoggedIn = false;
	@Override
	public void setFocus() {
		Activator.logBackground("QuestHUDSetFocus", null);
		if (HALOServiceFactory.getInstance().isLoggedIn() && !isShowingLoggedIn)
			loggedIn();
		else if(!HALOServiceFactory.getInstance().isLoggedIn() && isShowingLoggedIn)
			loggedOut();
		updateQuests();
	}

	private void updateQuests() {
		synchronized (updatingFlag) {
			if (updatingFlag)
				return;
			updatingFlag = true;

			if (HALOServiceFactory.getInstance().isLoggedIn()
					&& questsViewer != null) {
				Job j = new Job("Updating quests list") {

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						quests = new HashMap<Quest,QuestWrapper>();
						HashMap<Quest, QuestProgress> progress = new HashMap<Quest, QuestProgress>();
						if (HALOServiceFactory.getInstance().getUserSvc()
								.getMyProgress() != null)
							for (QuestProgress p : HALOServiceFactory
									.getInstance().getUserSvc().getMyProgress()) {
								progress.put(p.getQuest(), p);
								cachedProgress.put(p.getTask(), p);
							}
						for (Enrollment e : HALOServiceFactory.getInstance()
								.getUserSvc().getEnrollments()) {
							if (e.getType().equals(EnrollmentType.STUDENT)) {
								for (Assignment a : HALOServiceFactory
										.getInstance().getUserSvc()
										.getAssignmentsFor(e.getCourse())) {
									for (Quest q : HALOServiceFactory
											.getInstance().getUserSvc()
											.getQuestsFor(a)) {
										QuestWrapper qw = new QuestWrapper(q,
												a, e.getCourse(),
												progress.get(q));
										quests.put(q,qw);
									}
								}
							}
						}
						Display.getDefault().syncExec(new Runnable() {

							@Override
							public void run() {
								questsViewer.refresh();
								questsViewer.expandAll();
								updatingFlag = false;
							}
						});
						return Status.OK_STATUS;
					}
				};
				j.schedule();
			} else
				updatingFlag = false;
		}
	}
}
