package edu.columbia.cs.psl.halo.admin.views;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.ViewSite;
import org.eclipse.ui.part.ViewPart;

import edu.columbia.cs.psl.halo.HALOServiceFactory;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class LoginView extends ViewPart {
	private Label lblUsername;
	private Text txtUsername;
	private Button btnLogin;
	private Text txtPassword;
	private Label lblPassword;

	public LoginView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		{
			FormLayout parentLayout = new FormLayout();
			parent.setLayout(parentLayout);
			parent.setSize(448, 218);
			{
				{
					txtUsername = new Text(parent, SWT.NONE);
					FormData txtUsernameLData = new FormData();
					txtUsernameLData.left =  new FormAttachment(0, 1000, 76);
					txtUsernameLData.top =  new FormAttachment(0, 1000, 12);
					txtUsernameLData.width = 127;
					txtUsernameLData.height = 14;
					txtUsername.setLayoutData(txtUsernameLData);
				}
				{
					lblUsername = new Label(parent, SWT.NONE);
					FormData lblUsernameLData = new FormData();
					lblUsernameLData.left =  new FormAttachment(0, 1000, 12);
					lblUsernameLData.top =  new FormAttachment(0, 1000, 12);
					lblUsernameLData.width = 58;
					lblUsernameLData.height = 14;
					lblUsername.setLayoutData(lblUsernameLData);
					lblUsername.setText("Username");
				}
				
			{
				FormData txtPasswordLData = new FormData();
				txtPasswordLData.left =  new FormAttachment(0, 1000, 76);
				txtPasswordLData.top =  new FormAttachment(0, 1000, 32);
				txtPasswordLData.width = 129;
				txtPasswordLData.height = 14;
				txtPassword = new Text(parent, SWT.NONE);
				txtPassword.setEchoChar('*');
				txtPassword.setLayoutData(txtPasswordLData);
			}
			{
				lblPassword = new Label(parent, SWT.NONE);
				FormData lblPasswordLData = new FormData();
				lblPasswordLData.left =  new FormAttachment(0, 1000, 12);
				lblPasswordLData.top =  new FormAttachment(0, 1000, 32);
				lblPasswordLData.width = 55;
				lblPasswordLData.height = 14;
				lblPassword.setLayoutData(lblPasswordLData);
				lblPassword.setText("Password");
			}

		}

			btnLogin = new Button(parent, SWT.PUSH | SWT.CENTER);
			FormData btnLoginLData = new FormData();
			btnLoginLData.left =  new FormAttachment(0, 1000, 49);
			btnLoginLData.top =  new FormAttachment(0, 1000, 58);
			btnLoginLData.width = 59;
			btnLoginLData.height = 28;
			btnLogin.setLayoutData(btnLoginLData);
			btnLogin.setText("Log in");
			btnLogin.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					final String username = txtUsername.getText();
					final String password = txtPassword.getText();
					Job job = new Job("Logging in") {
						
						@Override
						protected IStatus run(IProgressMonitor monitor) {
							if (HALOServiceFactory.getInstance().login(username,password))
								return Status.OK_STATUS;
							else
								return Status.CANCEL_STATUS;
						}
					};
					job.addJobChangeListener(new JobChangeAdapter(){
						@Override
						public void done(final IJobChangeEvent event) {
							Display.getDefault().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									if(event.getResult().isOK())
										getViewSite().getActionBars().getStatusLineManager().setMessage("Logged in");
									else
										getViewSite().getActionBars().getStatusLineManager().setMessage("Log in failed");

								}
							});
						}
					});
					job.schedule();
				}
			});
		}
	
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
