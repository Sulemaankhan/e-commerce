package com.test.shopping.shoppingapp.customexception;

public class InsufficientBalance extends RuntimeException {
	public InsufficientBalance(String message) {
		super(message);
	}

public InsufficientBalance(String message,Throwable errors) {
	super(message,errors);
}
}
