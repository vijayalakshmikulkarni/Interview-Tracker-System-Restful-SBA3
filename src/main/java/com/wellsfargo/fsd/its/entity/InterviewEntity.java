package com.wellsfargo.fsd.its.entity;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name="interview")
public class InterviewEntity {
	

	@Id
	@Column(name="interviewId", unique=true)
	private Integer interviewId;
	
	@Column(name="interviewerName")
	private String interviewerName;
	
	@Column(name="interviewName")
	private String interviewName;
	
	@Column(name="userSkills")
	private String userSkills;
	
	@Column(name="time")
	private String time;
	
	@Column(name="date")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate date;
	
	@Column(name="interviewStatus")
	private String interviewStatus;
	
	@Column(name="remarks")
	private String remarks;
	
	@ManyToMany(mappedBy = "interviewEntity", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )
	private Set<UserEntity> userEntity;
	
	public InterviewEntity() {
		//No implementation
	}
	
	public InterviewEntity(Integer interviewId, String interviewerName, String interviewName, String userSkills,
			String time, LocalDate date, String interviewStatus, String remarks) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.userSkills = userSkills;
		this.time = time;
		this.date = date;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "InterviewEntity [interviewId=" + interviewId + ", interviewerName=" + interviewerName
				+ ", interviewName=" + interviewName + ", userSkills=" + userSkills + ", time=" + time + ", date="
				+ date + ", interviewStatus=" + interviewStatus + ", remarks=" + remarks + "]";
	}

	
	public Set<UserEntity> getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(Set<UserEntity> userEntity) {
		this.userEntity = userEntity;
	}

	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public String getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(String userSkills) {
		this.userSkills = userSkills;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	}
