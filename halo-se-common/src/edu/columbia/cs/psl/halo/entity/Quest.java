package edu.columbia.cs.psl.halo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Quest extends LazyCycleBreaker implements Serializable {
	
	private static final long serialVersionUID = -7137631033959876404L;
	private int id;
	private String name;
	private String description;
	private Quest parent;
	private List<Quest> children;
	private List<Task> tasks;
	private Assignment assignment;
	
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
	
	@XmlTransient
	public Quest getParent() {
		return parent;
	}
	public void setParent(Quest parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy="parent")
	public List<Quest> getChildren() {
		return children;
	}
	public void setChildren(List<Quest> children) {
		this.children = children;
	}
	
	@OneToMany(mappedBy="quest")
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	@XmlTransient
	public Assignment getAssignment() {
		return assignment;
	}
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}
	
	private List<CausualRelation> causedBy;
	private List<CausualRelation> resultsIn;
	@XmlTransient
	@OneToMany(mappedBy="questResult")
	public List<CausualRelation> getCausedBy() {
		return causedBy;
	}
	public void setCausedBy(List<CausualRelation> causedBy) {
		this.causedBy = causedBy;
	}
	@XmlTransient
	@OneToMany(mappedBy="questCause")
	public List<CausualRelation> getResultsIn() {
		return resultsIn;
	}
	public void setResultsIn(List<CausualRelation> resultsIn) {
		this.resultsIn = resultsIn;
	}
	
}
