package com.example.shamo.dto.productcategory;

import javax.validation.constraints.NotBlank;

public class InsertProductCategoryReq {

	@NotBlank(message = "Category can't be blank")
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
