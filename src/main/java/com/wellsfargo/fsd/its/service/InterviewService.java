package com.wellsfargo.fsd.its.service;

import java.util.List;

import com.wellsfargo.fsd.its.exception.ItsException;
import com.wellsfargo.fsd.its.model.InterviewModel;


public interface InterviewService {

	InterviewModel add(InterviewModel interview) throws ItsException;
	
	boolean deleteInterview(int interviewId) throws ItsException;
	
	InterviewModel updateInterviewStatus(int interviewId, String interviewStatus) throws ItsException;
	
	List<InterviewModel> getInterviewName(String interviewName) throws ItsException;
	List<InterviewModel> getInterviewerName(String interviewerName) throws ItsException;
	
	List<InterviewModel> getAllInterviews();
	 Integer getInterviewTotalCount();
}
