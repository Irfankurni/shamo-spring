package com.example.shamo.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shamo.dto.LoginReq;
import com.example.shamo.dto.LoginRes;
import com.example.shamo.dto.LoginResData;
import com.example.shamo.exception.InvalidLoginException;
import com.example.shamo.model.Users;
import com.example.shamo.security.JwtComponent;
import com.example.shamo.service.UserService;

@RestController
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtComponent jwtComponent;

	@PostMapping
	public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginReq) throws Exception {
		try {
			authenticationManager
					.authenticate(
							new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPasswords()))
					.isAuthenticated();
		} catch (Exception e) {
			throw new InvalidLoginException("Invalid Username/Passwords");
		}
		Users user = userService.login(loginReq.getEmail());
		String token = jwtComponent.generateToken(Duration.ofDays(1), user.getId().toString());
		LoginRes loginRes = new LoginRes();
		LoginResData data = new LoginResData();
		data.setEmail(user.getEmail());
		data.setUsername(user.getUsername());
		data.setToken(token);
		data.setRoleType(user.getRole().getRoleType());

		loginRes.setData(data);

		return new ResponseEntity<LoginRes>(loginRes, HttpStatus.OK);
	}

}
