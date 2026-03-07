package com.test.shopping.shoppingapp.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.test.shopping.shoppingapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	//List<Product> findByProductNameContainsAndCategoryName(String productName, String category);
	//List<Product> findByProductNameLikeCategoryName(@Param("productName")String productName,@Param("category") String category);
	List<Product> findByProductNameContainsAndCategoryName(String productName, String category);
	//List<Product> findByCategoryName(String productName, String categoryName);
	
	Page<Product> findByProductNameContainingIgnoreCaseOrCategoryNameContainingIgnoreCase(
            String productName,
            String categoryName,
            Pageable pageable);

    Page<Product> findByCategoryNameIgnoreCase(String categoryName, Pageable pageable);

    Page<Product> findByCategoryNameIgnoreCaseAndProductNameContainingIgnoreCase(
            String categoryName,
            String productName,
            Pageable pageable);

    Page<Product> findByIdGreaterThan(Long id, Pageable pageable);
}
