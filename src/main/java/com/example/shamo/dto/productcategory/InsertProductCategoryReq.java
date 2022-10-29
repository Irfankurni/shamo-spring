package com.example.shamo.dto.productcategory;

import javax.validation.constraints.NotNull;

public class InsertProductCategoryReq {

	@NotNull(message = "Category can't be blank")
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
