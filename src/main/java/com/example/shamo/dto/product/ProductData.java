package com.example.shamo.dto.product;

import com.example.shamo.dto.productgallery.ProductGalleryData;

import java.util.List;

public class ProductData {

	private Long id;
	private String productName;
	private Long categoryId;
	private String categoryName;
	private float price;
	private String description;
	private String tags;
	private Boolean isActive;

	private List<ProductGalleryData> galleries;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<ProductGalleryData> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<ProductGalleryData> galleries) {
		this.galleries = galleries;
	}

}
