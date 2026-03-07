package com.test.shopping.shoppingapp.service;

import java.util.List;


import com.test.shopping.shoppingapp.dto.OrderHistoryResponseDTO;
import com.test.shopping.shoppingapp.dto.PagedResponseDTO;
import com.test.shopping.shoppingapp.dto.ProductRequestDTO;
import com.test.shopping.shoppingapp.dto.ProductResDTO;
import com.test.shopping.shoppingapp.dto.ProductResponseDTO;

public interface ProductService {

//	ProductCategoryResponseDTO search(String productCategory, String productName);

	List<ProductResponseDTO> searchProduct(String productName, String productCategory);

	List<ProductResponseDTO> getAll();

	String saveProduct(ProductRequestDTO productRequest);
	
	PagedResponseDTO<ProductResDTO> getProductsPage(int page,
             int size,
             String search,
             String category,
             String sortBy,
             String sortDir);

}
