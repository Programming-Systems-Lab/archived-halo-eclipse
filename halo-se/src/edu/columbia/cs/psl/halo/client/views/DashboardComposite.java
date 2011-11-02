package edu.columbia.cs.psl.halo.client.views;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.dialogs.ListDialog;
import org.osgi.service.prefs.BackingStoreException;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.client.Activator;
import edu.columbia.cs.psl.halo.client.FBTokenChecker;
import edu.columbia.cs.psl.halo.client.Util;
import edu.columbia.cs.psl.halo.client.wrapper.UserWrapper;
import edu.columbia.cs.psl.halo.server.stubs.AchievementRecord;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Enrollment;
import edu.columbia.cs.psl.halo.server.stubs.EnrollmentType;
import edu.columbia.cs.psl.halo.server.stubs.Level;
import edu.columbia.cs.psl.halo.server.stubs.Quest;
import edu.columbia.cs.psl.halo.server.stubs.QuestProgress;
import edu.columbia.cs.psl.halo.server.stubs.Task;
import edu.columbia.cs.psl.halo.server.stubs.Title;

public class DashboardComposite extends Composite {

	private DashboardView dashboard;
	private Assignment selectedAssignment;

	private static final int H1_SIZE = 24;
	private static final int H2_SIZE = 18;
	private static final int H3_SIZE = 16;
	private static final int H4_SIZE = 12;

	public Assignment getSelectedAssignment() {
		return selectedAssignment;
	}

	public DashboardComposite(Composite parent, int style,
			DashboardView dashboard) {
		super(parent, style);
		this.dashboard = dashboard;
		initGUI();
		setLayout(new GridLayout(1, true));

		// this.addFocusListener(new FocusAdapter() {
		// @Override
		// public void focusGained(FocusEvent e) {
		// updateWindow();
		// }
		// });
		// updateWindow();
	}

	private Label nameLabel;
	private Thermometer expProgress;
	private Thermometer achievementsProgress;
	private Thermometer questProgressBar;
	private Label curLevel;
	private Label nextLevel;
	private Label leaderLabel;
	
	private Button logout;
	private Composite assignmentsInfo;
	private Composite leaderInfo;

	private Button changePassword;
	private Button facebookLogin;
	private FBTokenChecker fastFBChecker;
	private Button changeTitle;
	IWebBrowser browser;


	private Label recentAchievements;

	Composite topRow;
	Composite bottomRow;
	private void setFontSize(Label l, int size, boolean bold) {
		FontData[] fD = l.getFont().getFontData();
		fD[0].setHeight(size);
		if (bold)
			fD[0].setStyle(SWT.BOLD);
		else
			fD[0].setStyle(SWT.NORMAL);
		l.setFont(new Font(getDisplay(), fD[0]));

	}

	private boolean wasLoggedIn = false;
	/**
	 * Do not call this directly. It is called from DashboardView.facebookLoginUpdated
	 * @param nowLoggedIn
	 */
	public void setFBButtonText(boolean nowLoggedIn) {
		if (wasLoggedIn ^ nowLoggedIn) {
			if (fastFBChecker != null) {
				fastFBChecker.stop();
			}
			if (browser != null) {
				browser.close();
			}
		}
		if (nowLoggedIn) {
			facebookLogin.setText("Log out of Facebook");
			facebookLogin.getShell().layout(true);
		} else if (facebookLogin.getText() != "Log in to Facebook"){
			facebookLogin.setText("Log in to Facebook");
			facebookLogin.getShell().layout(true);
		}
		wasLoggedIn = nowLoggedIn;
	}

