package com.example.shamo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.productcategory.FindAllProductCategoryRes;
import com.example.shamo.dto.productcategory.FindByIdProductCategoryRes;
import com.example.shamo.dto.productcategory.InsertProductCategoryReq;
import com.example.shamo.dto.productcategory.UpdateProductCategoryReq;
import com.example.shamo.service.ProductCategoryService;

@RestController
@RequestMapping("product-categories")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService categoryService;

	@GetMapping
	public ResponseEntity<FindAllProductCategoryRes> findAll() throws Exception {
		FindAllProductCategoryRes data = categoryService.findAllProductCategory();
		return new ResponseEntity<FindAllProductCategoryRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<FindByIdProductCategoryRes> findById(@PathVariable Long id) throws Exception {
		FindByIdProductCategoryRes data = categoryService.findByIdProductCategory(id);
		return new ResponseEntity<FindByIdProductCategoryRes>(data, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody @Valid InsertProductCategoryReq req) throws Exception {
		InsertRes data = categoryService.insert(req);
		return new ResponseEntity<InsertRes>(data, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody UpdateProductCategoryReq req) throws Exception {
		UpdateRes data = categoryService.update(req);
		return new ResponseEntity<UpdateRes>(data, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable Long id) throws Exception {
		DeleteRes data = categoryService.delete(id);
		return new ResponseEntity<DeleteRes>(data, HttpStatus.OK);
	}

}
