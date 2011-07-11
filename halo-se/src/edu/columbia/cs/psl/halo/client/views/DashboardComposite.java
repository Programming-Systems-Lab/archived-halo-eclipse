package edu.columbia.cs.psl.halo.client.views;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.handlers.IHandlerService;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Course;
import edu.columbia.cs.psl.halo.server.stubs.Enrollment;
import edu.columbia.cs.psl.halo.server.stubs.EnrollmentType;
import edu.columbia.cs.psl.halo.server.stubs.Quest;

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
public class DashboardComposite extends Composite {

	private DashboardView dashboard;
	private Label lblLoggedInAs;
	private Tree assignmentsTree;
	private Button btnLogOut;
	private Assignment selectedAssignment;
	
	public Assignment getSelectedAssignment() {
		return selectedAssignment;
	}
	public DashboardComposite(Composite parent, int style, DashboardView dashboard) {
		super(parent, style);
		this.dashboard = dashboard;
		initGUI();
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				updateWindow();
			}
		});
		updateWindow();
	}
	private void updateWindow() {
		lblLoggedInAs.setText("Logged in as: " + HALOServiceFactory.getInstance().getMe().getEmail());
		for(TreeItem i : assignmentsTree.getItems())
			i.dispose();
		for(Enrollment c : HALOServiceFactory.getInstance().getUserSvc().getEnrollments())
		{
			if(c.getType().equals(EnrollmentType.STUDENT))
			{
				TreeItem courseRoot = new TreeItem(assignmentsTree, SWT.NONE);
				courseRoot.setText(c.getCourse().getName());
				courseRoot.setExpanded(true);
				for(Assignment a : HALOServiceFactory.getInstance().getUserSvc().getAssignmentsFor(c.getCourse()))
				{
					TreeItem assignmentRoot = new TreeItem(courseRoot, SWT.NONE);
					assignmentRoot.setText(a.getTitle());
					assignmentRoot.setExpanded(true);
					assignmentRoot.setData(a);
					System.out.println(assignmentRoot);
					for(Quest t : HALOServiceFactory.getInstance().getUserSvc().getQuestsFor(a))
					{
						TreeItem questRoot = new TreeItem(assignmentRoot, SWT.NONE);
						questRoot.setText(t.getName());
					}
				}
			}
		}
		
	}
	private void initGUI() {
		try {
			
				this.setSize(281, 325);
				{
					lblLoggedInAs = new Label(this, SWT.NONE);
					lblLoggedInAs.setText("Logged in as Foo");
					lblLoggedInAs.setBounds(12, 12, 205, 16);
				}
				{
					btnLogOut = new Button(this, SWT.PUSH | SWT.CENTER);
					btnLogOut.setText("Log Out");
					btnLogOut.setBounds(193, -2, 81, 30);
					btnLogOut.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							dashboard.loggedOut();
						}
					});
				}
				{
					assignmentsTree = new Tree(this, SWT.NONE);
//					assignmentsTree.setHeaderVisible(true);
//				    TreeColumn column1 = new TreeColumn(assignmentsTree, SWT.LEFT);
//				    column1.setText("Column 1");
//				    column1.setWidth(200);
//				    TreeColumn column2 = new TreeColumn(assignmentsTree, SWT.CENTER);
//				    column2.setText("Column 2");
//				    column2.setWidth(200);
//				    TreeColumn column3 = new TreeColumn(assignmentsTree, SWT.RIGHT);
//				    column3.setText("Column 3");
//				    column3.setWidth(200);
//				    
					assignmentsTree.setBounds(12, 34, 262, 279);
				}
			hookDoubleClickCommand();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void hookDoubleClickCommand() {
		assignmentsTree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				handleDoubleClick();
			}

			private void handleDoubleClick() {
				
				if(assignmentsTree.getSelection()[0].getData() != null && assignmentsTree.getSelection()[0].getData() instanceof Assignment)
				{
				IHandlerService handlerService = (IHandlerService) dashboard.getSite().getService(IHandlerService.class);
				selectedAssignment = (Assignment) assignmentsTree.getSelection()[0].getData();
				
				try {
					handlerService.executeCommand(
							"edu.columbia.cs.psl.halo.client.commands.openEditor", null);
				} catch (Exception ex) {
					ex.printStackTrace();
//					throw new RuntimeException(
//							"edu.columbia.cs.psl.halo.client.commands.openEditor not found");
				}
			
				}
				}
		});
	}
}