	private void changeTitleSelected(SelectionEvent e)
	{

		List<Title> titles = HALOServiceFactory.getInstance().getUserSvc().getMyTitles();
		if(titles.size() == 0)
		{
			MessageDialog.openError(getShell(), "Unable to select a title", "You have not unlocked any titles, so you may not select one to be displayed with your name");
			return;
		}
		else if(titles.size() == 1)
		{
			MessageDialog.openError(getShell(), "Unable to select a title", "You have unlocked only one title, which will be displayed with your name. You can not change your selected title until you unlock more than one.");
		}
		ListDialog dlg = new ListDialog(getShell());
		dlg.setTitle("Select a title to be displayed with your name");
		dlg.setBlockOnOpen(true);
		dlg.setContentProvider(new ArrayContentProvider());
		dlg.setInput(titles.toArray());
		dlg.setLabelProvider(new ILabelProvider() {
			
			@Override
			public void removeListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isLabelProperty(Object element, String property) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getText(Object element) {
				return ((Title) element).getTitle();
			}
			
			@Override
			public Image getImage(Object element) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		dlg.open();
		if(dlg.getResult() != null && dlg.getResult().length == 1)
		{
			HALOServiceFactory.getInstance().getUserSvc().setDefaultTitle(((Title) dlg.getResult()[0]));
			UserWrapper uw = new UserWrapper(HALOServiceFactory.getInstance()
					.getMe());
			uw.setActiveTitle(((Title) dlg.getResult()[0]));
			if (!nameLabel.getText().equals(uw.getFullName()))
				nameLabel.setText(uw.getFullName());
			forceRelayout();
			updateWindow();
			MessageDialog.openInformation(getShell(), "Success", "Your active title selection has been updated");
		}
	}

	private void logoutButtonSelected(SelectionEvent e)
	{
		IEclipsePreferences node = new InstanceScope().getNode(Activator.PLUGIN_ID);

		node.remove("username");
		node.remove("password");
		try {
			node.flush();
		}
		catch(BackingStoreException ex)
		{

		}
		HALOServiceFactory.getInstance().logout();
		IWorkbenchPage page = dashboard.getSite()
				.getPage();
		QuestHUD view = (QuestHUD) page
				.findView(QuestHUD.ID);
		if(view != null)
			view.loggedOut();
		dashboard.loggedOut();
	}
	private void facebookLoginSelected(SelectionEvent e)
	{
		HALOServiceFactory.getInstance().refreshMe();

		IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();
		try {
			browser = support.createBrowser("halo-fb");
			boolean loggedIn = HALOServiceFactory.getInstance().getMe().isFBKeyFlag();
			if (!loggedIn) {
				browser.openURL(new URL("http://www.facebook.com/login.php?api_key=191177150954478&connect_display=popup&v=1.0&next=http://amos.cs.columbia.edu:18585/halo-se-web/FacebookCallback%3Fuid="+HALOServiceFactory.getInstance().getMe().getId()+"&cancel_url=http://www.facebook.com/connect/login_failure.html&fbconnect=true&return_session=true&req_perms=read_stream, publish_stream, offline_access"));
				fastFBChecker = new FBTokenChecker(5, dashboard);
			}
			else
				dashboard.facebookLoginUpdated(false);
		} catch (PartInitException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		};			
	}

	private void changePasswordButtonSelected(SelectionEvent e)
	{
		// TODO Auto-generated method stub
		PasswordChangeDialog newPassDlg = new PasswordChangeDialog(getShell());
		newPassDlg.open();
		String newPass = newPassDlg.getPassword();

		if(newPass != null && !newPass.equals(""))
		{
			HALOServiceFactory.getInstance().getUserSvc().setPassword(UserWrapper.getEncryptedPassword(newPass));
			HALOServiceFactory.getInstance().login(HALOServiceFactory.getInstance().getMe().getEmail(), newPass);

			MessageDialog confDlg = new MessageDialog(getShell(), "Success", null, "Your password was successfully updated", SWT.None, new String[]{"OK"}, 0);
			confDlg.open();
		}
	}
	private void initGUI() {
		GridData d = new GridData();
		d.grabExcessHorizontalSpace = true;
		topRow = new Composite(this, SWT.None);
		topRow.setLayoutData(d);
		topRow.setLayout(new GridLayout(3, false));

		Composite nameArea = new Composite(topRow, SWT.None);
		nameArea.setLayout(new GridLayout(2, false));
		nameLabel = new Label(nameArea, SWT.None);
		nameLabel.setText("Loading...");
		setFontSize(nameLabel, H1_SIZE, true);
		changeTitle = new Button(nameArea, SWT.PUSH);
		changeTitle.setText("Select Title");

		changeTitle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeTitleSelected(e);
			}
		});

