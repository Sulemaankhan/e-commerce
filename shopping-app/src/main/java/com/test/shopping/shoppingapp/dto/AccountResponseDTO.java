package com.test.shopping.shoppingapp.dto;

import jakarta.validation.constraints.NotEmpty;

public class AccountResponseDTO {

	private long accountNumber;
	private long balance;

	public AccountResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public AccountResponseDTO(long accountNumber, long balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

}
