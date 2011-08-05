package edu.columbia.cs.psl.halo.admin.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Course;

public class AssignmentEditorInput implements IEditorInput{
    private final int id;
    private Assignment assignment;
    
    public AssignmentEditorInput(int id, Assignment assignment)
    {
    	this.id=id;
    	this.assignment = assignment;
    }
    public Assignment getAssignment() {
		return assignment;
	}
    public int getId() {
		return id;
	}
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return assignment.getTitle();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "Displays a Course";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AssignmentEditorInput other = (AssignmentEditorInput) obj;
        if (id != other.id)
            return false;
        return true;
	}
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + id;
	        return result;
	    }
}
