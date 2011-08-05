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
import edu.columbia.cs.psl.halo.admin.editor.QuestEditor;
import edu.columbia.cs.psl.halo.admin.editor.QuestEditorInput;
import edu.columbia.cs.psl.halo.admin.view.ClassesUsers;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Quest;

public class NewQuest extends AbstractHandler {


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get the view
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		Quest q = new Quest();
		q.setDescription("");
		q.setName("New Quest");
		q.setExperiencePoints(0);

		try {
			QuestEditorInput input = new QuestEditorInput(
				(new Random()).nextInt() + 1000,q);
			page.openEditor(input, QuestEditor.ID);
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
		return null;
	}


}
