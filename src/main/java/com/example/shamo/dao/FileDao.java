package com.example.shamo.dao;

import com.example.shamo.model.Files;

public interface FileDao {

	Files findById(Long id) throws Exception;

	Files insert(Files files) throws Exception;
	Boolean delete(Long id) throws Exception;

}
