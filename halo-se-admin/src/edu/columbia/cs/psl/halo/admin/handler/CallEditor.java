package edu.columbia.cs.psl.halo.admin.handler;

import java.util.Random;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import edu.columbia.cs.psl.halo.admin.editor.AssignmentEditor;
import edu.columbia.cs.psl.halo.admin.editor.AssignmentEditorInput;
import edu.columbia.cs.psl.halo.admin.editor.CourseEditor;
import edu.columbia.cs.psl.halo.admin.editor.CourseEditorInput;
import edu.columbia.cs.psl.halo.admin.editor.QuestEditor;
import edu.columbia.cs.psl.halo.admin.editor.QuestEditorInput;
import edu.columbia.cs.psl.halo.admin.editor.TaskEditor;
import edu.columbia.cs.psl.halo.admin.editor.TaskEditorInput;
import edu.columbia.cs.psl.halo.admin.editor.UserEditor;
import edu.columbia.cs.psl.halo.admin.editor.UserEditorInput;
import edu.columbia.cs.psl.halo.admin.view.ClassUserDetails;
import edu.columbia.cs.psl.halo.admin.view.ClassesUsers;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Course;
import edu.columbia.cs.psl.halo.server.stubs.Quest;
import edu.columbia.cs.psl.halo.server.stubs.Task;
import edu.columbia.cs.psl.halo.server.stubs.User;

public class CallEditor extends AbstractHandler {


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get the view
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		ClassesUsers view = (ClassesUsers) page.findView(ClassesUsers.ID);
		// Get the selection
		if(view.getSite().getSelectionProvider().getSelection() instanceof IStructuredSelection)
		{
			Object iSelectn = ((IStructuredSelection) view.getSite().getSelectionProvider().getSelection()).getFirstElement();
			
			if(iSelectn instanceof Course)
			{
				Course selected = (Course) iSelectn;
				try {
					CourseEditorInput input = new CourseEditorInput(
						selected.getId(),selected);
					page.openEditor(input, CourseEditor.ID);
					return null;
				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
			}
			else if(iSelectn instanceof Assignment)
			{
				Assignment selected = (Assignment) iSelectn;
				try {
					AssignmentEditorInput input = new AssignmentEditorInput(
						selected.getId(),selected);
					page.openEditor(input, AssignmentEditor.ID);
					return null;
				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
			}
			else if(iSelectn instanceof Quest)
			{
				Quest selected = (Quest) iSelectn;
				try {
					QuestEditorInput input = new QuestEditorInput(
						selected.getId(),selected);
					page.openEditor(input, QuestEditor.ID);
					return null;
				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
			}
			else if(iSelectn instanceof Task)
			{
				Task selected = (Task) iSelectn;
				try {
					TaskEditorInput input = new TaskEditorInput(
						selected.getId(),selected);
					page.openEditor(input, TaskEditor.ID);
					return null;
				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
			}
		}
//				try {
//						UserEditorInput input = new UserEditorInput(
//							selected.getId());
//						page.openEditor(input, UserEditor.ID);
//				} catch (PartInitException e) {
//					throw new RuntimeException(e);
//				}
		
		return null;
	}


}
