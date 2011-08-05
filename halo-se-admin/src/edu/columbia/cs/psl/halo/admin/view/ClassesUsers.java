package edu.columbia.cs.psl.halo.admin.view;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cloudgarden.resource.SWTResourceManager;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Course;
import edu.columbia.cs.psl.halo.server.stubs.Quest;
import edu.columbia.cs.psl.halo.server.stubs.Task;
import edu.columbia.cs.psl.halo.server.stubs.User;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
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
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;


public class ClassesUsers extends ViewPart {
	private static ClassesUsers instance;
	public static Course defaultCourse;
	class NameSorter extends ViewerSorter {
	}

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {
		private Set<Course> invisibleRoot;

		public void dispose() {
		}

		public Object[] getChildren(Object parent) {
			if (parent instanceof Quest)
				return ((Quest) parent).getTasks().toArray();
			else if (parent instanceof Assignment)
				return ((Assignment) parent).getQuests().toArray();
			else if(parent instanceof Course)
				return assignmentsByCourse.get(((Course) parent)).toArray();
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
			if (child instanceof Quest)
				return ((Quest) child).getAssignment();
			else if(child instanceof Assignment)
				return ((Assignment) child).getCourse();
			else if(child instanceof Course)
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
			else if (parent instanceof Course)
				return assignmentsByCourse.get(((Course) parent)).size() > 0;
			return false;
		}

		private void initialize() {
			invisibleRoot = new HashSet<Course>();
			if (courses != null)
				for (Course c : courses) {
					invisibleRoot.add(c);
					System.out.println(c);
					
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
			// ImageDescriptor desc =
			// Activator.getImageDescriptor("icons/checkedBox.png");
			File f = new File("icons/checkedBox.png");
			ImageDescriptor desc;
			try {
				desc = ImageDescriptor.createFromURL(f.toURL());
				checkedImage = desc.createImage();
				f = new File("icons/uncheckedBox.png");
				desc = ImageDescriptor.createFromURL(f.toURL());
				unCheckedImage = desc.createImage();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void dispose() {
			super.dispose();
			checkedImage.dispose();
		}

		public Image getImage(Object obj) {

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
				return a.getTitle();
			}
			else if (obj instanceof Task) {
				Task t = (Task) obj;
				return t.getName();
			}
			else if(obj instanceof Course)
			{
				return ((Course) obj).getName();
			}
			return obj.toString();
		}

		@Override
		public Color getForeground(Object element) {
			
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
	
	public static String ID= "edu.columbia.cs.psl.halo.admin.view.ClassesUsers";
	private ToolItem newStudentButton;
	private ToolBar toolBar;
	private TreeViewer tree;

	private ToolItem newClassButton;
	public ClassesUsers() {
		instance = this;
		// TODO Auto-generated constructor stub
	}

	public static ClassesUsers getInstance() {
		return instance;
	}
	private Composite parent;
	@Override
	public void createPartControl(Composite parent) {
		{
			this.parent = parent;
			GridLayout parentLayout = new GridLayout(1,false);

			parent.setLayout(parentLayout);
			{
				GridData gridData = new GridData();
				gridData.grabExcessVerticalSpace = true;
				gridData.grabExcessHorizontalSpace = true;
				gridData.verticalAlignment=SWT.FILL;
				gridData.horizontalAlignment=SWT.FILL;
				tree = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
				tree.setContentProvider(new ViewContentProvider());
				tree.setLabelProvider(new ViewLabelProvider());
				tree.setInput("root");
				tree.getTree().setLayoutData(gridData);
				getSite().setSelectionProvider(tree);
				
				tree.addDoubleClickListener(new IDoubleClickListener() {
					
					@Override
					public void doubleClick(DoubleClickEvent event) {
						if(tree.getSelection() instanceof IStructuredSelection)
						{
							Object obj = ((IStructuredSelection) tree.getSelection()).getFirstElement();
							if(obj instanceof Course || obj instanceof Task || obj instanceof Assignment || obj instanceof Quest ||  obj instanceof Task)
							{
								IHandlerService handlerService = (IHandlerService) getSite()
										.getService(IHandlerService.class);
								try {
									handlerService.executeCommand(
											"edu.columbia.cs.psl.halo.admin.openEditor", null);
								} catch (Exception ex) {
									ex.printStackTrace();
//									throw new RuntimeException(
//											"edu.columbia.cs.psl.halo.admin.openEditor not found");
								}
							}
							else if(obj instanceof Assignment)
							{
								
							}
							else if(obj instanceof Task)
							{
								
							}
						}
					}
				});
				
			}
			
			{
				toolBar = new ToolBar(parent, SWT.NONE);
				{
					newStudentButton = new ToolItem(toolBar, SWT.NONE);
					newStudentButton.setImage(SWTResourceManager.getImage("icons/new-student.png"));
					newStudentButton.setText("New Student");
					newStudentButton.addSelectionListener(new SelectionListener() {
						
						@Override
						public void widgetSelected(SelectionEvent e) {
							IHandlerService handlerService = (IHandlerService) getSite()
									.getService(IHandlerService.class);
							try {
								handlerService.executeCommand(
										"edu.columbia.cs.psl.halo.admin.openEditor", null);
							} catch (Exception ex) {
								ex.printStackTrace();
								throw new RuntimeException(
										"edu.columbia.cs.psl.halo.admin.openEditor not found");
							}
						}
						
						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							// TODO Auto-generated method stub
							
						}
					});					}
				{
					newClassButton = new ToolItem(toolBar, SWT.NONE);
					newClassButton.setText("New Class");
					newClassButton.setImage(SWTResourceManager.getImage("icons/new-class.png"));
				}
			}			
		}
	}
	private List<Course> courses = null;
	public static HashMap<Course, List<Assignment>> assignmentsByCourse = null;
	@Override
	public void setFocus() {
//		List<User> users = HALOServiceFactory.getInstance().getAdminSvc().getUsers();
		
	refresh();
//		tree.expandAll();
//		parent.pack();
//		parent.layout(true);
	}
	
	public void refresh()
	{
		courses = HALOServiceFactory.getInstance().getAdminSvc().getCourses();
		assignmentsByCourse = new HashMap<Course, List<Assignment>>();
		for(Course c : courses)
		{
			defaultCourse = c;
			assignmentsByCourse.put(c, HALOServiceFactory.getInstance().getUserSvc().getAssignmentsFor(c));
		}
		tree.refresh();
		tree.expandToLevel(3);
	}
	public static void main(String[] args) {
		HALOServiceFactory.getInstance().login("jon", "test123");

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setBounds(200, 200, 300, 600);
		ClassesUsers s = new ClassesUsers();
		s.createPartControl(shell);
		s.setFocus();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}
}
