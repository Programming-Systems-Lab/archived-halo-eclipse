package edu.columbia.cs.psl.halo.client.editor;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Quest;


public class AssignmentViewerInput implements IEditorInput {
	private final Assignment assignment;
	private final List<Quest> quests;
	
	public Assignment getAssignment() {
		return assignment;
	}
	public List<Quest> getQuests() {
		return quests;
	}
	public AssignmentViewerInput(Assignment a, List<Quest> quests)
	{
		this.assignment = a;
		this.quests = quests;
	}
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return assignment.getTitle();
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		return "Displays an assignment";
	}
	@Override
	public int hashCode() {
		return assignment.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		if(assignment.getId() != ((AssignmentViewerInput) obj).assignment.getId())
			return false;
		return true;
	}
}
