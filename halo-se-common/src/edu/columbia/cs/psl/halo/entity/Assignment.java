package edu.columbia.cs.psl.halo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="studentAsisgnment")
public class Assignment extends LazyCycleBreaker implements Serializable {
	private static final long serialVersionUID = -5087782653671688079L;
	private int id;
	private String title;
	private String description;
	private Date dueOn;
	private Date assignedOn;
	private Course course;
	private List<Quest> quests;
	
	@XmlElement
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@OneToMany(mappedBy="assignment")
	public List<Quest> getQuests() {
		return quests;
	}
	public void setQuests(List<Quest> quests) {
		this.quests = quests;
	}
	
}
