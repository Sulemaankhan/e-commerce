package com.test.shopping.shoppingapp.service;

import javax.validation.Valid;

import com.test.shopping.shoppingapp.dto.BuyProductRequest;

import com.test.shopping.shoppingapp.dto.BuyProductResponse;
import com.test.shopping.shoppingapp.entity.Orders;

public interface BuyProductService {
	
//	String buyProduct(BuyProductRequest buyProductRequest);

	//String buyProduct(long accountnumber, BuyProductRequest buyProductRequest);
	public String  orderProducts(@Valid BuyProductRequest orderRequestDTO);
}
