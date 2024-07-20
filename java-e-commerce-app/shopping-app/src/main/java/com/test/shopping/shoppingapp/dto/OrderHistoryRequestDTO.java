package com.test.shopping.shoppingapp.dto;

import javax.persistence.JoinColumn;

import com.test.shopping.shoppingapp.entity.User;

public class OrderHistoryRequestDTO {

	private OrderHistoryUserRequest user;

	public OrderHistoryUserRequest getUser() {
		return user;
	}

	public void setUser(OrderHistoryUserRequest user) {
		this.user = user;
	}

}
