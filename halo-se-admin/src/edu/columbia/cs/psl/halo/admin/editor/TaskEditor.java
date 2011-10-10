package edu.columbia.cs.psl.halo.admin.editor;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.admin.view.ClassesUsers;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Course;
import edu.columbia.cs.psl.halo.server.stubs.Quest;
import edu.columbia.cs.psl.halo.server.stubs.Task;
import edu.columbia.cs.psl.halo.server.stubs.User;

public class TaskEditor extends EditorPart {
	public static final String ID = "edu.columbia.cs.psl.halo.admin.editor.TaskEditor";
	private Task task;
	
	
	public TaskEditor() {
		// TODO Auto-generated constructor stub
	}

	public void doSave(IProgressMonitor monitor) {
		if(isDirty())
		{
			task.setName(taskTitle.getText());
			task.setDescription(taskDesc.getText());
			task.getQuest().setId(questMap.get(assignedQuest.getText()).getId());
			task = HALOServiceFactory.getInstance().getAdminSvc().updateTask(task);
			ClassesUsers.getInstance().refresh();

		}
	}
	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {

		setSite(site);
		setInput(input);
		this.task = ((TaskEditorInput) input).getTask();
		setPartName("Task: " + task.getName());

	}

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
    	boolean result = ! (task.getName().equals(taskTitle.getText()) && task.getDescription().equals(taskDesc.getText()) && 
    			(task.getQuest().getId() == questMap.get(assignedQuest.getText()).getId() || (task.getQuest().getId() == 0 && ((Quest) task.getQuest().getRef()).getId() == questMap.get(assignedQuest.getText()).getId() ))
    			
    			);
    	if(result != dirty)
    	{
    		dirty = result;
    		firePropertyChange(PROP_DIRTY);
    	}
    }

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	Text taskTitle;
	Text taskDesc;
	Combo assignedQuest;
	
	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		new Label(parent, SWT.NONE).setText("Task Name");
		taskTitle = new Text(parent, SWT.BORDER);
		taskTitle.setText(task.getName());
		taskTitle.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		taskTitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkDirty();
			}
		});
		
		new Label(parent, SWT.NONE).setText("Task Description");
		
		taskDesc = new Text(parent, SWT.BORDER|SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		taskDesc.setText(task.getDescription());
		GridData data = new GridData(SWT.FILL,SWT.BEGINNING,true,false);
		data.heightHint = 200;
		taskDesc.setLayoutData(data);
	
		taskDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkDirty();
			}
		});

		new Label(parent,SWT.None).setText("Quest");
		assignedQuest = new Combo(parent, SWT.READ_ONLY);
		
		assignedQuest.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkDirty();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		questMap = new HashMap<String, Quest>();
		int i = 0;
		int s = 0;
		try{
		List<Quest> allquests =HALOServiceFactory.getInstance().getUserSvc().getAllQuests();
		String[] ar = new String[allquests.size()];
		for(Quest a : allquests)
		{
			ar[i] = a.getName() + " (ID"+a.getId()+")";
			questMap.put(a.getName() + " (ID" +a.getId() + ")", a);
			if(a.equals(task.getQuest().getRef()))
				s = i;
			i++;
		}
		assignedQuest.setItems(ar);
		assignedQuest.select(s);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		
//		lastName.setText(person.getLastName());
	}
	private HashMap<String, Quest> questMap;

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
