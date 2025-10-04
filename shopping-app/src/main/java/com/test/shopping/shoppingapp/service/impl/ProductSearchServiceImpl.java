package com.test.shopping.shoppingapp.service.impl;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.autoconfigure.DevToolsProperties.Restart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.test.shopping.shoppingapp.customexception.OrderNotAvailable;
import com.test.shopping.shoppingapp.customexception.ProductNotFoundException;
import com.test.shopping.shoppingapp.dto.OrderHistoryResponseDTO;
import com.test.shopping.shoppingapp.dto.ProductRequestDTO;
import com.test.shopping.shoppingapp.dto.ProductResponseDTO;
import com.test.shopping.shoppingapp.dto.SearchCategoryRequestDTO;
import com.test.shopping.shoppingapp.dto.SearchProductRequestDTO;
import com.test.shopping.shoppingapp.entity.Orders;
import com.test.shopping.shoppingapp.entity.Product;

import com.test.shopping.shoppingapp.repo.OrdersRepository;
import com.test.shopping.shoppingapp.repo.ProductRepository;
import com.test.shopping.shoppingapp.service.ProductService;

@Service
@Transactional
public class ProductSearchServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ProductResponseDTO> searchProduct(String productName, String category) {

		// using ModelMapper with stream api
		List<Product> list = productRepository.findByProductNameContainsAndCategoryName(productName, category);

		// Object utlis
		if (ObjectUtils.isEmpty(list)) {
			// if list obj is empty then return product not found
			throw new ProductNotFoundException("No such Category is available     :" + category);
		}
		return list.stream().map(productList -> modelMapper.map(productList, ProductResponseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductResponseDTO> getAll() {
		// using ModelMapper with stream api
		List<Product> list = productRepository.findAll();

		// Object utlis
		if (ObjectUtils.isEmpty(list)) {
			// if list obj is empty then return product not found
			throw new ProductNotFoundException("No such Category is available ");
		}
		System.out.println("===Return list of the product====");
		return list.stream().map(productList -> modelMapper.map(productList, ProductResponseDTO.class))
				.collect(Collectors.toList());

	}

	@Override
	public String saveProduct(ProductRequestDTO productRequest) {
		String result = "";
		Product pd = new Product();
		pd.setProductName(productRequest.getProductName());
		pd.setCategoryName(productRequest.getProductCategory());
		pd.setDescription(productRequest.getDescription());
		pd.setPrice(productRequest.getPrice());
		Product res = productRepository.save(pd);
		if (!ObjectUtils.isEmpty(res)) {
			result = "Successfully save";
		} else {
			result = "Insertion failed";
		}
		System.out.println("Product save api result :"+result);
		return result;
	}
}
