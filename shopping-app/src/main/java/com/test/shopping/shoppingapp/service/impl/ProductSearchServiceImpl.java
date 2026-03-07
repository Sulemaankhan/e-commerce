package com.test.shopping.shoppingapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.autoconfigure.DevToolsProperties.Restart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.test.shopping.shoppingapp.customexception.OrderNotAvailable;
import com.test.shopping.shoppingapp.customexception.ProductNotFoundException;
import com.test.shopping.shoppingapp.dto.OrderHistoryResponseDTO;
import com.test.shopping.shoppingapp.dto.PagedResponseDTO;
import com.test.shopping.shoppingapp.dto.ProductRequestDTO;
import com.test.shopping.shoppingapp.dto.ProductResDTO;
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
		pd.setCategoryName(productRequest.getCategoryName());
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

	@Override
	public PagedResponseDTO<ProductResDTO> getProductsPage(int page, int size, String search, String category,
			String sortBy, String sortDir) {

		if (page < 0) {
			page = 0;
		}
		if (size <= 0) {
			size = 20;
		}
		if (sortBy == null || sortBy.isBlank()) {
			sortBy = "id";
		}
		if (sortDir == null || sortDir.isBlank()) {
			sortDir = "asc";
		}

		Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		boolean hasSearch = search != null && !search.isBlank();
		boolean hasCategory = category != null && !category.isBlank();

		Page<Product> pageResult;

		if (hasCategory && hasSearch) {
			pageResult = productRepository.findByCategoryNameIgnoreCaseAndProductNameContainingIgnoreCase(category, search,
					pageable);
		} else if (hasCategory) {
			pageResult = productRepository.findByCategoryNameIgnoreCase(category, pageable);
		} else if (hasSearch) {
			pageResult = productRepository.findByProductNameContainingIgnoreCaseOrCategoryNameContainingIgnoreCase(search, search,
					pageable);
		} else {
			pageResult = productRepository.findAll(pageable);
		}

		List<ProductResDTO> content = pageResult.getContent().stream()
				.map(p -> new ProductResDTO(p.getId(), p.getProductName(), p.getCategoryName(), p.getDescription(),
						p.getPrice()))
				.toList();

		return new PagedResponseDTO<>(content, pageResult.getNumber(), pageResult.getSize(),
				pageResult.getTotalElements(), pageResult.getTotalPages(), pageResult.isFirst(), pageResult.isLast(),
				sortBy, sortDir);
	}
}
