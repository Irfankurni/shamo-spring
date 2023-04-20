package com.example.shamo.controller;

import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.product.UpdateProductReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<?> findAll(@RequestParam(required = false) String category) throws Exception {
		FindAllProductRes data = productService.findAllProduct(category);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {
		FindByIdProductRes data = productService.findByIdProduct(id);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody InsertProductReq product) throws Exception {
		InsertRes data = productService.insertProduct(product);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody UpdateProductReq product) throws Exception {
		UpdateRes data = productService.updateProduct(product);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
