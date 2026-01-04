package com.test.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
	private long accountNumber;
	private long balance;

//	public long getAccountNumber() {
//		return accountNumber;
//	}
//
//	public void setAccountNumber(long accountNumber) {
//		this.accountNumber = accountNumber;
//	}
//
//	public long getBalance() {
//		return balance;
//	}
//
//	public void setBalance(long balance) {
//		this.balance = balance;
//	}
//
//	public Account(long accountNumber, long balance) {
//		super();
//		this.accountNumber = accountNumber;
//		this.balance = balance;
//	}
}
