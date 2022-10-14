package com.example.shamo.dao;

import java.util.List;

import com.example.shamo.model.ProductCategories;

public interface ProductCategoryDao {

	List<ProductCategories> findAllCategory() throws Exception;

	ProductCategories findByIdCategory(Long id) throws Exception;

	ProductCategories insertCategory(ProductCategories categories) throws Exception;

	ProductCategories updateCategory(ProductCategories categories) throws Exception;

	Boolean deleteCategory(Long id) throws Exception;

}
