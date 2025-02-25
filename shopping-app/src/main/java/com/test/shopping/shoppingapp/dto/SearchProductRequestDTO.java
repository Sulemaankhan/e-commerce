package com.test.shopping.shoppingapp.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import com.test.shopping.shoppingapp.entity.ProductCategory;

public class SearchProductRequestDTO {

	private String productName;
	private SearchCategoryRequestDTO productCategory;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public SearchCategoryRequestDTO getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(SearchCategoryRequestDTO productCategory) {
		this.productCategory = productCategory;
	}
	

}
