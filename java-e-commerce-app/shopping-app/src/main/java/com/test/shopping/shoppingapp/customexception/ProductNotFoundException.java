package com.test.shopping.shoppingapp.customexception;


public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ProductNotFoundException(String message) {
		super(message);
	}

}
