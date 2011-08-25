package edu.columbia.cs.psl.halo.client.views;

import java.util.HashMap;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbenchPage;
import org.osgi.service.prefs.BackingStoreException;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.client.Activator;
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
	private Button logout;
	private Composite assignmentsInfo;
	private Button changePassword;
	
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

	private void initGUI() {
		GridData d = new GridData();
		d.grabExcessHorizontalSpace = true;
		topRow = new Composite(this, SWT.None);
		topRow.setLayoutData(d);
		topRow.setLayout(new GridLayout(3, false));

		nameLabel = new Label(topRow, SWT.None);
		nameLabel.setText("Your name here!");
		setFontSize(nameLabel, H1_SIZE, true);

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
		expProgress.setSelection(700, "Partway there");

		bottomRow = new Composite(this, SWT.None);
		d = new GridData();
		d.grabExcessHorizontalSpace = true;
		d.grabExcessVerticalSpace = true;
		d.horizontalAlignment = GridData.FILL;
		bottomRow.setLayoutData(d);
		bottomRow.setLayout(new GridLayout(5, false));

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
				.setText("Flash's quest - super long thing here\nWelcome aboard! - 3/5/11");
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
		questProgressBar.setSelection(700, "Foobar");
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

		Composite buttons = new Composite(this, SWT.NONE);
		buttons.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false));
		buttons.setLayout(new FillLayout());
		
		changePassword = new Button(buttons,SWT.PUSH);
		changePassword.setText("Change Password");
		
		logout = new Button(buttons, SWT.PUSH);
		logout.setText("Logout");


		
		logout.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				IEclipsePreferences node = InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID);
				
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
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		changePassword.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
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
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
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
			UserWrapper uw = new UserWrapper(HALOServiceFactory.getInstance()
					.getMe());
			if (!nameLabel.getText().equals(uw.getFullName()))
				nameLabel.setText(uw.getFullName());

			topRow.layout(true);
			if (!curLevel.getText().equals("Level " + uw.getLevel().getLevel()))
				curLevel.setText("Level " + uw.getLevel().getLevel());

			Level nextLevelLvl = HALOServiceFactory.getInstance().getUserSvc()
					.getLevel(uw.getLevel().getLevel() + 1);

			if (!nextLevel.getText().equals(
					"Level " + (uw.getLevel().getLevel() + 1)))
				nextLevel.setText("Level " + (uw.getLevel().getLevel() + 1));

			if (expProgress.getValue() != uw.getXp())
				expProgress.setSelection(uw.getXp(), uw.getXp() + " of "
						+ nextLevelLvl.getXpRequired());

			int maxAchievement = HALOServiceFactory.getInstance().getUserSvc().getMaxAchievementPts();
			achievementsProgress.setMaximum(maxAchievement);

			if (achievementsProgress.getValue() != uw.getAchievementPoints())
				achievementsProgress.setSelection(uw.getAchievementPoints(),
						uw.getAchievementPoints() + " points of " + maxAchievement);
			// achievementsStatus.setText("3 achievements completed of 15");
			String recentAchievemntsTxt = "";

			for (AchievementRecord a : HALOServiceFactory.getInstance()
					.getUserSvc().getMyAchievements()) {
				recentAchievemntsTxt += a.getAchievement().getName() + " - "
						+ Util.formatDate(a.getCompletionTime()) + "\n";
			}
			if (!recentAchievements.getText().equals(recentAchievemntsTxt))
			{
				recentAchievements.setText(recentAchievemntsTxt);
			}
			
			for (Control c : assignmentsInfo.getChildren())
				c.dispose();
			assignmentsInfo.layout(true);
			assignmentsInfo.setLayout(new GridLayout(3, false));

			HashMap<Task, QuestProgress> progress = new HashMap<Task, QuestProgress>();
			for (QuestProgress p : HALOServiceFactory.getInstance()
					.getUserSvc().getMyProgress()) {
				progress.put(p.getTask(), p);
			}
			int n_quests = 0;
			int n_quests_done = 0;
			for (Enrollment e : HALOServiceFactory.getInstance().getUserSvc()
					.getEnrollments()) {
				if (e.getType().equals(EnrollmentType.STUDENT)) {
					for (Assignment a : HALOServiceFactory.getInstance()
							.getUserSvc().getAssignmentsFor(e.getCourse())) {
						Label l = new Label(assignmentsInfo, SWT.NONE);
						l.setText(a.getTitle());
						Thermometer t = new Thermometer(assignmentsInfo,
								SWT.NONE);
						int max = 0;
						int done = 0;
						for (Quest q : HALOServiceFactory.getInstance()
								.getUserSvc().getAllQuestsFor(a)) {
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
						t.setMinimum(0);
						t.setMaximum(max);
						t.setSelection(done, done + " of " + max + " quests");

						Label ll = new Label(assignmentsInfo, SWT.NONE);
						ll.setText("Due " + Util.getDueStrHuman(a.getDueOn()));
					}
				}
			}
			questProgressBar.setMinimum(0);
			questProgressBar.setMaximum(n_quests);
			questProgressBar.setSelection(n_quests_done, n_quests_done + " of " + n_quests);
			
			pack(true);
			this.getShell().layout(true);
			((GridData) topRow.getLayoutData()).widthHint = getParent()
					.getBounds().width;
			((GridData) bottomRow.getLayoutData()).widthHint = getParent()
					.getBounds().width;
		}
	}

}
