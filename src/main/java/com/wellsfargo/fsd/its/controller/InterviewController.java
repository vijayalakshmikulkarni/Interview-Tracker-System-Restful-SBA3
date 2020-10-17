package com.wellsfargo.fsd.its.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.wellsfargo.fsd.its.exception.ItsException;
import com.wellsfargo.fsd.its.model.InterviewModel;

import com.wellsfargo.fsd.its.service.InterviewService;


@RestController
@Validated
@RequestMapping("/interviews")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;
	
	//Allow to display all the interviews
	@GetMapping
	public ResponseEntity<List<InterviewModel>> getAllInterviews(){
		return new ResponseEntity<List<InterviewModel>>(interviewService.getAllInterviews(),HttpStatus.OK);
	}	
	
	//Allow to add an interview
	@PostMapping
	public ResponseEntity<InterviewModel> addInterview(@RequestBody @Valid InterviewModel interview,BindingResult result) throws ItsException{
		if(result.hasErrors()) {
			throw new ItsException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(interviewService.add(interview),HttpStatus.OK);
	}	
	
	//Allows to search the interview on the basis of Interview Name
	@GetMapping("/interviewName/{interviewName}")
	public ResponseEntity<List<InterviewModel>>  getInterviewName(@PathVariable("interviewName") String interviewName) throws ItsException{
		ResponseEntity<List<InterviewModel>>  resp=null;
		
		List<InterviewModel> interview = interviewService.getInterviewName(interviewName);
		
		if(interview!=null) {
			resp =new ResponseEntity<List<InterviewModel>>(interview,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<List<InterviewModel>>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
	
	
	//Allows to search the interview on the basis of Interviewer Name
	@GetMapping("/interviewerName/{interviewerName}")
	public ResponseEntity<List<InterviewModel>>  getInterviewerName(@PathVariable("interviewerName") String interviewerName) throws ItsException{
		ResponseEntity<List<InterviewModel>>  resp=null;
		
		List<InterviewModel> interview = interviewService.getInterviewerName(interviewerName);
		
		if(interview!=null) {
			resp =new ResponseEntity<List<InterviewModel>>(interview,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<List<InterviewModel>>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}	
		
	//Allows to update interview status on go
	@PutMapping("/updateStatus/{interviewStatus}/{interviewId}")
	public ResponseEntity<InterviewModel> updateInterviewStatus(@PathVariable("interviewId")int interviewId,@PathVariable("interviewStatus") @Size(min = 5, max=100, message = "interviewStatus must be of 5 to 100 chars in length") String interviewStatus) throws ItsException{
		InterviewModel interview = interviewService.updateInterviewStatus(interviewId, interviewStatus);	
		return new ResponseEntity<InterviewModel>(interview, HttpStatus.OK);
	}
	
	//Allows searching total no of count of interview
	@GetMapping("/getInterviewTotalCount")
	public ResponseEntity<String> getInterviewTotalCount() {
		Integer intCount = interviewService.getInterviewTotalCount();	
		return new ResponseEntity<String>("Total Interview Count ="+intCount,HttpStatus.OK);
	}
	
	//Allows to delete an existing interview
	@DeleteMapping("/{interviewId}")
	public ResponseEntity<String> deleteInterview(@PathVariable("interviewId")int interviewId) throws ItsException{
		boolean status = interviewService.deleteInterview(interviewId)	;	
		ResponseEntity<String> resp=null;
		
		if(status) {
			resp =new ResponseEntity<String>("Interview record got deleted successfully",HttpStatus.OK);
		}else {
			resp =new ResponseEntity<String>("Interview record failed to delete",HttpStatus.BAD_REQUEST);
		}
		
		return resp;

	}
}
