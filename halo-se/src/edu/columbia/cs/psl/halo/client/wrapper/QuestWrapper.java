package edu.columbia.cs.psl.halo.client.wrapper;

import edu.columbia.cs.psl.halo.server.stubs.Course;
import edu.columbia.cs.psl.halo.server.stubs.Assignment;
import edu.columbia.cs.psl.halo.server.stubs.Quest;
import edu.columbia.cs.psl.halo.server.stubs.QuestProgress;

public class QuestWrapper {
	private Quest quest;
	private Course course;
	private QuestProgress progress;
	private Assignment assignment;
	
	public QuestWrapper(Quest q, Assignment a, Course c, QuestProgress p)
	{
		quest = q;
		assignment = a;
		course = c;
		progress =p;
	}
	public Quest getQuest() {
		return quest;
	}
	public Course getCourse() {
		return course;
	}
	public QuestProgress getProgress() {
		return progress;
	}
	public Assignment getAssignment() {
		return assignment;
	}
}
