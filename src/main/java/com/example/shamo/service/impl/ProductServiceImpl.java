package com.example.shamo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shamo.dao.ProductCategoryDao;
import com.example.shamo.dao.ProductDao;
import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.InsertResData;
import com.example.shamo.dto.product.FindAllProductRes;
import com.example.shamo.dto.product.FindByIdProductRes;
import com.example.shamo.dto.product.InsertProductReq;
import com.example.shamo.dto.product.ProductData;
import com.example.shamo.model.ProductCategories;
import com.example.shamo.model.Products;
import com.example.shamo.service.ProductService;

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductCategoryDao categoryDao;

	@Override
	public FindAllProductRes findAllProduct() throws Exception {
		List<ProductData> productData = new ArrayList<>();

		List<Products> products = productDao.findAllProducts();
		for (int i = 0; i < products.size(); i++) {
			ProductData product = new ProductData();
			product.setId(products.get(i).getId());
			product.setProductName(products.get(i).getProductName());
			product.setCategoryId(products.get(i).getCategory().getId());
			product.setPrice(products.get(i).getPrice());
			product.setDescription(products.get(i).getDescription());
			product.setTags(products.get(i).getTags());

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
