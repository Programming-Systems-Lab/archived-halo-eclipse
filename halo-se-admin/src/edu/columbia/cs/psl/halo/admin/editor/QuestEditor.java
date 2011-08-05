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
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.EditorPart;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.admin.view.ClassesUsers;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Course;
import edu.columbia.cs.psl.halo.server.stubs.Quest;
import edu.columbia.cs.psl.halo.server.stubs.User;

public class QuestEditor extends EditorPart {
	public static final String ID = "edu.columbia.cs.psl.halo.admin.editor.QuestEditor";
	private Quest quest;
	
	
	public QuestEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if(isDirty())
		{
			quest.setName(questTitle.getText());
			quest.setDescription(questDesc.getText());
			if(quest.getAssignment() == null)
				quest.setAssignment(new Assignment());
			quest.getAssignment().setId(assignments.get(assignment.getText()).getId());
			quest.setExperiencePoints(Integer.parseInt(questXP.getText()));
			
			int oldId = quest.getId();
			quest = HALOServiceFactory.getInstance().getAdminSvc().updateQuest(quest);
			quest.getAssignment().setRef(quest.getAssignment());
			if(oldId != quest.getId())
			{
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(this, false);
				QuestEditorInput input2 = new QuestEditorInput(quest.getId(), quest);
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

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {

		setSite(site);
		setInput(input);
		this.quest = ((QuestEditorInput) input).getQuest();
		setPartName("Quest: " + quest.getName());

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
    	boolean result = ! (quest.getName().equals(questTitle.getText()) && quest.getDescription().equals(questDesc.getText()) && 
    			Integer.parseInt(questXP.getText()) == quest.getExperiencePoints() &&
    			(quest.getAssignment().getId() == assignments.get(assignment.getText()).getId() || (quest.getAssignment().getId() == 0 && ((Assignment) quest.getAssignment().getRef()).getId() == assignments.get(assignment.getText()).getId() ))
    			
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

	Text questTitle;
	Text questDesc;
	Combo assignment;
	Text questXP;
	
	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		new Label(parent, SWT.NONE).setText("Quest Name");
		questTitle = new Text(parent, SWT.BORDER);
		questTitle.setText(quest.getName());
		questTitle.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		questTitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkDirty();
			}
		});
		
		new Label(parent, SWT.NONE).setText("Quest Description");
		
		questDesc = new Text(parent, SWT.BORDER|SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		questDesc.setText(quest.getDescription());
		GridData data = new GridData(SWT.FILL,SWT.BEGINNING,true,false);
		data.heightHint = 200;
		questDesc.setLayoutData(data);
	
		questDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkDirty();
			}
		});
		
		new Label(parent,SWT.None).setText("Experience points awarded");
		questXP = new Text(parent, SWT.BORDER);
		questXP.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		questXP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkDirty();
			}
		});
		
		questXP.addListener(SWT.Verify, new Listener() {
		      public void handleEvent(Event e) {
		          String string = e.text;
		          char[] chars = new char[string.length()];
		          string.getChars(0, chars.length, chars, 0);
		          for (int i = 0; i < chars.length; i++) {
		            if (!('0' <= chars[i] && chars[i] <= '9')) {
		              e.doit = false;
		              return;
		            }
		          }
		        }
		      });
		questXP.setText(""+quest.getExperiencePoints());
		new Label(parent,SWT.None).setText("Assignment");
		assignment = new Combo(parent, SWT.READ_ONLY);
		
		assignment.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkDirty();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		assignments = new HashMap<String, Assignment>();
		int i = 0;
		int s = 0;
		List<Assignment> allAsignments;
		
		if(quest.getAssignment() != null && quest.getAssignment().getRef() != null)
			allAsignments = ClassesUsers.assignmentsByCourse.get(((Assignment) quest.getAssignment().getRef()).getCourse());
		else
			allAsignments = ClassesUsers.assignmentsByCourse.get(ClassesUsers.defaultCourse);
		String[] ar = new String[allAsignments.size()];
		for(Assignment a : allAsignments)
		{
			ar[i] = a.getTitle() + " (ID"+a.getId()+")";
			assignments.put(a.getTitle() + " (ID" +a.getId() + ")", a);
			if(quest.getAssignment() != null && a.equals(quest.getAssignment().getRef()))
				s = i;
			i++;
		}

		assignment.setItems(ar);
		assignment.select(s);
		
//		lastName.setText(person.getLastName());
	}
	private HashMap<String, Assignment> assignments;
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
