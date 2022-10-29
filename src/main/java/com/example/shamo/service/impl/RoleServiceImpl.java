package com.example.shamo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shamo.dao.RoleDao;
import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.InsertResData;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.UpdateResData;
import com.example.shamo.dto.role.FindAllRoleRes;
import com.example.shamo.dto.role.FindByIdRoleRes;
import com.example.shamo.dto.role.InsertRoleReq;
import com.example.shamo.dto.role.RoleData;
import com.example.shamo.dto.role.UpdateRoleReq;
import com.example.shamo.model.Role;
import com.example.shamo.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public FindAllRoleRes findAllRoles() throws Exception {
		FindAllRoleRes res = new FindAllRoleRes();
		List<RoleData> roleData = new ArrayList<>();

		List<Role> roles = roleDao.findAllRoles();
		for (int i = 0; i < roles.size(); i++) {
			RoleData role = new RoleData();
			role.setId(roles.get(i).getId());
			role.setRoleType(roles.get(i).getRoleType());
			role.setIsActive(roles.get(i).getIsActive());
			role.setVersion(roles.get(i).getVersion());

			roleData.add(role);
		}
		res.setData(roleData);

		return res;
	}

	@Override
	public FindByIdRoleRes findByIdRole(Long id) throws Exception {
		FindByIdRoleRes res = new FindByIdRoleRes();
		
		Role roleData = roleDao.findByIdRole(id);
		
		RoleData data = new RoleData();
		data.setId(roleData.getId());
		data.setRoleType(roleData.getRoleType());
		data.setIsActive(roleData.getIsActive());
		data.setVersion(roleData.getVersion());
		
		res.setData(data);
		
		return res;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertRes insertRole(InsertRoleReq role) throws Exception {
		Role insert = new Role();
		insert.setRoleType(role.getRoleType());
		insert.setCreatedBy(getUserId());
		insert.setIsActive(true);
		
		Role result = roleDao.insertRole(insert);
		
		InsertResData resData = new InsertResData();
		resData.setId(result.getId());
		
		InsertRes data = new InsertRes();
		data.setData(resData);
		data.setMessage("Berhasil");
		return data;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateRes updateRole(UpdateRoleReq role) throws Exception {
		Role roleData = roleDao.findByIdRole(role.getId());
		roleData.setRoleType(role.getRoleType());
		roleData.setIsActive(role.getIsActive());
		roleData.setUpdatedBy(getUserId());
		
		Role updated = roleDao.updateRole(roleData);
		
		UpdateResData resData = new UpdateResData();
		resData.setVersion(updated.getVersion());
		
		UpdateRes res = new UpdateRes();
		res.setData(resData);
		res.setMessage("Berhasil");
		
		return res;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteRes deleteRole(Long id) throws Exception {
		Boolean deleted = roleDao.deleteRole(id);
		
		DeleteRes res = null;
		if(deleted) {
			res = new DeleteRes();
			res.setMessage("Berhasil");
		}
		return res;
	}

}
