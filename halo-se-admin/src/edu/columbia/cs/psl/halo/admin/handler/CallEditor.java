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

import edu.columbia.cs.psl.halo.admin.editor.UserEditor;
import edu.columbia.cs.psl.halo.admin.editor.UserEditorInput;
import edu.columbia.cs.psl.halo.admin.view.ClassUserDetails;
import edu.columbia.cs.psl.halo.admin.view.ClassesUsers;
import edu.columbia.cs.psl.halo.server.stubs.User;

public class CallEditor extends AbstractHandler {


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("called");
		// Get the view
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		ClassUserDetails view = (ClassUserDetails) page.findView(ClassUserDetails.ID);
		// Get the selection

				try {
					UserEditorInput input = new UserEditorInput(
							(new Random()).nextInt(10));
						page.openEditor(input, UserEditor.ID);
				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
		
		return null;
	}


}
