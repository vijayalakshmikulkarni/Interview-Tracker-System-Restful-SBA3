package com.wellsfargo.fsd.its.model;




import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;





public class UserModel {

	
	@NotNull(message="userId should not be omitted")
	private Integer userId;
	
	@NotNull(message="firstName should not be omitted")
	@NotBlank(message="firstName should not be blank")
	@Size(min = 5, max=30, message = "FirstName must be of 5 to 30 chars in length")
	private String firstName;
	
	@NotNull(message="lastName should not be omitted")
	@NotBlank(message="lastName should not be blank")
	@Size(min = 3, max=25, message = "lastName must be of 3 to 25 chars in length")
	private String lastName;
	
	@NotNull(message="email should not be omitted")
	@Email(message =" Please enter valid email")
	private String email;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	@NotNull(message="mobile should not be omitted")
	@NotBlank(message="mobile should not be blank")
	@Size(min = 10, max=10, message = "mobile number should be 10 digits in length")
	private String mobile;
	
		
	public UserModel() {
		//No implementation
	}
	
	public UserModel(Integer userId, String firstName, String lastName, String email, String mobile) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}	
	

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobile=" + mobile + "]";
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
			
}
