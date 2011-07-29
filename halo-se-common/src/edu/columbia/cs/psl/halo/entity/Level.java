package edu.columbia.cs.psl.halo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Level {
	private int id;
	private int level;
	private int xpRequired;
	private int xpMax;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getXpRequired() {
		return xpRequired;
	}
	public void setXpRequired(int xpRequired) {
		this.xpRequired = xpRequired;
	}
	public int getXpMax() {
		return xpMax;
	}
	public void setXpMax(int xpMax) {
		this.xpMax = xpMax;
	}
	
	
}
