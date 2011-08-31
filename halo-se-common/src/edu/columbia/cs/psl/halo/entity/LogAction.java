package edu.columbia.cs.psl.halo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogAction {
	private int id;
	private String key;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="log_key")
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
