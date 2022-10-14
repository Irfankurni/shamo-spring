package com.example.shamo.dao;

import java.util.List;

import com.example.shamo.model.Role;

public interface RoleDao {

	List<Role> findAllRoles() throws Exception;

	Role findByIdRole(Long id) throws Exception;

	Role insertRole(Role role) throws Exception;

	Role updateRole(Role role) throws Exception;

	Boolean deleteRole(Long id) throws Exception;

}
