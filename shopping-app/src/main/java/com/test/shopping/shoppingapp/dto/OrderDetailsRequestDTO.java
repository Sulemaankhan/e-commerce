package com.test.shopping.shoppingapp.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class OrderDetailsRequestDTO {

	@Min(value = 1, message = "add a min msg")
	private int quantity;

	@Min(value = 0L, message = "The value must be positive")
	private long productId;

	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
}
