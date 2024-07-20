package com.test.shopping.shoppingapp.service.impl;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.test.shopping.shoppingapp.customexception.OrderNotAvailable;
import com.test.shopping.shoppingapp.dto.OrderHistoryRequestDTO;
import com.test.shopping.shoppingapp.dto.OrderHistoryResponseDTO;
import com.test.shopping.shoppingapp.dto.OrderHistoryUserRequest;
import com.test.shopping.shoppingapp.entity.Orders;
import com.test.shopping.shoppingapp.repo.OrdersRepository;

@Service
public class OrderHistoryServiceImpl implements com.test.shopping.shoppingapp.service.OrderHistoryService {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<OrderHistoryResponseDTO> orderHistory(long userId) {

		OrderHistoryRequestDTO historyRequest = new OrderHistoryRequestDTO();
		OrderHistoryUserRequest historyUserRequest = new OrderHistoryUserRequest();
		// setting userId into OrderHistoryUserRequest obj
		historyUserRequest.setUserId(userId);

		// setting OrderHistoryUserRequest into OrderHistoryRequestDTO obj
		historyRequest.setUser(historyUserRequest);

		List<Orders> orders = ordersRepository.findByUserId(historyRequest.getUser().getUserId());
		if (ObjectUtils.isEmpty(orders)) {
			// if userId not matched with Orders table
			throw new OrderNotAvailable("No order history is available      :" + userId);
		}
		// with sorting list obj
		List<OrderHistoryResponseDTO> histList = orders.stream()
				.map(orderList -> modelMapper.map(orderList, OrderHistoryResponseDTO.class))
				.collect(Collectors.toList());
		// after sorting orders based on the total price
		List<OrderHistoryResponseDTO> sortedList = histList.stream()
				.sorted(Comparator.comparingDouble(OrderHistoryResponseDTO::getTotalPrice))
				.collect(Collectors.toList());
		return sortedList;
	}

}
