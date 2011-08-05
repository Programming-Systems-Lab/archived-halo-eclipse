package edu.columbia.cs.psl.halo.admin.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.admin.view.ClassesUsers;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Course;
import edu.columbia.cs.psl.halo.server.stubs.User;

public class AssignmentEditor extends EditorPart {
	public static final String ID = "edu.columbia.cs.psl.halo.admin.editor.AssignmentEditor";
	private Assignment assignment;

    protected boolean dirty = false;
    
	public boolean isDirty() {
        return dirty;
     }
     protected void setDirty(boolean value) {
        dirty = value;
        firePropertyChange(PROP_DIRTY);
     }
     
    private void checkDirty()
    {
    	boolean result = ! (assignment.getTitle().equals(assignmentTitle.getText()) && assignment.getDescription().equals(assignmentDesc.getText()));
    	if(result != dirty)
    	{
    		dirty = result;
    		firePropertyChange(PROP_DIRTY);
    	}
    }
	public AssignmentEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if(isDirty())
		{
			assignment.setTitle(assignmentTitle.getText());
			assignment.setDescription(assignmentDesc.getText());
			int oldId = assignment.getId();
			assignment = HALOServiceFactory.getInstance().getAdminSvc().updateAssignment(assignment);
			System.out.println("New id is " + assignment.getId());
			if(assignment.getId() != oldId)
			{
				//Had a new page, new id, editor input is FUBAR'ed
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(this, false);
				AssignmentEditorInput input2 = new AssignmentEditorInput(assignment.getId(), assignment);
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(input2, ID);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ClassesUsers.getInstance().refresh();
		}
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}
	AssignmentEditorInput input;
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {

		setSite(site);
		setInput(input);
		this.assignment = ((AssignmentEditorInput) input).getAssignment();
		this.input = ((AssignmentEditorInput) input);
		setPartName("Assignment: " + assignment.getTitle());

	}


	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
	Text assignmentTitle;
	Text assignmentDesc;
	
	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		new Label(parent, SWT.NONE).setText("Assignment Name");
		assignmentTitle = new Text(parent, SWT.BORDER);
		assignmentTitle.setText(assignment.getTitle());
		assignmentTitle.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		assignmentTitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkDirty();
			}
		});
		
		new Label(parent, SWT.NONE).setText("Assignment Release Date");
		DateTime releaseDate = new DateTime (parent, SWT.DATE | SWT.LONG);
		new Label(parent, SWT.NONE).setText("Assignment Due Date");
		DateTime dueDate = new DateTime (parent, SWT.TIME);
		
		new Label(parent, SWT.NONE).setText("Assignment Description");
		
		assignmentDesc = new Text(parent, SWT.BORDER|SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		assignmentDesc.setText(assignment.getDescription());
		GridData data = new GridData(SWT.FILL,SWT.BEGINNING,true,false);
		data.heightHint = 200;
		assignmentDesc.setLayoutData(data);
	
		assignmentDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkDirty();
			}
		});
		
//		lastName.setText(person.getLastName());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
