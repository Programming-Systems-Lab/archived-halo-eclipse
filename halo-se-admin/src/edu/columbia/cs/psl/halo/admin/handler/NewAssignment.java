package edu.columbia.cs.psl.halo.admin.handler;

import java.util.Random;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import edu.columbia.cs.psl.halo.admin.editor.AssignmentEditor;
import edu.columbia.cs.psl.halo.admin.editor.AssignmentEditorInput;
import edu.columbia.cs.psl.halo.admin.view.ClassesUsers;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;

public class NewAssignment extends AbstractHandler {


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get the view
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		Assignment a = new Assignment();
		a.setDescription("");
		a.setTitle("New Assignment");
		a.setCourse(ClassesUsers.defaultCourse);

		try {
			AssignmentEditorInput input = new AssignmentEditorInput(
				(new Random()).nextInt() + 1000,a);
			page.openEditor(input, AssignmentEditor.ID);
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
		return null;
	}


}
