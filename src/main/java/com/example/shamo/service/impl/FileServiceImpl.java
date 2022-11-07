package com.example.shamo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shamo.dao.FileDao;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.InsertResData;
import com.example.shamo.dto.file.InsertFileReq;
import com.example.shamo.model.Files;
import com.example.shamo.service.FileService;

@Service
public class FileServiceImpl extends BaseServiceImpl implements FileService {

	@Autowired
	private FileDao fileDao;

	@Override
	public Files getById(Long id) throws Exception {
		Files file = fileDao.findById(id);
		return file;
	}

	@Override
	public InsertRes insert(InsertFileReq data) throws Exception {
		Files file = new Files();
		file.setFileName(data.getFileName());
		file.setFileExtension(data.getFileName());
		file.setCreatedBy(getUserId());
		file.setIsActive(true);
		
		Files inserted = fileDao.insert(file);
		
		InsertResData resData = new InsertResData();
		resData.setId(inserted.getId());
		
		InsertRes res = new InsertRes();
		res.setData(resData);
		res.setMessage("Berhasil");
		return res;
	}

}
