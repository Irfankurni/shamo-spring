package com.example.shamo.service;

import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.file.InsertFileReq;
import com.example.shamo.model.Files;

public interface FileService {

	Files getById(Long id) throws Exception;

	InsertRes insert(InsertFileReq data) throws Exception;

}
