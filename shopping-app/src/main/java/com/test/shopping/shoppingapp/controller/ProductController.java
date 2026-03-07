package com.test.shopping.shoppingapp.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.shopping.shoppingapp.dto.ProductRequestDTO;
import com.test.shopping.shoppingapp.dto.ProductResponseDTO;
import com.test.shopping.shoppingapp.service.ProductService;

@RestController
@Validated
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
	@PostMapping(value ="/products")
	public ResponseEntity<String> saveProduct(@RequestBody ProductRequestDTO productRequest) {
		System.out.println("Entered::/products::POST");
		String productResponseDTO = productSearchService.saveProduct(productRequest);
		System.out.println("Api::/products::POST");
		return new ResponseEntity<String>(productResponseDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/products/page")
	public com.test.shopping.shoppingapp.dto.PagedResponseDTO<com.test.shopping.shoppingapp.dto.ProductResDTO> getProductsPaged(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "20") int size,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "asc") String sortDir) {
			System.out.println("========pagination grid called====");
		return productSearchService.getProductsPage(page, size, search, category, sortBy, sortDir);
	}
}
