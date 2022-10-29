package com.example.shamo.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.shamo.exception.InvalidLoginException;

public class BaseServiceImpl {
	
	public Long getUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null) {
			throw new InvalidLoginException("Invalid Username/password");
		}
		Long id = Long.valueOf(auth.getPrincipal().toString());
		return id;
	}

}
