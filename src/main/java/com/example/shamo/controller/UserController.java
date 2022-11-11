package com.example.shamo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.user.FindAllUserRes;
import com.example.shamo.dto.user.FindByIdUser;
import com.example.shamo.dto.user.InsertUserReq;
import com.example.shamo.dto.user.UpdateUserReq;
import com.example.shamo.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<FindAllUserRes> findAll() throws Exception {
		FindAllUserRes data = userService.findAllUsers();
		return new ResponseEntity<FindAllUserRes>(data, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<FindByIdUser> findById(@PathVariable Long id) throws Exception {
		FindByIdUser data = userService.findByIdUser(id);
		return new ResponseEntity<FindByIdUser>(data, HttpStatus.OK);
	}
	@GetMapping("current")
	public ResponseEntity<FindByIdUser> findLoggedInUser() throws Exception {
		FindByIdUser data = userService.findLoggedInUser();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertRes> insert(@RequestBody InsertUserReq user) throws Exception {
		InsertRes data = userService.insertUser(user);
		return new ResponseEntity<InsertRes>(data, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateRes> update(@RequestBody UpdateUserReq user) throws Exception {
		UpdateRes data = userService.updateUser(user);
		return new ResponseEntity<UpdateRes>(data, HttpStatus.OK);
	}

}
