package com.test.shopping.shoppingapp.dto;

public class ProductResDTO {

	private Long id ;
	private String productName;
	private String categoryName;
	private String description;
	private double price;
	
	public ProductResDTO() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ProductResDTO(Long id, String productName, String categoryName, String description, double price) {
		super();
		this.id = id;
		this.productName = productName;
		this.categoryName = categoryName;
		this.description = description;
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductResDTO [id=" + id + ", productName=" + productName + ", categoryName=" + categoryName
				+ ", description=" + description + ", price=" + price + "]";
	}
	
	
	
	
}
