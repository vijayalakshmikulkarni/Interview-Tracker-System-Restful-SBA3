package com.wellsfargo.fsd.its.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.its.exception.ItsException;
import com.wellsfargo.fsd.its.model.UserModel;
import com.wellsfargo.fsd.its.service.UserService;


@RestController
@Validated
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//Allows to Add user
	@PostMapping
	public ResponseEntity<UserModel> addUser(@RequestBody @Valid UserModel user,BindingResult result) throws ItsException{
		if(result.hasErrors()) {
			throw new ItsException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(userService.add(user),HttpStatus.OK);
	}
	
	//Allows to delete an existing user
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId")int userId) throws ItsException{
		userService.deleteUser(userId)	;	
		return new ResponseEntity<String>("User deleted successfully",HttpStatus.OK);
	}
	
	//Allows to display all the users
	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUsers(){
		return new ResponseEntity<List<UserModel>>(userService.getAllUsers(),HttpStatus.OK);
	}	
			
	//Allow to add attendee to an interview
	@GetMapping("/addAttendee/{userId}/{interviewId}")
	public ResponseEntity<String> addAttendee(@PathVariable("userId")  int userId, @PathVariable("interviewId")  int interviewId) throws ItsException{
		userService.addAttendee(userId, interviewId);			
		return new ResponseEntity<String>("Attendee added to interview successfully", HttpStatus.OK);
	}
	
	//Allow to show all attendee of an interview
	@GetMapping("/ShowAttendeesofInterview/{interviewId}")
	public ResponseEntity<List<UserModel>> showAttendeesOfInterview(@PathVariable("interviewId")int interviewId) throws ItsException {
		return new ResponseEntity<List<UserModel>>(userService.showAttendeesOfInerview(interviewId),HttpStatus.OK);
	}
	
	
}
