package com.test.shopping.shoppingapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.shopping.shoppingapp.customexception.InsufficientBalance;
import com.test.shopping.shoppingapp.customexception.ProductNotFoundException;
import com.test.shopping.shoppingapp.customexception.TransactionFailed;
import com.test.shopping.shoppingapp.customexception.UserNotFoundException;
import com.test.shopping.shoppingapp.dto.AccountResponseDTO;
import com.test.shopping.shoppingapp.dto.BuyProductRequest;
import com.test.shopping.shoppingapp.dto.OrderDetailsRequestDTO;
import com.test.shopping.shoppingapp.entity.OrderDetails;
import com.test.shopping.shoppingapp.entity.Orders;
import com.test.shopping.shoppingapp.entity.Product;
import org.springframework.util.ObjectUtils;
import com.test.shopping.shoppingapp.entity.User;
import com.test.shopping.shoppingapp.feignclient.BankClient;
import com.test.shopping.shoppingapp.repo.OrdersRepository;
import com.test.shopping.shoppingapp.repo.ProductRepository;
import com.test.shopping.shoppingapp.repo.UserRepository;
import com.test.shopping.shoppingapp.service.BuyProductService;

@Service
public class PurchaseOrderImpl implements BuyProductService {
	@Autowired
	OrdersRepository orderRepo;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BankClient bankClient;

	public String orderProducts(@Valid BuyProductRequest orderRequestDTO)
			throws ProductNotFoundException, InsufficientBalance, UserNotFoundException {
		double totalamount = 0;
		Orders order = new Orders();
		List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		List<OrderDetailsRequestDTO> orderProductRequestDTOs = orderRequestDTO.getOrderDetailsRequest();
		User u = userRepository.findById(orderRequestDTO.getUserId()).orElseThrow(
				() -> new UserNotFoundException("User not found for this id :: " + +orderRequestDTO.getUserId()));
		User user = new User();
		u.setId(orderRequestDTO.getUserId());
		order.setUser(u);
		order.setDate(new Date());
		totalamount = saveOrderDetails(orderRequestDTO, totalamount, orderDetailsList, orderProductRequestDTOs, order);
		AccountResponseDTO accountResponseDTO = bankClient.payment(orderRequestDTO.getAccountNumber());
		if (orderRequestDTO.getAccountNumber() == accountResponseDTO.getAccountNumber()) {
			if (totalamount < accountResponseDTO.getBalance()) {
				order.setTotalPrice(totalamount);
				order.setOrderDetails(orderDetailsList);
				Orders savedOrder = orderRepo.save(order);
				return "Order Success";
			} else {
				throw new InsufficientBalance("InsufficientBalance......." + accountResponseDTO.getBalance());
			}

		} else {
			throw new TransactionFailed("Transection Failed......");
		}

	}

	private double saveOrderDetails(BuyProductRequest orderRequestDTO, double totalamount,
			List<OrderDetails> orderDetailsList, List<OrderDetailsRequestDTO> orderProductRequestDTOs, Orders order)
			throws ProductNotFoundException {

		for (OrderDetailsRequestDTO orderProductRequestDTO : orderProductRequestDTOs) {
			OrderDetails orderDetails = new OrderDetails();
			Optional<Product> products = productRepo.findById(orderProductRequestDTO.getProductId());
			if (ObjectUtils.isEmpty(products)) {
				throw new ProductNotFoundException("product not found");
			}
			if (products.isPresent()) {
				Product product = products.get();
				User u = new User();
				double price = product.getPrice() * (orderProductRequestDTO.getQuantity());
				totalamount = totalamount + price;
				orderDetails.setPrice(price);
				orderDetails.setProduct(product);
				orderDetails.setQuantity(orderProductRequestDTO.getQuantity());
				u.setId(orderRequestDTO.getUserId());
				orderDetails.setOrders(order);
				orderDetailsList.add(orderDetails);
			}
		}
		return totalamount;
	}

	private String getResponseDTO(Orders savedOrder) {

		Orders savedOrders = new Orders();
		Optional<Orders> orders = orderRepo.findById(savedOrder.getId());
		if (orders.isPresent()) {
			savedOrders = orders.get();
		}

		List<OrderDetails> orderDetails = savedOrders.getOrderDetails();
		List<OrderDetailsRequestDTO> orderDetailsDTOs = new ArrayList<>();
		orderDetails.forEach(orderDetail -> {
			OrderDetailsRequestDTO orderDetailsDTO = new OrderDetailsRequestDTO();
			BeanUtils.copyProperties(orderDetail, orderDetailsDTO);
			orderDetailsDTOs.add(orderDetailsDTO);
		});
		return "sucess";
	}
}
