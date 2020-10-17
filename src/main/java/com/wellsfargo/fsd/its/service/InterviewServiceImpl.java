package com.wellsfargo.fsd.its.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.its.dao.InterviewDao;
import com.wellsfargo.fsd.its.entity.InterviewEntity;

import com.wellsfargo.fsd.its.exception.ItsException;
import com.wellsfargo.fsd.its.model.InterviewModel;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewDao interviewRepo;

	private InterviewEntity toEntity(InterviewModel model) {
		return new InterviewEntity(model.getInterviewId(), model.getInterviewerName(), model.getInterviewName(),
				model.getUserSkills(), model.getTime(), model.getDate(), model.getInterviewStatus(),
				model.getRemarks());

	}

	private InterviewModel toModel(InterviewEntity entity) {
		return new InterviewModel(entity.getInterviewId(), entity.getInterviewerName(), entity.getInterviewName(),
				entity.getUserSkills(), entity.getTime(), entity.getDate(), entity.getInterviewStatus(),
				entity.getRemarks());
	}

	@Override
	@Transactional
	public InterviewModel add(InterviewModel interview) throws ItsException {
		if (interview != null) {
			if (interviewRepo.existsById(interview.getInterviewId())) {
				throw new ItsException("interview Id already used!");
			}

			interview = toModel(interviewRepo.save(toEntity(interview)));
		}
		return interview;
	}

	@Override
	@Transactional
	public boolean deleteInterview(int interviewId) throws ItsException {
		if (!interviewRepo.existsById(interviewId)) {
			throw new ItsException("Interview Not Found");
		}

		 if(!(interviewRepo.getOne(interviewId).getUserEntity().size() == 0)) {
			 throw new ItsException("Failed to delete,  Please delete the users associated with this interview");
			}
		interviewRepo.deleteById(interviewId);
		
		if (interviewRepo.existsById(interviewId)) {
				throw new ItsException("Failed to delete the specified record");
		           
		        } 
		return true; 	
		
	}

	@Override
	public Integer getInterviewTotalCount() {
		return interviewRepo.getTotalCountOfInterviews();

	}

	@Override
	@Transactional
	public InterviewModel updateInterviewStatus(int interviewId, String interviewStatus) throws ItsException {
		
		InterviewModel interview=null;
		if (!interviewRepo.existsById(interviewId)) {
			throw new ItsException("Interview Id Not Found");
		}
		InterviewEntity entity = interviewRepo.getOne(interviewId);
		entity.setInterviewStatus(interviewStatus);
		interview = toModel(interviewRepo.save(entity));

		return interview;
	}

	@Override
	public List<InterviewModel> getInterviewName(String interviewName) throws ItsException {
		List<InterviewEntity> entities = interviewRepo.findByInterviewName(interviewName);
		
		List<InterviewModel> models = null;
		if (entities != null && !entities.isEmpty()) {
			models = entities.stream().map(e -> toModel(e)).collect(Collectors.toList());

		}else
			throw new ItsException("Interview Name Not Found");
		return models;
	}

	@Override
	public List<InterviewModel> getInterviewerName(String interviewerName) throws ItsException {
		List<InterviewEntity> entities = interviewRepo.findByInterviewerName(interviewerName);
		List<InterviewModel> models = null;
		if (entities != null && !entities.isEmpty()) {
			models = entities.stream().map(e -> toModel(e)).collect(Collectors.toList());

		}else
			throw new ItsException("Interviewer Name Not Found");
		return models;
	}

	@Override
	public List<InterviewModel> getAllInterviews() {
		List<InterviewEntity> entities = interviewRepo.findAll();
		List<InterviewModel> models = null;
		if (entities != null && !entities.isEmpty()) {
			models = entities.stream().map(e -> toModel(e)).collect(Collectors.toList());

		}
		return models;
	}

}
