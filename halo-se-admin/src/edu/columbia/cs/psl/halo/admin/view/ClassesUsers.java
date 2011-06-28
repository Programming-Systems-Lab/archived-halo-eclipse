package edu.columbia.cs.psl.halo.admin.view;
import java.util.List;

import com.cloudgarden.resource.SWTResourceManager;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.server.stubs.Course;
import edu.columbia.cs.psl.halo.server.stubs.User;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ClassesUsers extends ViewPart {

	
	public static String ID= "edu.columbia.cs.psl.halo.admin.view.ClassesUsers";
	private ToolItem newStudentButton;
	private ToolBar toolBar;
	private Tree tree;

	private ToolItem newClassButton;
	public ClassesUsers() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		{
			GridLayout parentLayout = new GridLayout(1,false);

			parent.setLayout(parentLayout);
			{
				GridData gridData = new GridData();
				gridData.grabExcessVerticalSpace = true;
				gridData.grabExcessHorizontalSpace = true;
				gridData.verticalAlignment=SWT.FILL;
				gridData.horizontalAlignment=SWT.FILL;
				tree = new Tree(parent, SWT.NONE);
				tree.setLayoutData(gridData);

				tree.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						
						Object s = tree.getSelection()[0].getData();
						if(s instanceof User)
						{
							User u = (User) s;
							System.out.println("User " + u.getEmail());
						}
						else if(s instanceof Course)
						{
							Course c = (Course) s;
							System.out.println("Course " + c.getName());
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						
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

	@Override
	public void setFocus() {
		List<User> users = HALOServiceFactory.getInstance().getAdminSvc().getUsers();
		
		List<Course> courses = HALOServiceFactory.getInstance().getAdminSvc().getCourses();

		tree.removeAll();
		TreeItem userRoot = new TreeItem(tree, 0);
		userRoot.setText("Users");
		for(User u : users)
		{
			TreeItem ui = new TreeItem(userRoot, 0);
			ui.setText(u.getFirstName() + u.getLastName());
			ui.setData(u);
		}
		userRoot.setExpanded(true);
		
		TreeItem courseRoot = new TreeItem(tree, 0);
		courseRoot.setText("Courses");
		for(Course c  : courses)
		{
			TreeItem ui = new TreeItem(courseRoot, 0);
			ui.setData(c);
			ui.setText(c.getName());
		}
		courseRoot.setExpanded(true);
	}

}
