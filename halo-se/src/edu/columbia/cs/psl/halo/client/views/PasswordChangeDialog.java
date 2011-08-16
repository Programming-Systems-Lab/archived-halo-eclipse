package edu.columbia.cs.psl.halo.client.views;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PasswordChangeDialog extends Dialog{
	private Text passwordField1;
	private Text passwordField2;
	private String password;
	public PasswordChangeDialog(Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(true);

	}
	public String getPassword()
	{
		return password;
	}
	@Override
	protected boolean isResizable() {
		return true;
	}
	@Override
	protected Point getInitialSize() {
		return new Point(400, 150);
	}
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite comp = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) comp.getLayout();
		layout.numColumns=2;
		
		Label pass1l = new Label(comp,SWT.RIGHT);
		pass1l.setText("New Password: ");
		passwordField1 = new Text(comp, SWT.SINGLE | SWT.PASSWORD);
		passwordField1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label pass2l = new Label(comp,SWT.RIGHT);
		pass2l.setText("Confirm New Password: ");
		passwordField2 = new Text(comp, SWT.SINGLE | SWT.PASSWORD);
		passwordField2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		errorField = new Label(comp,SWT.CENTER);
		errorField.setText("");
		GridData data = new GridData();
		data.horizontalSpan = 2;
		errorField.setLayoutData(data);
		getShell().setText("Change password");
		return comp;
	}
	Label errorField;
	@Override
	protected Control createButtonBar(Composite parent) {
		Control ret = super.createButtonBar(parent);
		
		getButton(OK).removeListener(SWT.Selection, getButton(OK).getListeners(SWT.Selection)[0]);
		getButton(OK).addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(passwordField1.getText().equals(passwordField2.getText()) && passwordField1.getText().length() > 1)
				{
					password = passwordField1.getText();
					okPressed();
				}
				else
				{
					errorField.setText("Error: Passwords do not match");
					errorField.getParent().layout(true);
				}
			}
		});
		return ret;
	}
	
}