		Canvas spacer = new Canvas(topRow, SWT.NONE);
		GridData data = new GridData(GridData.FILL, GridData.FILL, true, true);
		spacer.setLayoutData(data);

		data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = SWT.FILL;
		data.verticalAlignment = SWT.TOP;
		Composite experienceArea = new Composite(topRow, SWT.NONE);
		// topRow.setBackground(new Color(getDisplay(),255,255,0));
		experienceArea.setLayout(new GridLayout(2, false));
		experienceArea.setLayoutData(data);

		Label expPointsLabel = new Label(experienceArea, SWT.None);
		data = new GridData();
		data.verticalAlignment = SWT.BOTTOM;
		expPointsLabel.setLayoutData(data);
		expPointsLabel.setText("Experience Points:");
		setFontSize(expPointsLabel, H2_SIZE, true);

		Composite expProgressArea = new Composite(experienceArea, SWT.NONE);
		data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = SWT.FILL;
		expProgressArea.setLayout(new GridLayout(2, true));
		expProgressArea.setLayoutData(data);

		curLevel = new Label(expProgressArea, SWT.NONE);
		curLevel.setText("Level 1");
		setFontSize(curLevel, 10, false);

		data = new GridData();
		data.horizontalAlignment = SWT.LEFT;
		curLevel.setLayoutData(data);

		nextLevel = new Label(expProgressArea, SWT.NONE);
		nextLevel.setText("Level 2");
		setFontSize(nextLevel, 10, false);

		data = new GridData();
		data.horizontalAlignment = SWT.RIGHT;
		nextLevel.setLayoutData(data);

		expProgress = new Thermometer(expProgressArea, SWT.NONE);
		data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = SWT.FILL;
		data.horizontalSpan = 2;
		expProgress.setLayoutData(data);
		expProgress.setMinimum(0);
		expProgress.setMaximum(1000);
		expProgress.setSelection(700, "Loading...");

		bottomRow = new Composite(this, SWT.None);
		d = new GridData();
		d.grabExcessHorizontalSpace = true;
		d.grabExcessVerticalSpace = true;
		d.horizontalAlignment = GridData.FILL;
		bottomRow.setLayoutData(d);
		bottomRow.setLayout(new GridLayout(7, false));

		spacer = new Canvas(bottomRow, SWT.NONE);
		data = new GridData(GridData.FILL, GridData.FILL, true, true);
		spacer.setLayoutData(data);

		Composite achievementsArea = new Composite(bottomRow, SWT.NONE);
		achievementsArea.setLayout(new GridLayout(1, true));
		data = new GridData();
		data.verticalAlignment = SWT.TOP;
		achievementsArea.setLayoutData(data);
		Composite achievementsBar = new Composite(achievementsArea, SWT.NONE);
		achievementsBar.setLayout(new GridLayout(2, false));
		Label achievementsLabel = new Label(achievementsBar, SWT.None);
		achievementsLabel.setText("Achievements");
		setFontSize(achievementsLabel, H2_SIZE, true);

		achievementsProgress = new Thermometer(achievementsBar, SWT.NONE);
		data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = SWT.FILL;
		achievementsProgress.setLayoutData(data);
		achievementsProgress.setMinimum(0);
		achievementsProgress.setMaximum(1000);
		achievementsProgress.setSelection(700, "");

		spacer = new Canvas(bottomRow, SWT.NONE);
		data = new GridData(GridData.FILL, GridData.FILL, true, true);
		spacer.setLayoutData(data);

		Label recentAchievementsLbl = new Label(achievementsArea, SWT.NONE);
		recentAchievementsLbl.setText("Recent Achievements:");
		setFontSize(recentAchievementsLbl, H3_SIZE, true);

		recentAchievements = new Label(achievementsArea, SWT.NONE);
		recentAchievements
		.setText("Loading... Loading.... Loading...");
		setFontSize(recentAchievements, H3_SIZE, false);

		data = new GridData();
		data.horizontalIndent = 30;
		data.grabExcessHorizontalSpace = true;
		recentAchievements.setLayoutData(data);

		Composite questProgressArea = new Composite(bottomRow, SWT.NONE);
		data = new GridData();
		data.verticalAlignment = SWT.TOP;
		questProgressArea.setLayoutData(data);
		questProgressArea.setLayout(new GridLayout());

