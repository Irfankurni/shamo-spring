package com.example.shamo.controller;


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
import com.example.shamo.dto.role.FindAllRoleRes;
import com.example.shamo.dto.role.FindByIdRoleRes;
import com.example.shamo.dto.role.InsertRoleReq;
import com.example.shamo.dto.role.UpdateRoleReq;
import com.example.shamo.service.RoleService;

@RestController
@RequestMapping("roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ResponseEntity<FindAllRoleRes> findAll() throws Exception {
		FindAllRoleRes data = roleService.findAllRoles();
		return new ResponseEntity<FindAllRoleRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<FindByIdRoleRes> findById(@PathVariable Long id) throws Exception {
		FindByIdRoleRes data = roleService.findByIdRole(id);
		return new ResponseEntity<FindByIdRoleRes>(data, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody InsertRoleReq role) throws Exception {
		InsertRes data = roleService.insertRole(role);
		return new ResponseEntity<InsertRes>(data, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody UpdateRoleReq role) throws Exception {
		UpdateRes data = roleService.updateRole(role);
		return new ResponseEntity<UpdateRes>(data, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteRes> delete(@PathVariable Long id) throws Exception {
		DeleteRes data = roleService.deleteRole(id);
		return new ResponseEntity<DeleteRes>(data, HttpStatus.OK);
	}

}
