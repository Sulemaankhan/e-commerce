package com.test.shopping.shoppingapp.dto;

import javax.validation.constraints.NotEmpty;

public class UserLoginResponseDTO {

	@NotEmpty(message = "username is required")
	private String userName;
	@NotEmpty(message = "password is required")
	private String message = "user successfull login";

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
