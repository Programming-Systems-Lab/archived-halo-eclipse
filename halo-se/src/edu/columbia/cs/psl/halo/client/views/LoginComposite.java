package edu.columbia.cs.psl.halo.client.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;

import edu.columbia.cs.psl.halo.HALOServiceFactory;

public class LoginComposite extends Composite {
	private Button loginButton;

	private final static int F_LABEL_HORIZONTAL_INDENT = 10;

	private final static int F_BUTTON_WIDTH_HINT = 80;

	private final static int F_TEXT_WIDTH_HINT = 175;

	private final static int F_COLUMN_COUNT = 3;

	private Text fTextUsername;

	private Text fTextPassword;

	private Button rememberMe;

	private Button fButtonOK;

	private Button fButtonCancel;

	private DashboardView dashboard;

	public LoginComposite(Composite parent, int style, DashboardView dashboard) {
		super(parent, style);
		this.dashboard = dashboard;
		GridLayout layout = new GridLayout(F_COLUMN_COUNT, false);
		setLayout(layout);
		initGUI();
	}

	private void initGUI() {

		// Create the blank spanner
		// createUICompositeBlank();
		// Create the user name label
		createUILabelUserName();
		// Create the user name text widget
		createUITextUserName();
		// Create the password label
		createUILabelPassword();
		// Create the password text widget
		createUITextPassword();
		// createUILabelBlank();
		//
		createUIRememberMe();

		// Create the blank label
		createUILabelBlank();
		// Create the OK button
		createUIButtonOK();
		createUILabelBlank();
		createUILabelBlank();
		createProgressInfo();

		// TODO delete this to turn off auto login
		// fTextPassword.setText("test123");
		// fTextUsername.setText("jon");
		// handleButtonOKWidgetSelected();

		// Create the cancel button
		// createUIButtonCancel();
	}

	private ProgressBar progressBar;

	private void createUIRememberMe() {
		Label label = new Label(this, SWT.NONE);
		label.setText("&Remember me:"); //$NON-NLS-1$
		// Configure layout data
		label.setForeground(new Color(null, 0, 0, 0));
		GridData data = new GridData();
		// data.horizontalIndent = F_LABEL_HORIZONTAL_INDENT;
		label.setLayoutData(data);

		rememberMe = new Button(this, SWT.CHECK);
		rememberMe.setSelection(true);
		data = new GridData(SWT.NONE, SWT.NONE, false, false);
		// data.widthHint = F_BUTTON_WIDTH_HINT;
		data.horizontalSpan = 2;
		rememberMe.setLayoutData(data);
	}

	private void createProgressInfo() {

		progressBar = new ProgressBar(this, SWT.NONE | SWT.INDETERMINATE);
		GridData data = new GridData(SWT.NONE, SWT.NONE, false, false);
		data.widthHint = F_TEXT_WIDTH_HINT;
		data.horizontalSpan = 2;
		progressBar.setLayoutData(data);
		progressBar.setVisible(false);
	}

	/**
	 * 
	 */
	private void createUIButtonCancel() {
		// Create the button
		fButtonCancel = new Button(this, SWT.PUSH);
		fButtonCancel.setText("Cancel"); //$NON-NLS-1$
		// Configure layout data
		GridData data = new GridData(SWT.NONE, SWT.NONE, false, false);
		data.widthHint = F_BUTTON_WIDTH_HINT;
		data.verticalIndent = 10;
		fButtonCancel.setLayoutData(data);
		fButtonCancel.setVisible(false);
	}

	/**
	 * 
	 */
	private void createUIButtonOK() {
		// Create the button
		fButtonOK = new Button(this, SWT.PUSH);
		fButtonOK.setText("OK"); //$NON-NLS-1$
		// Configure layout data
		GridData data = new GridData(SWT.NONE, SWT.NONE, false, false);
		data.widthHint = F_BUTTON_WIDTH_HINT;
		data.verticalIndent = 10;
		fButtonOK.setLayoutData(data);
		fButtonOK.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				handleButtonOKWidgetSelected();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 
	 */
	private void createUILabelBlank() {
		Label label = new Label(this, SWT.NONE);
		label.setVisible(false);
	}

	/**
	 * 
	 */
	private void createUITextPassword() {
		// Create the text widget
		int style = SWT.PASSWORD | SWT.BORDER;
		fTextPassword = new Text(this, style);

		fTextPassword.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR)
					handleButtonOKWidgetSelected();
			}
		});
		// Configure layout data
		GridData data = new GridData(SWT.NONE, SWT.NONE, false, false);
		data.widthHint = F_TEXT_WIDTH_HINT;
		data.horizontalSpan = 2;
		fTextPassword.setLayoutData(data);
	}

	private void handleButtonOKWidgetSelected() {
		final String username = fTextUsername.getText();
		final String password = fTextPassword.getText();
		// any password
		if ((username.length() > 0) && (password.length() > 0)) {
			progressBar.setVisible(true);
			Job job = new Job("Logging in") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					if (HALOServiceFactory.getInstance().login(username,
							password)) {
						return Status.OK_STATUS;
					} else {
						return Status.CANCEL_STATUS;
					}
				}
			};

			job.schedule();
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {

					if (event.getResult().isOK()) {
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								dashboard.loggedIn();
								IWorkbenchPage page = dashboard.getSite()
										.getPage();
								QuestHUD view = (QuestHUD) page
										.findView(QuestHUD.ID);
								view.loggedIn();
							}
						});

					} else {
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								MessageDialog.openError(getShell(),
										"Authentication Failed", //$NON-NLS-1$
										"Invalid username or password."); //$NON-NLS-1$
								progressBar.setVisible(false);
							}
						});
					}
				}
			});
		} else {
			MessageDialog.openError(this.getShell(), "Authentication Failed", //$NON-NLS-1$
					"A username and password must be specified to login."); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 */
	private void createUILabelPassword() {
		// Create the label
		Label label = new Label(this, SWT.NONE);
		label.setText("&Password:"); //$NON-NLS-1$
		// Configure layout data
		label.setForeground(new Color(null, 0, 0, 0));
		GridData data = new GridData();
		data.horizontalIndent = F_LABEL_HORIZONTAL_INDENT;
		label.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void createUITextUserName() {
		// Create the text widget
		fTextUsername = new Text(this, SWT.BORDER);
		// Configure layout data
		GridData data = new GridData(SWT.NONE, SWT.NONE, false, false);
		data.widthHint = F_TEXT_WIDTH_HINT;
		data.horizontalSpan = 2;
		fTextUsername.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void createUILabelUserName() {
		// Create the label
		Label label = new Label(this, SWT.NONE);
		label.setText("&User Name:"); //$NON-NLS-1$
		label.setForeground(new Color(null, 0, 0, 0));
		// Configure layout data
		GridData data = new GridData();
		data.horizontalIndent = F_LABEL_HORIZONTAL_INDENT;
		label.setLayoutData(data);
	}

}
