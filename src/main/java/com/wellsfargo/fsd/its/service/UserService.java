package com.wellsfargo.fsd.its.service;

import java.util.List;

import com.wellsfargo.fsd.its.exception.ItsException;
import com.wellsfargo.fsd.its.model.UserModel;

public interface UserService {

	UserModel add(UserModel user) throws ItsException;
	
	boolean deleteUser(int userId) throws ItsException;
	
	List<UserModel> getAllUsers();
	
	void addAttendee(int userId, int interviewId) throws ItsException;
	
	List<UserModel> showAttendeesOfInerview(int interviewId) throws ItsException;
}
