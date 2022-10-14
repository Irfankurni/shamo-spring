package com.example.shamo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Products extends BaseModel {

	@Column(name = "product_name", length = 100)
	private String productName;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategories category;

	private float price;
	private String description;
	private String tags;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductCategories getCategory() {
		return category;
	}

	public void setCategory(ProductCategories category) {
		this.category = category;
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

}
