package com.test.bank.entity;

public class Account {
	private long accountNumber;
	private long balance;

	public Account() {
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

	public Account(long accountNumber, long balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
}
