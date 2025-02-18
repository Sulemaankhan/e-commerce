package com.test.shopping.shoppingapp.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.shopping.shoppingapp.dto.BuyProductRequest;

import com.test.shopping.shoppingapp.dto.BuyProductResponse;
import com.test.shopping.shoppingapp.dto.OrderDetailsRequestDTO;
//import com.test.shopping.shoppingapp.feignclient.BankClient;
import com.test.shopping.shoppingapp.service.BuyProductService;

@RestController
@RequestMapping("buyProducts")
@Validated
public class BuyProductController {

	@Autowired
	private BuyProductService buyProductService;

	@PostMapping("order")
	@Valid
	public ResponseEntity<String> buyProducts(@Valid @RequestBody BuyProductRequest buyProductRequest) {
		String orderStatus = buyProductService.orderProducts(buyProductRequest);
		if (orderStatus != null) {
			return new ResponseEntity<String>(orderStatus, HttpStatus.OK);
		}
		return new ResponseEntity<String>(orderStatus, HttpStatus.OK);
	}
}
