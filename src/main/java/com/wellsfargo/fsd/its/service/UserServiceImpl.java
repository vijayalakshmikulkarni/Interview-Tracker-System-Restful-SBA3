package com.wellsfargo.fsd.its.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.its.dao.InterviewDao;
import com.wellsfargo.fsd.its.dao.UserDao;
import com.wellsfargo.fsd.its.entity.InterviewEntity;
import com.wellsfargo.fsd.its.entity.UserEntity;
import com.wellsfargo.fsd.its.exception.ItsException;
import com.wellsfargo.fsd.its.model.InterviewModel;
import com.wellsfargo.fsd.its.model.UserModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userRepo;
	
	@Autowired
	private InterviewDao interviewRepo;
	
	
	private UserEntity toEntity(UserModel model) {
		return new UserEntity(model.getUserId(),model.getFirstName(),
				model.getLastName(),model.getEmail(),model.getMobile());
				
	}
	
	private UserModel toModel(UserEntity entity) {
		return new UserModel(entity.getUserId(),entity.getFirstName(),
				entity.getLastName(),entity.getEmail(),entity.getMobile());
	}
	
	@Override
	@Transactional
	public UserModel add(UserModel user) throws ItsException {
		if(user!=null) {
			if(userRepo.existsById(user.getUserId())) {
				throw new ItsException("user Id already used!");
			}
			
			user = toModel(userRepo.save(toEntity(user)));
		}
		return user;
	}
	

	@Override
	@Transactional
	public void addAttendee(int userId, int interviewId) throws ItsException {
				
			if(!userRepo.existsById(userId)) {
				throw new ItsException("user Id does not exist, please enter valid userId!");
			}
			
			if(!interviewRepo.existsById(interviewId)) {
				throw new ItsException("interview Id does not exist, please enter valid interviewId!");
			}
		
		UserEntity user = userRepo.getOne(userId);
		InterviewEntity interview =interviewRepo.getOne(interviewId);
		
		for(InterviewEntity ie :user.getInterviewEntity())
		if(ie.getInterviewId()== interviewId) {
			throw new ItsException("User has been already added as an attendee to the given interview!");
		
		}
		user.getInterviewEntity().add(interview);
			
		userRepo.save(user);					
		
	}
	
	
	@Override
	public List<UserModel> showAttendeesOfInerview(int interviewId) throws ItsException {

		if(!interviewRepo.existsById(interviewId)) {
			throw new ItsException("interview Id does not exist, please enter valid interviewId!");
		}
		
		InterviewEntity interview =interviewRepo.getOne(interviewId);
				
		Set<UserEntity> users= interview.getUserEntity();
		
		List<UserModel> models = null;
		if(users!=null && !users.isEmpty()) {
			models = users.stream().map(e -> toModel(e)).collect(Collectors.toList());
		}
		else {
			throw new ItsException("There are no attendees for the given inetrviewId!");
		}
		return models;
	}
	

	@Override
	@Transactional
	public boolean deleteUser(int userId) throws ItsException {
		if(!userRepo.existsById(userId)) {
			throw new ItsException("User Not Found");
		}
		
		userRepo.deleteById(userId);
		return true;
	}

	

	@Override
	public List<UserModel> getAllUsers() {
		List<UserEntity> entities = userRepo.findAll();
		List<UserModel> models = null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> toModel(e)).collect(Collectors.toList());
			}
		return models;
	}
}
