package edu.columbia.cs.psl.halo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class QuestProgress implements Serializable{

	private static final long serialVersionUID = -1127318809166897519L;
	private int id;
	private Quest quest;
	private Task task;
	private User user;
	private boolean completed;
	private Date updated;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Quest getQuest() {
		return quest;
	}
	public void setQuest(Quest quest) {
		this.quest = quest;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof QuestProgress)
		{
			return ((QuestProgress) obj).getId() == getId();
		}
		return false;
	}
	@Override
	public int hashCode() {
		return 29 * getId() + this.getClass().getName().hashCode();
	}
}
