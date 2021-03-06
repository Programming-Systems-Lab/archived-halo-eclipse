package edu.columbia.cs.psl.halo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Task extends LazyCycleBreaker implements Serializable {

	private static final long serialVersionUID = 8320114424613456623L;
	private int id;
	private String name;
	private String description;
	private TaskType type;
	private Task parent;
	private List<Task> children;
	private Quest quest;
	
	@XmlElement
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Enumerated(EnumType.STRING)
	public TaskType getType() {
		return type;
	}
	public void setType(TaskType type) {
		this.type = type;
	}
	public Task getParent() {
		return parent;
	}
	public void setParent(Task parent) {
		this.parent = parent;
	}
	@XmlTransient
	@OneToMany(mappedBy="parent")
	public List<Task> getChildren() {
		return children;
	}
	public void setChildren(List<Task> children) {
		this.children = children;
	}
	public Quest getQuest() {
		return quest;
	}
	public void setQuest(Quest quest) {
		this.quest = quest;
	}
	
	private List<CausualRelation> causedBy;
	private List<CausualRelation> resultsIn;
	@XmlTransient
	@OneToMany(mappedBy="taskResult")
	public List<CausualRelation> getCausedBy() {
		return causedBy;
	}
	public void setCausedBy(List<CausualRelation> causedBy) {
		this.causedBy = causedBy;
	}
	@XmlTransient
	@OneToMany(mappedBy="taskCause")
	public List<CausualRelation> getResultsIn() {
		return resultsIn;
	}
	public void setResultsIn(List<CausualRelation> resultsIn) {
		this.resultsIn = resultsIn;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Task)
		{
			return ((Task) obj).getId() == getId();
		}
		return false;
	}
	@Override
	public int hashCode() {
		return 29 * getId() + this.getClass().getName().hashCode();
	}
}
