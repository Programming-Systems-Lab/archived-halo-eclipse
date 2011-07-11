package edu.columbia.cs.psl.halo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Achievement {
	private int id;
	private String name;
	private int points;
	
	
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
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	private List<CausualRelation> causedBy;
	private List<CausualRelation> resultsIn;
	@XmlTransient
	@OneToMany(mappedBy="achievementResult")
	public List<CausualRelation> getCausedBy() {
		return causedBy;
	}
	public void setCausedBy(List<CausualRelation> causedBy) {
		this.causedBy = causedBy;
	}
	@XmlTransient
	@OneToMany(mappedBy="achievementCause")
	public List<CausualRelation> getResultsIn() {
		return resultsIn;
	}
	public void setResultsIn(List<CausualRelation> resultsIn) {
		this.resultsIn = resultsIn;
	}
	
	
	
}
