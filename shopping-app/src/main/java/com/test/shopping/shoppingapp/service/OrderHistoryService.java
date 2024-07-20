package com.test.shopping.shoppingapp.service;

import java.util.List;

import com.test.shopping.shoppingapp.dto.OrderHistoryResponseDTO;
import com.test.shopping.shoppingapp.entity.Orders;

public interface OrderHistoryService {
	
	List<OrderHistoryResponseDTO> orderHistory(long userId);
}
