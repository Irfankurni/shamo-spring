package com.example.shamo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_galleries")
public class ProductGalleries extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "file_id")
	private Files file;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products product;

	public Files getFile() {
		return file;
	}

	public void setFile(Files file) {
		this.file = file;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

}
