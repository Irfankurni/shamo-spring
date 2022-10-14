package com.example.shamo.dao;

import java.util.List;

import com.example.shamo.model.Products;

public interface ProductDao {

	List<Products> findAllProducts() throws Exception;

	Products findByIdProduct(Long id) throws Exception;

	Products insertProduct(Products products) throws Exception;

	Products updateProduct(Products products) throws Exception;

	Boolean deleteProduct(Long id) throws Exception;

}
