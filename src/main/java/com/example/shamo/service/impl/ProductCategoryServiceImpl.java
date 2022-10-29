package com.example.shamo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shamo.dao.ProductCategoryDao;
import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.InsertResData;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.UpdateResData;
import com.example.shamo.dto.productcategory.FindAllProductCategoryRes;
import com.example.shamo.dto.productcategory.FindByIdProductCategoryRes;
import com.example.shamo.dto.productcategory.InsertProductCategoryReq;
import com.example.shamo.dto.productcategory.ProductCategoryData;
import com.example.shamo.dto.productcategory.UpdateProductCategoryReq;
import com.example.shamo.model.ProductCategories;
import com.example.shamo.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl extends BaseServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao categoryDao;

	@Override
	public FindAllProductCategoryRes findAllProductCategory() throws Exception {
		List<ProductCategoryData> categoryData = new ArrayList<>();
		List<ProductCategories> categories = categoryDao.findAllCategory();
		for (int i = 0; i < categories.size(); i++) {
			ProductCategoryData category = new ProductCategoryData();
			category.setId(categories.get(i).getId());
			category.setCategory(categories.get(i).getCategory());
			category.setIsActive(categories.get(i).getIsActive());

			categoryData.add(category);
		}

		FindAllProductCategoryRes res = new FindAllProductCategoryRes();
		res.setData(categoryData);

		return res;
	}

	@Override
	public FindByIdProductCategoryRes findByIdProductCategory(Long id) throws Exception {
		ProductCategoryData category = new ProductCategoryData();
		ProductCategories categories = categoryDao.findByIdCategory(id);
		category.setId(categories.getId());
		category.setCategory(categories.getCategory());
		category.setIsActive(categories.getIsActive());

		FindByIdProductCategoryRes res = new FindByIdProductCategoryRes();
		res.setData(category);

		return res;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertRes insert(InsertProductCategoryReq category) throws Exception {
		ProductCategories categories = new ProductCategories();
		categories.setCategory(category.getCategory());
		categories.setCreatedBy(getUserId());
		categories.setIsActive(true);

		ProductCategories inserted = categoryDao.insertCategory(categories);

		InsertResData resData = new InsertResData();
		resData.setId(inserted.getId());

		InsertRes res = new InsertRes();
		res.setData(resData);
		res.setMessage("Berhasil");

		return res;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateRes update(UpdateProductCategoryReq category) throws Exception {
		ProductCategories categoryData = categoryDao.findByIdCategory(category.getId());
		categoryData.setCategory(category.getCategory());
		categoryData.setIsActive(category.getIsActive());
		categoryData.setUpdatedBy(getUserId());
		
		ProductCategories update = categoryDao.updateCategory(categoryData);
		
		UpdateResData resData = new UpdateResData();
		resData.setVersion(update.getVersion());
		
		UpdateRes res = new UpdateRes();
		res.setData(resData);
		res.setMessage("Berhasil");
		
		return res;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteRes delete(Long id) throws Exception {
		Boolean delete = categoryDao.deleteCategory(id);
		DeleteRes res = null;
		if(delete) {
			res = new DeleteRes();
			res.setMessage("Berhasil");
		}

		return res;
	}

}
