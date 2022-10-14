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
import com.example.shamo.dto.product.FindAllProductRes;
import com.example.shamo.dto.product.FindByIdProductRes;
import com.example.shamo.dto.product.InsertProductReq;
import com.example.shamo.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		FindAllProductRes data = productService.findAllProduct();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findByid(@PathVariable Long id) throws Exception {
		FindByIdProductRes data = productService.findByIdProduct(id);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody InsertProductReq product) throws Exception {
		InsertRes data = productService.insertProduct(product);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}
	

}
