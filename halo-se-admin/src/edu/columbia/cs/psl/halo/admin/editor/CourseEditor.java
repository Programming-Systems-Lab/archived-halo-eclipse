package edu.columbia.cs.psl.halo.admin.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import edu.columbia.cs.psl.halo.server.stubs.Course;
import edu.columbia.cs.psl.halo.server.stubs.User;

public class CourseEditor extends EditorPart {
	public static final String ID = "edu.columbia.cs.psl.halo.admin.editor.CourseEditor";
	private Course course;
	
	
	public CourseEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

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
		this.course = ((CourseEditorInput) input).getCourse();
		setPartName("Course: " + course.getName());

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		Label label1 = new Label(parent, SWT.NONE);
		label1.setText("Course Name");
		Text text = new Text(parent, SWT.BORDER);
		text.setText(course.getName());
		text.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		new Label(parent, SWT.NONE).setText("Last Name");
		Text lastName = new Text(parent, SWT.BORDER);
		lastName.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
//		lastName.setText(person.getLastName());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
