package com.example.shamo.service;

import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.user.FindAllUserRes;
import com.example.shamo.dto.user.FindByIdUser;
import com.example.shamo.dto.user.InsertUserReq;
import com.example.shamo.dto.user.UpdateUserReq;

public interface UserService {
	
	FindAllUserRes findAllUsers() throws Exception;

	FindByIdUser findByIdUser(Long id) throws Exception;

	InsertRes insertUser(InsertUserReq users) throws Exception;

	UpdateRes updateUser(UpdateUserReq users) throws Exception;

	DeleteRes deleteUser(Long id) throws Exception;

}
