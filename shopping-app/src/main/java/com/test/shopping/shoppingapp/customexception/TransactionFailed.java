package com.test.shopping.shoppingapp.customexception;

public class TransactionFailed extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TransactionFailed(String status) {
		super(status);
	}
}
