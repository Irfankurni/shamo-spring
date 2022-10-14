package com.example.shamo.dao;

import java.util.List;

import com.example.shamo.model.Profiles;


public interface ProfileDao {

	List<Profiles> findAllProfiles() throws Exception;

	Profiles findByIdProfile(Long id) throws Exception;
	
	Profiles findByUserId(Long userId) throws Exception;

	Profiles insertProfile(Profiles profiles) throws Exception;

	Profiles updateProfile(Profiles profiles) throws Exception;

	Boolean deleteProfile(Long id) throws Exception;
	
}
