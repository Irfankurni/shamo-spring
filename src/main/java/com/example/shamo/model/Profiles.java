package com.example.shamo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Profiles extends BaseModel {

	@Column(name="fullname")
	private String fullName;
	private String address;

	@ManyToOne
	@JoinColumn(name = "file_id")
	private Files file;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Files getFile() {
		return file;
	}

	public void setFile(Files file) {
		this.file = file;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
