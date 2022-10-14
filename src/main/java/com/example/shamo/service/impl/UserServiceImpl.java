package com.example.shamo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shamo.dao.ProfileDao;
import com.example.shamo.dao.RoleDao;
import com.example.shamo.dao.UserDao;
import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.InsertResData;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.UpdateResData;
import com.example.shamo.dto.user.FindAllUserRes;
import com.example.shamo.dto.user.FindByIdUser;
import com.example.shamo.dto.user.InsertUserReq;
import com.example.shamo.dto.user.UpdateUserReq;
import com.example.shamo.dto.user.UserData;
import com.example.shamo.model.Profiles;
import com.example.shamo.model.Role;
import com.example.shamo.model.Users;
import com.example.shamo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProfileDao profileDao;

	@Override
	public FindAllUserRes findAllUsers() throws Exception {

		List<UserData> userData = new ArrayList<>();
		List<Users> users = userDao.findAllUsers();
		for (int i = 0; i < users.size(); i++) {
			UserData user = new UserData();
			user.setId(users.get(i).getId());
			user.setUsername(users.get(i).getUsername());
			user.setEmail(users.get(i).getEmail());
			user.setRoleId(users.get(i).getRole().getId());

			Profiles profile = profileDao.findByUserId(users.get(i).getId());
			user.setFullName(profile.getFullName());
			user.setAddress(profile.getAddress());
			if (profile.getFile() != null) {
				user.setFileId(profile.getFile().getId());
			}
			user.setUserId(profile.getUser().getId());

			userData.add(user);
		}

		FindAllUserRes res = new FindAllUserRes();
		res.setData(userData);

		return res;
	}

	@Override
	public FindByIdUser findByIdUser(Long id) throws Exception {

		Users users = userDao.findByIdUser(id);

		UserData user = new UserData();
		user.setId(users.getId());
		user.setUsername(users.getUsername());
		user.setEmail(users.getEmail());
		user.setRoleId(users.getRole().getId());

		Profiles profile = profileDao.findByUserId(users.getId());
		user.setFullName(profile.getFullName());
		user.setAddress(profile.getAddress());
		if (profile.getFile() != null) {
			user.setFileId(profile.getFile().getId());
		}
		user.setUserId(profile.getUser().getId());

		FindByIdUser res = new FindByIdUser();
		res.setData(user);
		return res;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertRes insertUser(InsertUserReq users) throws Exception {
		Users user = new Users();
		user.setUsername(users.getUsername());
		user.setEmail(users.getEmail());
		user.setPasswords(users.getPasswords());
		user.setIsActive(true);

		Role role = roleDao.findByIdRole(users.getRoleId());
		user.setRole(role);

		Users insertedUser = userDao.insertUser(user);

		Profiles profile = new Profiles();
		profile.setFullName(users.getFullName());
		profile.setUser(insertedUser);
		profile.setIsActive(true);

		profileDao.insertProfile(profile);

		InsertResData resData = new InsertResData();
		resData.setId(user.getId());

		InsertRes res = new InsertRes();
		res.setData(resData);
		res.setMessage("Berhasil");

		return res;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateRes updateUser(UpdateUserReq users) throws Exception {
		Users user = userDao.findByIdUser(users.getId());
		user.setUsername(users.getUsername());
		user.setEmail(users.getEmail());
		
		Role role = roleDao.findByIdRole(users.getRoleId());
		user.setRole(role);
		
		Users updatedUser = userDao.updateUser(user);
		
		Profiles profile = profileDao.findByUserId(user.getId());
		profile.setFullName(users.getFullName());
		profile.setAddress(users.getAddress());
		profileDao.updateProfile(profile);
		
		UpdateResData resData = new UpdateResData();
		resData.setVersion(updatedUser.getVersion());
		
		UpdateRes data = new UpdateRes();
		data.setData(resData);
		data.setMessage("Berhasil");
		
		return data;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteRes deleteUser(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
