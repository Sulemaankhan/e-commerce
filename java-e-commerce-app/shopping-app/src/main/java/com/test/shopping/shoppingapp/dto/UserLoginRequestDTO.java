package com.test.shopping.shoppingapp.dto;

import javax.validation.constraints.NotEmpty;

public class UserLoginRequestDTO {

	@NotEmpty(message = "username is required")
	private String userName;
	@NotEmpty(message = "password is required")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