		Composite questBar = new Composite(questProgressArea, SWT.NONE);

		questBar.setLayout(new GridLayout(2, false));
		Label questProgressLabel = new Label(questBar, SWT.NONE);
		questProgressLabel.setText("Quest Progress");
		setFontSize(questProgressLabel, H2_SIZE, true);

		questProgressBar = new Thermometer(questBar, SWT.NULL);
		questProgressBar.setMinimum(0);
		questProgressBar.setMaximum(1000);
		questProgressBar.setSelection(700, "Loading...");
		// questBar.setBackground(new Color(getDisplay(),0,255,0));

		assignmentsInfo = new Composite(questBar, SWT.NONE);
		data = new GridData();
		data.horizontalIndent = 30;
		data.horizontalSpan = 2;
		assignmentsInfo.setLayoutData(data);
		assignmentsInfo.setLayout(new GridLayout(3, false));

		spacer = new Canvas(bottomRow, SWT.NONE);
		data = new GridData(GridData.FILL, GridData.FILL, true, true);
		spacer.setLayoutData(data);
		
		leaderInfo = new Composite(bottomRow, SWT.None);
//		leaderLabel = new Label(bottomRow, SWT.None);

		data = new GridData();
		data.verticalAlignment = SWT.TOP;
		leaderInfo.setLayoutData(data);
		leaderInfo.setLayout(new GridLayout());
		Label leaders = new Label(leaderInfo, SWT.None);
		leaders.setText("Leaders");
		setFontSize(leaders, H3_SIZE, true);
		leaderLabel = new Label(leaderInfo, SWT.None);
		leaderLabel.setText("Loading...\nLoading...\nLoading...");
		
		spacer = new Canvas(bottomRow, SWT.NONE);
		data = new GridData(GridData.FILL, GridData.FILL, true, true);
		spacer.setLayoutData(data);

