package com.example.shamo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.shamo.dao.UserDao;
import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.Users;

@Repository
public class UserDaoImpl extends BaseEntityManager implements UserDao {

	@Override
	public List<Users> findAllUsers() throws Exception {
		String sql = "FROM Users";
		List<Users> users = em.createQuery(sql, Users.class).getResultList();
		return users;
	}

	@Override
	public Users findByIdUser(Long id) throws Exception {
		Users user = em.find(Users.class, id);
		return user;
	}

	@Override
	public Users insertUser(Users users) throws Exception {
		em.persist(users);
		return users;
	}

	@Override
	public Users updateUser(Users users) throws Exception {
		Users updatedUsers = em.merge(users);
		em.flush();
		return updatedUsers;
	}

	@Override
	public Boolean deleteUser(Long id) throws Exception {
		String sql = "DELETE FROM Users WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

	@Override
	public Users findByUsername(String email) throws Exception {
		String sql = "SELECT tu FROM Users tu "
				+ "WHERE tu.email = :email";
		Users user = null;
		try {
			user = em.createQuery(sql, Users.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
