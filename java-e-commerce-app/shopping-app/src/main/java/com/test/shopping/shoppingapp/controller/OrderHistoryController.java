package com.test.shopping.shoppingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.shopping.shoppingapp.dto.OrderHistoryRequestDTO;
import com.test.shopping.shoppingapp.dto.OrderHistoryResponseDTO;
import com.test.shopping.shoppingapp.service.OrderHistoryService;

@RestController
@RequestMapping("orderHistory")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;

	@GetMapping("users/{userId}/orders")
	public ResponseEntity<List<OrderHistoryResponseDTO>> orderHistory(@PathVariable long userId) {
		List<OrderHistoryResponseDTO> ordersResponseDTO = orderHistoryService.orderHistory(userId);
		return new ResponseEntity<List<OrderHistoryResponseDTO>>(ordersResponseDTO, HttpStatus.OK);
	}
}
