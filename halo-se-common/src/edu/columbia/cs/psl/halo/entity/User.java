package edu.columbia.cs.psl.halo.entity;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name="h_user")
@XmlRootElement
public class User extends LazyCycleBreaker implements Serializable {


	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private UserStatus status;
	private List<Enrollment> enrollments;
	private List<AchievementRecord> achievements;
	private int xp;
	private int achievementPoints;
	private Level level;
	private List<Title> titles;
	private Title activeTitle;
	private byte[] thumbnail;
	private List<QuestProgress> progress;
	private String facebookSessionKey;
	
	public User() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
		
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	@XmlTransient
	@OneToMany(mappedBy="user")
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlTransient
	@OneToMany(mappedBy="user")
	public List<AchievementRecord> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<AchievementRecord> achievements) {
		this.achievements = achievements;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getAchievementPoints() {
		return achievementPoints;
	}

	public void setAchievementPoints(int achievementPoints) {
		this.achievementPoints = achievementPoints;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	
	@OneToMany
	@XmlTransient
	public List<Title> getTitles() {
		return titles;
	}
	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}
	
	public void setActiveTitle(Title activeTitle) {
		this.activeTitle = activeTitle;
	}
	public Title getActiveTitle() {
		return activeTitle;
	}
	
	@XmlTransient
	@Lob @Basic(fetch=FetchType.LAZY)
	public byte[] getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}
	@XmlTransient
	@OneToMany(mappedBy="user")
	public List<QuestProgress> getProgress() {
		return progress;
	}
	public void setProgress(List<QuestProgress> progress) {
		this.progress = progress;
	}
	@XmlTransient
	public String getFacebookSessionKey() {
		return facebookSessionKey;
	}
	public void setFacebookSessionKey(String facebookSessionKey) {
		this.facebookSessionKey = facebookSessionKey;
	}
}
