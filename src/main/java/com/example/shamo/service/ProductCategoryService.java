package com.example.shamo.service;

import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.productcategory.FindAllProductCategoryRes;
import com.example.shamo.dto.productcategory.FindByIdProductCategoryRes;
import com.example.shamo.dto.productcategory.InsertProductCategoryReq;
import com.example.shamo.dto.productcategory.UpdateProductCategoryReq;

public interface ProductCategoryService {

	FindAllProductCategoryRes findAllProductCategory() throws Exception;

	FindByIdProductCategoryRes findByIdProductCategory(Long id) throws Exception;

	InsertRes insert(InsertProductCategoryReq category) throws Exception;

	UpdateRes update(UpdateProductCategoryReq category) throws Exception;

	DeleteRes delete(Long id) throws Exception;

}
