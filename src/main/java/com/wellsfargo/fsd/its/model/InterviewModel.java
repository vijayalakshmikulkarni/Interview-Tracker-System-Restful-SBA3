package com.wellsfargo.fsd.its.model;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.wellsfargo.fsd.its.entity.UserEntity;


public class InterviewModel {
	

	@NotNull(message="interviewerId should not be omitted")
	private Integer interviewId;
	
	@NotNull(message="interviewerName should not be omitted")
	@NotBlank(message="interviewerName should not be blank")
	@Size(min = 5, max=30, message = "interviewerName must be of 5 to 30 chars in length")
	private String interviewerName;
	
	@NotNull(message="interviewName should not be omitted")
	@NotBlank(message="interviewName should not be blank")
	@Size(min = 3, max=30, message = "interviewName must be of 3 to 30 chars in length")
	private String interviewName;
	
	@NotNull(message="userSkills should not be omitted")
	@NotBlank(message="userSkills should not be blank")
	@Size(min = 5, max=30, message = "userSkills must be of 5 to 30 chars in length")
	private String userSkills;
	
	private String time;
		
	private LocalDate date;
	
	@NotNull(message="interviewStatus should not be omitted")
	@NotBlank(message="interviewStatus should not be blank")
	@Size(min = 5, max=100, message = "interviewStatus must be of 5 to 100 chars in length")
	private String interviewStatus;
	
	@NotNull(message="remarks should not be omitted")
	@NotBlank(message="remarks should not be blank")
	@Size(min = 5, max=100, message = "remarks must be of 5 to 100 chars in length")
	private String remarks;
		
	
	public InterviewModel() {
		//No implementation
	}
	
	public InterviewModel(Integer interviewId, String interviewerName, String interviewName, String userSkills,
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
