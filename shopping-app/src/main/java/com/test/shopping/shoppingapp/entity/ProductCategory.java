package com.test.shopping.shoppingapp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "category")
public class ProductCategory {

	@Id
	private Long id;
	@NotNull(message = "CategoryName name should not be null")
	private String category;

	@NotNull(message = "description name should not be null")
	private String description;

//	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//	List<Product> productList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public List<Product> getProductList() {
//		return productList;
//	}
//
//	public void setProductList(List<Product> productList) {
//		this.productList = productList;
//	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

}
