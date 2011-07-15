package edu.columbia.cs.psl.halo.client.views;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.handlers.IHandlerService;

import edu.columbia.cs.psl.halo.HALOServiceFactory;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Course;
import edu.columbia.cs.psl.halo.server.stubs.Enrollment;
import edu.columbia.cs.psl.halo.server.stubs.EnrollmentType;
import edu.columbia.cs.psl.halo.server.stubs.Quest;

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
	public DashboardComposite(Composite parent, int style, DashboardView dashboard) {
		super(parent, style);
		this.dashboard = dashboard;
		initGUI();
		setLayout(new GridLayout(1,true));
//		this.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusGained(FocusEvent e) {
//				updateWindow();
//			}
//		});
//		updateWindow();
	}
	private Label nameLabel;
	private Thermometer expProgress;
	private Thermometer achievementsProgress;
	private Label achievementsStatus;
	private Thermometer questProgressBar;
	private Label curLevel;
	private Label nextLevel;
	
	private Composite assignmentsInfo;
	
	private Label recentAchievements;
	
	Composite topRow;
	private void setFontSize(Label l, int size, boolean bold)
	{
		FontData[] fD = l.getFont().getFontData();
		fD[0].setHeight(size);
		if(bold)
			fD[0].setStyle(SWT.BOLD);
		else
			fD[0].setStyle(SWT.NORMAL);
		l.setFont( new Font(getDisplay(),fD[0]));

	}
	private void initGUI() {
		GridData d= new GridData();
		d.grabExcessHorizontalSpace=true;
		topRow = new Composite(this, SWT.None);
		topRow.setLayoutData(d);
		topRow.setLayout(new GridLayout(3, false));
		
		nameLabel = new Label(topRow, SWT.None);
		nameLabel.setText("Your name here!");
		setFontSize(nameLabel, H1_SIZE, true);
		
		Canvas spacer = new Canvas(topRow, SWT.NONE);
		GridData data = new GridData(GridData.FILL,GridData.FILL,true,true);
		spacer.setLayoutData(data);
		
		data = new GridData();
		data.grabExcessHorizontalSpace=true;
		data.horizontalAlignment=SWT.FILL;
		data.verticalAlignment=SWT.TOP;
		Composite experienceArea = new Composite(topRow, SWT.NONE);
//		topRow.setBackground(new Color(getDisplay(),255,255,0));
		experienceArea.setLayout(new GridLayout(2,false));
		experienceArea.setLayoutData(data);
		
		
		Label expPointsLabel = new Label(experienceArea, SWT.None);
		data =new GridData();
		data.verticalAlignment=SWT.BOTTOM;
		expPointsLabel.setLayoutData(data);
		expPointsLabel.setText("Experience Points:");
		setFontSize(expPointsLabel, H2_SIZE, true);
		
		Composite expProgressArea = new Composite(experienceArea, SWT.NONE);
		data = new GridData();
		data.grabExcessHorizontalSpace=true;
		data.horizontalAlignment=SWT.FILL;
		expProgressArea.setLayout(new GridLayout(2,true));
		expProgressArea.setLayoutData(data);
		
		curLevel = new Label(expProgressArea,SWT.NONE);
		curLevel.setText("Level 1");
		setFontSize(curLevel, 10, false);

		data = new GridData();
		data.horizontalAlignment=SWT.LEFT;
		curLevel.setLayoutData(data);
		
		nextLevel = new Label(expProgressArea, SWT.NONE);
		nextLevel.setText("Level 2");
		setFontSize(nextLevel, 10, false);

		data = new GridData();
		data.horizontalAlignment=SWT.RIGHT;
		nextLevel.setLayoutData(data);
		
		expProgress = new Thermometer(expProgressArea, SWT.NONE);
		data = new GridData();
		data.grabExcessHorizontalSpace=true;
		data.horizontalAlignment=SWT.FILL;	
		data.horizontalSpan=2;
		expProgress.setLayoutData(data);
		expProgress.setMinimum(0);
		expProgress.setMaximum(1000);
		expProgress.setSelection(700,"Partway there");
		

		
		Composite bottomRow = new Composite(this, SWT.None);
		d = new GridData();
		d.grabExcessHorizontalSpace=true;
		d.horizontalAlignment=GridData.FILL;
		bottomRow.setLayoutData(d);
		bottomRow.setLayout(new GridLayout(5, false));
//		bottomRow.setBackground(new Color(getDisplay(),255,255,0));

		spacer = new Canvas(bottomRow, SWT.NONE);
		data = new GridData(GridData.FILL,GridData.FILL,true,true);
		spacer.setLayoutData(data);
		
		Composite achievementsArea = new Composite(bottomRow, SWT.NONE);
		achievementsArea.setLayout(new GridLayout(1,true));
//		data = new GridData();
//		data.horizontalAlignment=GridData.FILL;
//		data.grabExcessHorizontalSpace = true;
//		achievementsArea.setLayoutData(data);
		
		Composite achievementsBar = new Composite(achievementsArea, SWT.NONE);
		achievementsBar.setLayout(new GridLayout(2, false));
		Label achievementsLabel = new Label(achievementsBar, SWT.None);
		achievementsLabel.setText("Achievements");
		setFontSize(achievementsLabel, H2_SIZE, true);

		achievementsProgress = new Thermometer(achievementsBar, SWT.NONE);
		data = new GridData();
		data.grabExcessHorizontalSpace=true;
		data.horizontalAlignment=SWT.FILL;	
		achievementsProgress.setLayoutData(data);
		achievementsProgress.setMinimum(0);
		achievementsProgress.setMaximum(1000);
		achievementsProgress.setSelection(700, "Foobar");
		
		achievementsStatus = new Label(achievementsArea, SWT.NONE);
		achievementsStatus.setText("3 achievements completed of 15");
		setFontSize(achievementsStatus, H3_SIZE, false);

		
		spacer = new Canvas(bottomRow, SWT.NONE);
		data = new GridData(GridData.FILL,GridData.FILL,true,true);
		spacer.setLayoutData(data);
		
		Label recentAchievementsLbl = new Label(achievementsArea, SWT.NONE);
		recentAchievementsLbl.setText("Recent Achievements:");
		setFontSize(recentAchievementsLbl, H3_SIZE, true);

		recentAchievements = new Label(achievementsArea,SWT.NONE);
		recentAchievements.setText("Flash's quest - 3/6/11\nWelcome aboard! - 3/5/11");
		setFontSize(recentAchievements, H3_SIZE, false);

		data = new GridData();
		data.horizontalIndent=30;
		recentAchievements.setLayoutData(data);
		
		Composite questProgressArea = new Composite(bottomRow, SWT.NONE);
		data = new GridData();
		data.verticalAlignment=SWT.TOP;
		questProgressArea.setLayoutData(data);
		questProgressArea.setLayout(new GridLayout());
		
		Composite questBar = new Composite(questProgressArea, SWT.NONE);
//		questBar.setBackground(new Color(getDisplay(),0,255,0));

		questBar.setLayout(new GridLayout(2,false));
		Label questProgressLabel = new Label(questBar, SWT.NONE);
		questProgressLabel.setText("Quest Progress");
		setFontSize(questProgressLabel, H2_SIZE, true);

		questProgressBar = new Thermometer(questBar, SWT.NULL);
		questProgressBar.setMinimum(0);
		questProgressBar.setMaximum(1000);
		questProgressBar.setSelection(700, "Foobar");
		
		assignmentsInfo = new Composite(questBar, SWT.NONE);
		data = new GridData();
		data.horizontalIndent=30;
		assignmentsInfo.setLayoutData(data);
		assignmentsInfo.setLayout(new GridLayout(3, false));
		
		spacer = new Canvas(bottomRow, SWT.NONE);
		data = new GridData(GridData.FILL,GridData.FILL,true,true);
		spacer.setLayoutData(data);
		
		this.getParent().addListener(SWT.Resize, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				((GridData) topRow.getLayoutData()).widthHint=getParent().getBounds().width;
				layout(true);
			}
		});
	}
	private void updateWindow() {
//		lblLoggedInAs.setText("Logged in as: " + HALOServiceFactory.getInstance().getMe().getEmail());
//		for(TreeItem i : assignmentsTree.getItems())
//			i.dispose();
//		for(Enrollment c : HALOServiceFactory.getInstance().getUserSvc().getEnrollments())
//		{
//			if(c.getType().equals(EnrollmentType.STUDENT))
//			{
//				TreeItem courseRoot = new TreeItem(assignmentsTree, SWT.NONE);
//				courseRoot.setText(c.getCourse().getName());
//				courseRoot.setExpanded(true);
//				for(Assignment a : HALOServiceFactory.getInstance().getUserSvc().getAssignmentsFor(c.getCourse()))
//				{
//					TreeItem assignmentRoot = new TreeItem(courseRoot, SWT.NONE);
//					assignmentRoot.setText(a.getTitle());
//					assignmentRoot.setExpanded(true);
//					assignmentRoot.setData(a);
//					System.out.println(assignmentRoot);
//					for(Quest t : HALOServiceFactory.getInstance().getUserSvc().getQuestsFor(a))
//					{
//						TreeItem questRoot = new TreeItem(assignmentRoot, SWT.NONE);
//						questRoot.setText(t.getName());
//					}
//				}
//			}
//		}
//		
	}
	
}
