package com.example.shamo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.shamo.dao.RoleDao;
import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.Role;

@Repository
public class RoleDaoImpl extends BaseEntityManager implements RoleDao {

	@Override
	public List<Role> findAllRoles() throws Exception {
		String sql = "SELECT r FROM Role r";
		List<Role> role = em.createQuery(sql, Role.class).getResultList();
		return role;
	}

	@Override
	public Role findByIdRole(Long id) throws Exception {
		Role role = em.find(Role.class, id);
		return role;
	}

	@Override
	public Role insertRole(Role role) throws Exception {
		em.persist(role);
		return role;
	}

	@Override
	public Role updateRole(Role role) throws Exception {
		Role updatedRole = em.merge(role);
		em.flush();
		return updatedRole;
	}

	@Override
	public Boolean deleteRole(Long id) throws Exception {
		String sql = "DELETE FROM Role WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

}
