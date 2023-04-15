package com.example.shamo.dao;

import java.util.List;

import com.example.shamo.model.TransactionDetails;

public interface TransactionDetailDao {

	List<TransactionDetails> findAllTransactions() throws Exception;

	TransactionDetails findByIdTransaction(Long id) throws Exception;

	TransactionDetails insertTransaction(TransactionDetails transactionDetails) throws Exception;

	TransactionDetails updateTransaction(TransactionDetails transactionDetails) throws Exception;

	Boolean deleteTransaction(Long id) throws Exception;

}
