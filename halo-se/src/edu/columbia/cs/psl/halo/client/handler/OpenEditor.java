package edu.columbia.cs.psl.halo.client.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import edu.columbia.cs.psl.halo.client.editor.AssignmentViewerEditor;
import edu.columbia.cs.psl.halo.client.editor.AssignmentViewerInput;
import edu.columbia.cs.psl.halo.client.views.DashboardView;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;

public class OpenEditor extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get the view
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		DashboardView view = (DashboardView) page.findView(DashboardView.ID);
		// Get the selection
		Assignment selection = view.getDashboardComposite().getSelectedAssignment();
		if (selection != null) {
				AssignmentViewerInput input = new AssignmentViewerInput(selection, null);
				try {
					page.openEditor(input, AssignmentViewerEditor.ID);

				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
			}
		
		return null;
	}

}
