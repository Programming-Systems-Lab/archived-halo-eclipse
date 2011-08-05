package edu.columbia.cs.psl.halo.admin.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import edu.columbia.cs.psl.halo.server.stubs.Course;

public class CourseEditorInput implements IEditorInput{
    private final int id;
    private Course course;
    
    public CourseEditorInput(int id, Course course)
    {
    	this.id=id;
    	this.course = course;
    }
    public Course getCourse() {
		return course;
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
		return course.getName();
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
        CourseEditorInput other = (CourseEditorInput) obj;
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
