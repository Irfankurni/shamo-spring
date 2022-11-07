package com.example.shamo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.shamo.dao.FileDao;
import com.example.shamo.dao.ProductGalleryDao;
import com.example.shamo.dto.product.*;
import com.example.shamo.dto.productgallery.ProductGalleryData;
import com.example.shamo.model.Files;
import com.example.shamo.model.ProductGalleries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shamo.dao.ProductCategoryDao;
import com.example.shamo.dao.ProductDao;
import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.InsertResData;
import com.example.shamo.model.ProductCategories;
import com.example.shamo.model.Products;
import com.example.shamo.service.ProductService;

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductCategoryDao categoryDao;

	@Autowired
	private ProductGalleryDao galleryDao;

	@Autowired
	private FileDao fileDao;

	@Override
	public FindAllProductRes findAllProduct() throws Exception {
		List<ProductListData> productData = new ArrayList<>();

		List<Products> products = productDao.findAllProducts();
		for (int i = 0; i < products.size(); i++) {
			ProductListData product = new ProductListData();
			product.setId(products.get(i).getId());
			product.setProductName(products.get(i).getProductName());
			product.setCategoryId(products.get(i).getCategory().getId());
			product.setPrice(products.get(i).getPrice());
			product.setDescription(products.get(i).getDescription());
			product.setTags(products.get(i).getTags());
			product.setIsActive(products.get(i).getIsActive());

			ProductGalleries gallery = galleryDao.findByProduct(products.get(i).getId());
			product.setFileId(gallery.getId());

			productData.add(product);

		}

		FindAllProductRes res = new FindAllProductRes();
		res.setData(productData);

		return res;
	}

	@Override
	public FindByIdProductRes findByIdProduct(Long id) throws Exception {
		Products products = productDao.findByIdProduct(id);

		ProductData product = new ProductData();
		product.setId(products.getId());
		product.setProductName(products.getProductName());
		product.setCategoryId(products.getCategory().getId());
		product.setPrice(products.getPrice());
		product.setDescription(products.getDescription());
		product.setTags(products.getDescription());

		List<ProductGalleries> galleries = galleryDao.findByProductId(products.getId());
		List<ProductGalleryData> galleriesData = new ArrayList<>();

		for (int i = 0; i < galleries.size() ; i++) {
			ProductGalleryData gallery = new ProductGalleryData();

			Files files = fileDao.findById(galleries.get(i).getFile().getId());
			gallery.setFileId(files.getId());

			galleriesData.add(gallery);
		}
		product.setGalleries(galleriesData);
		product.setIsActive(products.getIsActive());

		FindByIdProductRes res = new FindByIdProductRes();
		res.setData(product);

		return res;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertRes insertProduct(InsertProductReq products) throws Exception {
		Products product = new Products();
		product.setProductName(products.getProductName());

		ProductCategories categories = categoryDao.findByIdCategory(products.getCategoryId());
		product.setCategory(categories);
		product.setPrice(products.getPrice());
		product.setTags(products.getTags());
		product.setDescription(products.getDescription());
		product.setCreatedBy(getUserId());
		product.setIsActive(true);

		Products inserted = productDao.insertProduct(product);

		InsertResData resData = new InsertResData();
		resData.setId(inserted.getId());

		InsertRes res = new InsertRes();
		res.setData(resData);
		res.setMessage("Berhasil");
		return res;
	}

	@Override
	public DeleteRes deleteProduct(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
