package com.example.shamo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.shamo.dao.TransactionDetailDao;
import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.TransactionDetails;

@Repository
public class TransactionDetailDaoImpl extends BaseEntityManager implements TransactionDetailDao{

	@Override
	public List<TransactionDetails> findAllTransactions() throws Exception {
		String sql = "FROM TransactionDetails";
		List<TransactionDetails> transactionDetails = em.createQuery(sql, TransactionDetails.class).getResultList();
		return transactionDetails;
	}

	@Override
	public TransactionDetails findByIdTransaction(Long id) throws Exception {
		TransactionDetails transactionDetails = em.find(TransactionDetails.class, id);
		return transactionDetails;
	}

	@Override
	public TransactionDetails insertTransaction(TransactionDetails transactionDetails) throws Exception {
		em.persist(transactionDetails);
		return transactionDetails;
	}

	@Override
	public TransactionDetails updateTransaction(TransactionDetails transactionDetails) throws Exception {
		TransactionDetails updatedDetails = em.merge(transactionDetails);
		em.flush();
		return updatedDetails;
	}

	@Override
	public Boolean deleteTransaction(Long id) throws Exception {
		String sql = "DELETE FROM TransactionDetails WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

}
