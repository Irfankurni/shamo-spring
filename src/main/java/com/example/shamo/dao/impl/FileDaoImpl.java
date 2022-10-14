package com.example.shamo.dao.impl;

import org.springframework.stereotype.Repository;

import com.example.shamo.dao.FileDao;
import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.Files;

@Repository
public class FileDaoImpl extends BaseEntityManager implements FileDao{

	@Override
	public Files findById(Long id) throws Exception {
		Files file = em.find(Files.class, id);
		return file;
	}

	@Override
	public Files insert(Files files) throws Exception {
		em.persist(files);
		return files;
	}

}
