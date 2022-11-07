package com.example.shamo.dao;

import java.util.List;

import com.example.shamo.model.Users;

public interface UserDao {

	List<Users> findAllUsers() throws Exception;

	Users findByIdUser(Long id) throws Exception;

	Users insertUser(Users users) throws Exception;

	Users updateUser(Users users) throws Exception;

	Boolean deleteUser(Long id) throws Exception;
	
	Users findByUsername(String email) throws Exception;

}
