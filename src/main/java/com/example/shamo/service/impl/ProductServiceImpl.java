package com.example.shamo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.shamo.dao.FileDao;
import com.example.shamo.dao.ProductGalleryDao;
import com.example.shamo.dto.*;
import com.example.shamo.dto.product.*;
import com.example.shamo.dto.productgallery.ProductGalleryData;
import com.example.shamo.model.Files;
import com.example.shamo.model.ProductGalleries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shamo.dao.ProductCategoryDao;
import com.example.shamo.dao.ProductDao;
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
	public FindAllProductRes findAllProduct(String category) throws Exception {
		List<ProductListData> productData = new ArrayList<>();

		List<Products> products = productDao.findAllProducts(category);
		for (Products value : products) {
			ProductListData product = new ProductListData();
			product.setId(value.getId());
			product.setProductName(value.getProductName());
			product.setCategoryId(value.getCategory().getId());

			ProductCategories categories = categoryDao.findByIdCategory(value.getCategory().getId());
			product.setCategoryName(categories.getCategory());

			product.setPrice(value.getPrice());
			product.setDescription(value.getDescription());
			product.setTags(value.getTags());
			product.setIsActive(value.getIsActive());

			ProductGalleries gallery = galleryDao.findByProduct(value.getId());
			if (gallery != null) {
				product.setFileId(gallery.getId());
			}

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

		ProductCategories categories = categoryDao.findByIdCategory(products.getCategory().getId());
		product.setCategoryName(categories.getCategory());

		product.setPrice(products.getPrice());
		product.setDescription(products.getDescription());
		product.setTags(products.getDescription());

		List<ProductGalleries> galleries = galleryDao.findByProductId(products.getId());
		List<ProductGalleryData> galleriesData = new ArrayList<>();

		for (ProductGalleries productGalleries : galleries) {
			ProductGalleryData gallery = new ProductGalleryData();

			Files files = fileDao.findById(productGalleries.getFile().getId());
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
    @Transactional(rollbackOn = Exception.class)
    public UpdateRes updateProduct(UpdateProductReq product) throws Exception {
        Products products = productDao.findByIdProduct(product.getId());
        products.setProductName(product.getProductName());

        ProductCategories categories = categoryDao.findByIdCategory(product.getCategoryId());
        products.setCategory(categories);

        products.setPrice(product.getPrice());
        products.setDescription(product.getDescription());
        products.setTags(product.getTags());
        products.setIsActive(product.getActive());

        Products updated = productDao.updateProduct(products);

        UpdateResData resData = new UpdateResData();
        resData.setVersion(updated.getVersion());

        UpdateRes res = new UpdateRes();
        res.setData(resData);
        res.setMessage("Success");
        return res;
    }

    @Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteRes deleteProduct(Long id) throws Exception {
		return null;
	}

}
