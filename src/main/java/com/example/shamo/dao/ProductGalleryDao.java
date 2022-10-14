package com.example.shamo.dao;

import java.util.List;

import com.example.shamo.model.ProductGalleries;

public interface ProductGalleryDao {

	List<ProductGalleries> findAllGalleries() throws Exception;

	ProductGalleries findByIdGallery(Long id) throws Exception;
	
	ProductGalleries findByProductId(Long productId) throws Exception;

	ProductGalleries insertGallery(ProductGalleries productGalleries) throws Exception;

	ProductGalleries updateGallery(ProductGalleries productGalleries) throws Exception;

	Boolean deleteGallery(Long id) throws Exception;

}
