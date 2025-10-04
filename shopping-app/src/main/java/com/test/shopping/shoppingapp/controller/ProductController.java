package com.test.shopping.shoppingapp.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.shopping.shoppingapp.dto.ProductRequestDTO;
import com.test.shopping.shoppingapp.dto.ProductResponseDTO;
import com.test.shopping.shoppingapp.service.ProductService;

@RestController
//@RequestMapping("products")
@Validated
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productSearchService;

	@GetMapping
	@Valid
	public ResponseEntity<List<ProductResponseDTO>> search(@RequestParam String productName,
			@RequestParam String categoryName) {
		List<ProductResponseDTO> productResponseDTO = productSearchService.searchProduct(productName, categoryName);
		return new ResponseEntity<List<ProductResponseDTO>>(productResponseDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/products")
	@Valid
	public ResponseEntity<List<ProductResponseDTO>> getAll() {
		List<ProductResponseDTO> productResponseDTO = productSearchService.getAll();
		return new ResponseEntity<List<ProductResponseDTO>>(productResponseDTO, HttpStatus.OK);
	}
	@PostMapping(value = "/products")
	@Valid
	public ResponseEntity<String> saveProduct(@RequestBody ProductRequestDTO productRequest) {
		String productResponseDTO = productSearchService.saveProduct(productRequest);
		System.out.println("Api::/products::POST");
		return new ResponseEntity<String>(productResponseDTO, HttpStatus.OK);
	}

//	@PostMapping("search")
//	public ResponseEntity<ProductCategoryResponseDTO> search(
//			@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
//		ProductCategoryResponseDTO productResponseDTO = productSearchService
//				.search(categoryRequestDTO.getCategoryName(), categoryRequestDTO.getProductName());
//
//		return new ResponseEntity<ProductCategoryResponseDTO>(productResponseDTO, HttpStatus.OK);
//
//	}

}
