package com.example.shamo.dao;

import java.util.List;

import com.example.shamo.model.Transactions;

public interface TransactionDao {

	List<Transactions> findAllTransactions() throws Exception;

	Transactions findByIdTransaction(Long id) throws Exception;

	Transactions insertTransaction(Transactions transactions) throws Exception;

	Transactions updateTransaction(Transactions transactions) throws Exception;

	Boolean deleteTransaction(Long id) throws Exception;

}
