package com.test.shopping.shoppingapp.customexception;

public class OrderNotAvailable extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderNotAvailable(String message) {
		// TODO Auto-generated constructor stub

		super(message);
	}

	public OrderNotAvailable(String message, Throwable erros) {
		// TODO Auto-generated constructor stub

		super(message, erros);
	}

}
