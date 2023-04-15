package com.example.shamo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.transaction.FindAllTransactionRes;
import com.example.shamo.dto.transaction.FindByIdTransactionRes;
import com.example.shamo.dto.transaction.InsertTransactionReq;
import com.example.shamo.service.TransactionService;

@RestController
@RequestMapping("transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService trxService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		FindAllTransactionRes data = trxService.findAll();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {
		FindByIdTransactionRes data = trxService.findById(id);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody InsertTransactionReq trx) throws Exception {
		InsertRes data = trxService.insert(trx);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

}
