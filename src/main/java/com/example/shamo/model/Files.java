package com.example.shamo.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Files extends BaseModel {

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_extension", length = 8)
	private String fileExtension;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

}
