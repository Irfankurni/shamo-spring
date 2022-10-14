package com.example.shamo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.shamo.dao.ProductCategoryDao;
import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.ProductCategories;

@Repository
public class ProductCategoryDaoImpl extends BaseEntityManager implements ProductCategoryDao {

	@Override
	public List<ProductCategories> findAllCategory() throws Exception {
		String sql = "FROM ProductCategories";
		List<ProductCategories> categories = em.createQuery(sql, ProductCategories.class).getResultList();
		return categories;
	}

	@Override
	public ProductCategories findByIdCategory(Long id) throws Exception {
		ProductCategories category = em.find(ProductCategories.class, id);
		return category;
	}

	@Override
	public ProductCategories insertCategory(ProductCategories categories) throws Exception {
		em.persist(categories);
		return categories;
	}

	@Override
	public ProductCategories updateCategory(ProductCategories categories) throws Exception {
		ProductCategories updatedCategory = em.merge(categories);
		em.flush();
		return updatedCategory;
	}

	@Override
	public Boolean deleteCategory(Long id) throws Exception {
		String sql = "DELETE FROM ProductCategories WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

}
