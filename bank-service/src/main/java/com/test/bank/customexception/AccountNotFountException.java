package com.test.bank.customexception;

public class AccountNotFountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotFountException(String messString) {
		super(messString);
	}

	public AccountNotFountException(String messString, Throwable errors) {
		super(messString, errors);
	}

}
