package com.example.shamo.service;

import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.transaction.FindAllTransactionRes;
import com.example.shamo.dto.transaction.FindByIdTransactionRes;
import com.example.shamo.dto.transaction.InsertTransactionReq;

public interface TransactionService {

	FindAllTransactionRes findAll() throws Exception;

	FindByIdTransactionRes findById(Long id) throws Exception;

	InsertRes insert(InsertTransactionReq transaction) throws Exception;

}
