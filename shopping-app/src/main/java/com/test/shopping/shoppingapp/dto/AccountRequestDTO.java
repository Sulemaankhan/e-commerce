package com.test.shopping.shoppingapp.dto;

import javax.validation.constraints.NotEmpty;

public class AccountRequestDTO {

	@NotEmpty(message = "felied is required")
	private long accountNumber;
//	@NotEmpty(message = "felied is required")
//	private String bankName;
	
	private double cost;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

}
