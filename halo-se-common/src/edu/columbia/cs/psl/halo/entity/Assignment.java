package edu.columbia.cs.psl.halo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Assignment implements Serializable {
	private static final long serialVersionUID = -5087782653671688079L;
	private int id;
	private String title;
	private String description;
	private Date dueOn;
	private Date assignedOn;

	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDueOn() {
		return dueOn;
	}
	public void setDueOn(Date dueOn) {
		this.dueOn = dueOn;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAssignedOn() {
		return assignedOn;
	}
	public void setAssignedOn(Date assignedOn) {
		this.assignedOn = assignedOn;
	}

}
