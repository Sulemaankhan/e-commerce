package com.test.shopping.shoppingapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.test.shopping.shoppingapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	//List<Product> findByProductNameContainsAndCategoryName(String productName, String category);
	//List<Product> findByProductNameLikeCategoryName(@Param("productName")String productName,@Param("category") String category);
	List<Product> findByProductNameContainsAndCategoryName(String productName, String category);
	//List<Product> findByCategoryName(String productName, String categoryName);
}
