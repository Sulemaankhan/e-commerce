package com.test.bank.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AccountRequestDTO implements Serializable {

	@NotNull(message = "felied is required")
	private long accountNumber;

	@NotNull(message = "felied is required")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// @NotEmpty(message = "felied is required")
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
