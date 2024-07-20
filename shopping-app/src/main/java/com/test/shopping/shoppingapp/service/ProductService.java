package com.test.shopping.shoppingapp.service;

import java.util.List;


import com.test.shopping.shoppingapp.dto.OrderHistoryResponseDTO;
import com.test.shopping.shoppingapp.dto.ProductResponseDTO;

public interface ProductService {

//	ProductCategoryResponseDTO search(String productCategory, String productName);

	List<ProductResponseDTO> searchProduct(String productName, String productCategory);

}
