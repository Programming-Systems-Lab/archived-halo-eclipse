package edu.columbia.cs.psl.halo.client.views;

import java.io.File;
import java.net.MalformedURLException;
import java.security.PrivilegedAction;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.osgi.service.prefs.BackingStoreException;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.client.Activator;


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
public class LoginComposite extends Composite {
	private Button loginButton;
	public final static String PREF_KEY = "edu.columbia.cs.psl.halo.client";
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

	private Composite loginForm;
	
	private Image loginImage;



	public LoginComposite(Composite parent, int style, DashboardView dashboard) {
		super(parent, style);
		this.dashboard = dashboard;
		setLayout(new GridLayout(2, false));
		
//		 ImageDescriptor desc = Activator.getImageDescriptor("icons/login.bmp");
//				loginImage= desc.createImage();

		 Label img = new Label(this, SWT.None);
//		img.setImage(loginImage);
				
		loginForm = new Composite(this, style);
		GridLayout layout = new GridLayout(F_COLUMN_COUNT, false);
		loginForm.setLayout(layout);
		Label loginInst = new Label(loginForm,SWT.None);
		loginInst.setText("Login to HALO");
		GridData data = new GridData();
		data.horizontalSpan=3;
		data.horizontalAlignment=SWT.CENTER;
		loginInst.setLayoutData(data);
		FontData[] fd = loginInst.getFont().getFontData();
		fd[0].setHeight(18);
		fd[0].setStyle(SWT.BOLD);
		loginInst.setFont(new Font(parent.getDisplay(), fd[0]));
		initGUI();
//		loginForm.layout(true);
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
		IEclipsePreferences node = InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID);
		String username = node.get("username", null);
		String pass = node.get("password", null);
		if(username != null & pass != null)
		{
			fTextPassword.setText(pass);
			fTextUsername.setText(username);
			handleButtonOKWidgetSelected();
		}
		// Create the cancel button
		// createUIButtonCancel();
	}

	private ProgressBar progressBar;

	private void createUIRememberMe() {
		Label label = new Label(loginForm , SWT.NONE);
		label.setText("&Remember me:"); //$NON-NLS-1$
		// Configure layout data
		label.setForeground(new Color(null, 0, 0, 0));
		GridData data = new GridData();
		// data.horizontalIndent = F_LABEL_HORIZONTAL_INDENT;
		label.setLayoutData(data);

		rememberMe = new Button(loginForm , SWT.CHECK);
		rememberMe.setSelection(true);
		data = new GridData(SWT.NONE, SWT.NONE, false, false);
		// data.widthHint = F_BUTTON_WIDTH_HINT;
		data.horizontalSpan = 2;
		rememberMe.setLayoutData(data);
	}

	private void createProgressInfo() {
		{
			progressBar = new ProgressBar(loginForm , SWT.NONE | SWT.INDETERMINATE);
			GridData data = new GridData();
			data.widthHint = 175;
			data.horizontalSpan = 2;
			data.verticalAlignment = GridData.BEGINNING;
			data.horizontalAlignment = GridData.BEGINNING;
			progressBar.setLayoutData(data);
		}
		progressBar.setVisible(false);
	}

	/**
	 * 
	 */
	private void createUIButtonCancel() {

		// Create the button
		fButtonCancel = new Button(loginForm , SWT.PUSH);
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
		fButtonOK = new Button(loginForm , SWT.PUSH);
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
		Label label = new Label(loginForm , SWT.NONE);
		label.setVisible(false);
	}

	/**
	 * 
	 */
	private void createUITextPassword() {
		// Create the text widget
		int style = SWT.PASSWORD | SWT.BORDER;
		fTextPassword = new Text(loginForm , style);

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
	
	
	private String rememberToken = null;
	private void handleButtonOKWidgetSelected() {
		final String username = fTextUsername.getText();
		final String password = fTextPassword.getText();
		fTextUsername.setText("");
		fTextPassword.setText("");
		// any password
		if ((username.length() > 0) && (password.length() > 0)) {
			progressBar.setVisible(true);
			Job job = new Job("Logging in") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					boolean res = false;
					if(password.substring(0, 1).equals("<"))
					{
						res = HALOServiceFactory.getInstance().loginFromRememberMe(username,password);
						if(res)
							HALOServiceFactory.getInstance().log("LoginFromRememberMe", null);
					}
					else
					{
						try {
							res = HALOServiceFactory.getInstance().login(username,
											password);
							if(res)
								HALOServiceFactory.getInstance().log("LoginFromUsernamePassword", null);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						res = 
					}
					if (res) {
						rememberToken =  HALOServiceFactory.getInstance().getUserSvc().getRememberMeToken();
					
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
								progressBar.setVisible(false);

								IEclipsePreferences node = InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID);
								if(rememberMe.getSelection())
								{
									node.put("username", username);
									node.put("password",rememberToken);
								}
								else
								{
									node.remove("username");
									node.remove("password");
								}
								try {
									node.flush();
								} catch (BackingStoreException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								dashboard.loggedIn();
								IWorkbenchPage page = dashboard.getSite()
										.getPage();
								QuestHUD view = (QuestHUD) page
										.findView(QuestHUD.ID);
								if(view != null)
									view.loggedIn();
							}
						});

					} else {
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								MessageDialog.openError(getShell(),
										"Authentication Failed", //$NON-NLS-1$
										"Invalid username or password. If you need assistance logging in (ex, password reset), please email Jonathan Bell at jbell@cs.columbia.edu"); //$NON-NLS-1$
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
		Label label = new Label(loginForm , SWT.NONE);
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
		fTextUsername = new Text(loginForm , SWT.BORDER);
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
		Label label = new Label(loginForm , SWT.NONE);
		label.setText("&User Name:"); //$NON-NLS-1$
		label.setForeground(new Color(null, 0, 0, 0));
		// Configure layout data
		GridData data = new GridData();
		data.horizontalIndent = F_LABEL_HORIZONTAL_INDENT;
		label.setLayoutData(data);
	}

}
