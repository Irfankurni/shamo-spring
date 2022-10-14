package com.example.shamo.service;

import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.UpdateRes;
import com.example.shamo.dto.role.FindAllRoleRes;
import com.example.shamo.dto.role.FindByIdRoleRes;
import com.example.shamo.dto.role.InsertRoleReq;
import com.example.shamo.dto.role.UpdateRoleReq;

public interface RoleService {

	FindAllRoleRes findAllRoles() throws Exception;

	FindByIdRoleRes findByIdRole(Long id) throws Exception;

	InsertRes insertRole(InsertRoleReq role) throws Exception;

	UpdateRes updateRole(UpdateRoleReq role) throws Exception;

	DeleteRes deleteRole(Long id) throws Exception;

}
