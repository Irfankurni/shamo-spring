package com.example.shamo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.shamo.dao.TransactionDao;
import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.Transactions;

@Repository
public class TransactionDaoImpl extends BaseEntityManager implements TransactionDao {

	@Override
	public List<Transactions> findAllTransactions() throws Exception {
		String sql = "SELECT t FROM Transactions t";
		List<Transactions> transactions = em.createQuery(sql, Transactions.class).getResultList();
		return transactions;
	}

	@Override
	public Transactions findByIdTransaction(Long id) throws Exception {
		Transactions transaction = em.find(Transactions.class, id);
		return transaction;
	}

	@Override
	public Transactions insertTransaction(Transactions transactions) throws Exception {
		em.persist(transactions);
		return transactions;
	}

	@Override
	public Transactions updateTransaction(Transactions transactions) throws Exception {
		Transactions updatedTransactions = em.merge(transactions);
		em.flush();
		return updatedTransactions;
	}

	@Override
	public Boolean deleteTransaction(Long id) throws Exception {
		String sql = "DELETE FROM Transactions WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}


}
