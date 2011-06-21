package edu.columbia.cs.psl.halo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Class implements Serializable{

	
	private static final long serialVersionUID = -2366901985108807832L;
	private int id;
	private String name;
	private List<User> professors;
	private List<User> tas;
	private List<User> students;
	private List<User> admins;
	
	@Id
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
	@ManyToMany
	public List<User> getProfessors() {
		return professors;
	}
	public void setProfessors(List<User> professors) {
		this.professors = professors;
	}
	@ManyToMany
	public List<User> getTas() {
		return tas;
	}
	public void setTas(List<User> tas) {
		this.tas = tas;
	}
	@ManyToMany
	public List<User> getStudents() {
		return students;
	}
	public void setStudents(List<User> students) {
		this.students = students;
	}
	@ManyToMany
	public List<User> getAdmins() {
		return admins;
	}
	public void setAdmins(List<User> admins) {
		this.admins = admins;
	}

}
