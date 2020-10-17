package com.wellsfargo.fsd.its.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.its.entity.InterviewEntity;



@Repository
public interface InterviewDao extends JpaRepository<InterviewEntity, Integer>{

	List<InterviewEntity> findByInterviewName (String interviewName);
	
	List<InterviewEntity> findByInterviewerName (String interviewerName);
	
	@Query("SELECT COUNT(i.interviewId) FROM InterviewEntity i")
	Integer getTotalCountOfInterviews();
	
	
}
