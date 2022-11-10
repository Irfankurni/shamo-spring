package com.example.shamo.service;

import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.product.FindAllProductRes;
import com.example.shamo.dto.product.FindByIdProductRes;
import com.example.shamo.dto.product.InsertProductReq;
import com.example.shamo.dto.product.UpdateProductReq;

public interface ProductService {

	FindAllProductRes findAllProduct() throws Exception;

	FindByIdProductRes findByIdProduct(Long id) throws Exception;

	InsertRes insertProduct(InsertProductReq products) throws Exception;

	UpdateRes updateProduct(UpdateProductReq product) throws Exception;

	DeleteRes deleteProduct(Long id) throws Exception;

}
