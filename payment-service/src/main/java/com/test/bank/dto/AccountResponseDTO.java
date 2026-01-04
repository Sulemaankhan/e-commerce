package com.test.bank.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long accountNumber;
	private long balance;
	private String type;

//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public AccountResponseDTO() {
//		// TODO Auto-generated constructor stub
//	}
//
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
//	public AccountResponseDTO(long accountNumber, long balance) {
//		super();
//		this.accountNumber = accountNumber;
//		this.balance = balance;
//	}

}