		final Composite buttons = new Composite(this, SWT.NONE);
		buttons.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false));
		buttons.setLayout(new FillLayout());

		facebookLogin = new Button(buttons, SWT.PUSH);
		facebookLogin.setText("Log in to Facebook");

		changePassword = new Button(buttons,SWT.PUSH);
		changePassword.setText("Change Password");

		logout = new Button(buttons, SWT.PUSH);
		logout.setText("Logout");



		logout.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				logoutButtonSelected(e);
			}
		});


		facebookLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				facebookLoginSelected(e);
			}
		});


		changePassword.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changePasswordButtonSelected(e);
			}

		});
		this.getParent().addListener(SWT.Resize, new Listener() {

			@Override
			public void handleEvent(Event event) {
				((GridData) topRow.getLayoutData()).widthHint = getParent()
						.getBounds().width;
				layout(true);
			}
		});
	}

	public void updateWindow() {
		if (HALOServiceFactory.getInstance().isLoggedIn()) {

			Job j = new Job("Updating Dashboard Display") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					final String leaders = HALOServiceFactory.getInstance().getUserSvc().getLeadersStr();
					HALOServiceFactory.getInstance().refreshMe();
					final Level nextLevelLvl = HALOServiceFactory.getInstance().getUserSvc()
							.getLevel(HALOServiceFactory.getInstance().getMe().getLevel().getLevel() + 1);
					//					final ArrayList<Thermometer> ts = new ArrayList<Thermometer>();
					final HashMap<Task, QuestProgress> progress = new HashMap<Task, QuestProgress>();
					for (QuestProgress p : HALOServiceFactory.getInstance()
							.getUserSvc().getMyProgress()) {
						progress.put(p.getTask(), p);
					}
					final HashMap<Assignment,ArrayList<Quest>> quests = new HashMap<Assignment, ArrayList<Quest>>();
					for (Enrollment e : HALOServiceFactory.getInstance().getUserSvc()
							.getEnrollments()) {
						if (e.getCourse() != null && e.getType().equals(EnrollmentType.STUDENT)) {
							for (Assignment a : HALOServiceFactory.getInstance()
									.getUserSvc().getAssignmentsFor(e.getCourse())) {
								quests.put(a, new ArrayList<Quest>());

								for (Quest q : HALOServiceFactory.getInstance()
										.getUserSvc().getAllQuestsFor(a)) {
									quests.get(a).add(q);
								}
							}
						}
					}

					String recentAchievemntsTxt = "";

					int n = 0;
					for (AchievementRecord a : HALOServiceFactory.getInstance()
							.getUserSvc().getMyAchievements()) {
						recentAchievemntsTxt += a.getAchievement().getName() + " - "
								+ Util.formatDate(a.getCompletionTime()) + "\n";
						n++;
						if(n > 5)
							break;
					}

					final String achvTxt = recentAchievemntsTxt;
					final int maxAchievement = HALOServiceFactory.getInstance().getUserSvc().getMaxAchievementPts();

					Display.getDefault().syncExec(new Runnable() {

						@Override
						public void run() {
							leaderLabel.setText(leaders);
							leaderInfo.layout(true);
							UserWrapper uw = new UserWrapper(HALOServiceFactory.getInstance()
									.getMe());
							if (!nameLabel.getText().equals(uw.getFullName()))
								nameLabel.setText(uw.getFullName());

							topRow.layout(true);
							if (!curLevel.getText().equals("Level " + uw.getLevel().getLevel()))
								curLevel.setText("Level " + uw.getLevel().getLevel());

							//Update the facebook login stuff
							dashboard.facebookLoginUpdated(uw.isFBKeyFlag());
							
							achievementsProgress.setMaximum(maxAchievement);

							for (Control c : assignmentsInfo.getChildren())
								c.dispose();
							int n_quests = 0;
							int n_quests_done = 0;
							for(Assignment a : quests.keySet())
							{
								Label l = new Label(assignmentsInfo, SWT.NONE);
								l.setText(a.getTitle());
								int max = 0;
								int done = 0;
								for(Quest q : quests.get(a))
								{
									boolean complete =true;
									for(Task ta : q.getTasks())
									{
										if(!(progress.containsKey(ta) && progress.get(ta).isCompleted()))
											complete = false;
									}
									if (complete) {
										done++;
										n_quests_done++;
									}
									n_quests++;
									max++;
								}
								Thermometer t = new Thermometer(assignmentsInfo,
										SWT.NONE);
								t.setMinimum(0);
								t.setMaximum(max);
								t.setSelection(done, done + " of " + max + " quests");
								//								ts.add(t);

								Label ll = new Label(assignmentsInfo, SWT.NONE);
								ll.setText("Due " + Util.getDueStrHuman(a.getDueOn()));

							}
							if (!nextLevel.getText().equals(
									"Level " + (uw.getLevel().getLevel() + 1)))
								nextLevel.setText("Level " + (uw.getLevel().getLevel() + 1));

							expProgress.setMaximum(nextLevelLvl.getXpRequired());
							if (expProgress.getValue() != uw.getXp())
								expProgress.setSelection(uw.getXp(), uw.getXp() + " of "
										+ nextLevelLvl.getXpRequired());


							questProgressBar.setMinimum(0);
							questProgressBar.setMaximum(n_quests);
							questProgressBar.setSelection(n_quests_done, n_quests_done + " of " + n_quests);

							if (!recentAchievements.getText().equals(achvTxt))
							{
								recentAchievements.setText(achvTxt);
							}

							if (achievementsProgress.getValue() != uw.getAchievementPoints())
								achievementsProgress.setSelection(uw.getAchievementPoints(),
										uw.getAchievementPoints() + " points of " + maxAchievement);

							forceRelayout();
							
						}
					});
					return Status.OK_STATUS;
				}
			};
			j.schedule();
		}
	}
	private void forceRelayout()
	{
//		pack(true);
		dashboard.dashboardComposite.layout(true,true);
		
		((GridData) topRow.getLayoutData()).widthHint = getParent()
				.getBounds().width;
		((GridData) bottomRow.getLayoutData()).widthHint = getParent()
				.getBounds().width;
		
		Rectangle r = dashboard.dashboardScroller.getClientArea();
		dashboard.dashboardScroller.setMinSize(dashboard.dashboardComposite.computeSize(
				r.width, SWT.DEFAULT));
		
	}

}
