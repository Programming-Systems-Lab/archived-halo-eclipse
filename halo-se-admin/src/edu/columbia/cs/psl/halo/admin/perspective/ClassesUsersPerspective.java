package edu.columbia.cs.psl.halo.admin.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.PerspectiveAdapter;

import edu.columbia.cs.psl.halo.admin.view.ClassesUsers;

public class ClassesUsersPerspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
//		layout.addStandaloneView(ClassesUsers.ID,  true,
//	            IPageLayout.LEFT, .05f, layout.getEditorArea());
	}

}
