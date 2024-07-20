package com.test.shopping.shoppingapp.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BuyProductRequest {

//	@NotNull(message = "date should not be null")
//	private String date;
	@NotNull(message = "userId should not be null")
	private long accountNumber;

	@NotNull(message = "userId should not be null")
	private long userId;

	private List<OrderDetailsRequestDTO> orderDetailsRequest;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<OrderDetailsRequestDTO> getOrderDetailsRequest() {
		return orderDetailsRequest;
	}

	public void setOrderDetailsRequest(List<OrderDetailsRequestDTO> orderDetailsRequest) {
		this.orderDetailsRequest = orderDetailsRequest;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
}
