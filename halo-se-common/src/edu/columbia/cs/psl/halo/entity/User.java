package edu.columbia.cs.psl.halo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="h_user")
public class User {

	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private UserStatus status;
	private List<Class> studentClasses;
	private List<Class> taClasses;
	private List<Class> profClasses;
	private List<Class> adminClasses;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Enumerated(EnumType.STRING)
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	@ManyToMany(mappedBy="students")
	public List<Class> getStudentClasses() {
		return studentClasses;
	}
	public void setStudentClasses(List<Class> studentClasses) {
		this.studentClasses = studentClasses;
	}
	@ManyToMany(mappedBy="tas")
	public List<Class> getTaClasses() {
		return taClasses;
	}
	public void setTaClasses(List<Class> taClasses) {
		this.taClasses = taClasses;
	}
	@ManyToMany(mappedBy="professors")
	public List<Class> getProfClasses() {
		return profClasses;
	}
	public void setProfClasses(List<Class> profClasses) {
		this.profClasses = profClasses;
	}
	@ManyToMany(mappedBy="admins")
	public List<Class> getAdminClasses() {
		return adminClasses;
	}
	public void setAdminClasses(List<Class> adminClasses) {
		this.adminClasses = adminClasses;
	}
	
}
