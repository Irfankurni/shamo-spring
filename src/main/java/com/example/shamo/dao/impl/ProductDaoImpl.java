package com.example.shamo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.shamo.dao.ProductDao;
import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.Products;

@Repository
public class ProductDaoImpl extends BaseEntityManager implements ProductDao {

	@Override
	public List<Products> findAllProducts() throws Exception {
		String sql = "FROM Products";
		List<Products> products = em.createQuery(sql, Products.class).getResultList();
		return products;
	}

	@Override
	public Products findByIdProduct(Long id) throws Exception {
		Products product = em.find(Products.class, id);
		return product;
	}

	@Override
	public Products insertProduct(Products products) throws Exception {
		em.persist(products);
		return products;
	}

	@Override
	public Products updateProduct(Products products) throws Exception {
		Products updatedProduct = em.merge(products);
		em.flush();
		return updatedProduct;
	}

	@Override
	public Boolean deleteProduct(Long id) throws Exception {
		String sql = "DELETE FROM Products WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

}
