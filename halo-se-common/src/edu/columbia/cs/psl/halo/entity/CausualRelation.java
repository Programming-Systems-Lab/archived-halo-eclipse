package edu.columbia.cs.psl.halo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CausualRelation {
	private int id;
	
	private Achievement achievementCause;
	private Task taskCause;
	
	private Achievement achievementResult;
	private Task taskResult;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	public Achievement getAchievementCause() {
		return achievementCause;
	}
	public void setAchievementCause(Achievement achievementCause) {
		this.achievementCause = achievementCause;
	}
	@ManyToOne
	public Task getTaskCause() {
		return taskCause;
	}
	public void setTaskCause(Task taskCause) {
		this.taskCause = taskCause;
	}
	
	@ManyToOne
	public Achievement getAchievementResult() {
		return achievementResult;
	}
	public void setAchievementResult(Achievement achievementResult) {
		this.achievementResult = achievementResult;
	}
	@ManyToOne
	public Task getTaskResult() {
		return taskResult;
	}
	public void setTaskResult(Task taskResult) {
		this.taskResult = taskResult;
	}

	
	
}
