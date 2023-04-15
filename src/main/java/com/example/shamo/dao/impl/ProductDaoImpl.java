package com.example.shamo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.shamo.dao.ProductDao;
import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.Products;

import javax.persistence.TypedQuery;

@Repository
public class ProductDaoImpl extends BaseEntityManager implements ProductDao {

	@Override
	public List<Products> findAllProducts(String category) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p FROM Products p LEFT JOIN ProductCategories pc ON p.category.id = pc.id ");

		if(category != null && !category.isEmpty()) {
			sql.append("WHERE LOWER(pc.category) = LOWER(:category)");
		}

		sql.append("ORDER BY p.createdAt ASC");

		TypedQuery<Products> query = em.createQuery(sql.toString(), Products.class);

		if(category != null && !category.isEmpty()) {
			query.setParameter("category", category);
		}

		return query.getResultList();
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
