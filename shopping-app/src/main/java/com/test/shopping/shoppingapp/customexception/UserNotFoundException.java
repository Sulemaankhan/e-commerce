package com.test.shopping.shoppingapp.customexception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserNotFoundException(String message) {
		super(message);
	}
	public UserNotFoundException(String message,Throwable errors) {
		super(message,errors);
	}

}
