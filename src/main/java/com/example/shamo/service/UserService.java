package com.example.shamo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.user.FindAllUserRes;
import com.example.shamo.dto.user.FindByIdUser;
import com.example.shamo.dto.user.InsertUserReq;
import com.example.shamo.dto.user.UpdateUserReq;
import com.example.shamo.model.Users;

public interface UserService extends UserDetailsService {

	FindAllUserRes findAllUsers() throws Exception;

	FindByIdUser findByIdUser(Long id) throws Exception;

	FindByIdUser findLoggedInUser() throws Exception;

	InsertRes insertUser(InsertUserReq users) throws Exception;

	UpdateRes updateUser(UpdateUserReq users) throws Exception;

	DeleteRes deleteUser(Long id) throws Exception;

	Users login(String email) throws Exception;

}
