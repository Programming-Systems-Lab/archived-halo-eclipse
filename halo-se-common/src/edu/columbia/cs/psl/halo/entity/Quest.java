package edu.columbia.cs.psl.halo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Quest implements Serializable {
	
	private static final long serialVersionUID = -7137631033959876404L;
	private int id;
	private String name;
	private String description;
	private Quest parent;
	private List<Quest> children;
	private List<Task> tasks;
	
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
	
	
}
