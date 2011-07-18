package edu.columbia.cs.psl.halo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Title {
	private int id;
	private Achievement comesFrom;
	private String title;
	public enum TitlePosition {infix, prefix, sufix}
	private TitlePosition position;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Achievement getComesFrom() {
		return comesFrom;
	}
	public void setComesFrom(Achievement comesFrom) {
		this.comesFrom = comesFrom;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Enumerated(EnumType.STRING)
	public TitlePosition getPosition() {
		return position;
	}
	public void setPosition(TitlePosition position) {
		this.position = position;
	}
	
}
