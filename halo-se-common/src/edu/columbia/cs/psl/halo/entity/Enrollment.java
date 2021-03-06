package edu.columbia.cs.psl.halo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Enrollment extends LazyCycleBreaker implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9089080529784726383L;
	private int id;
	private User user;
	private Course course;
	private EnrollmentType type;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
//	@XmlIDREF
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Enumerated(EnumType.STRING)
	public EnrollmentType getType() {
		return type;
	}
	public void setType(EnrollmentType type) {
		this.type = type;
	}

}
