package com.test.bank.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountRequestDTO implements Serializable{

	@NotNull(message = "felied is required")
	private long accountNumber;
//	@NotEmpty(message = "felied is required")
//	private String bankName;
	public AccountRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
//	public String getBankName() {
//		return bankName;
//	}
//	public void setBankName(String bankName) {
//		this.bankName = bankName;
//	}
	
}
