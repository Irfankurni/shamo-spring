package com.example.shamo.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.shamo.dao.ProfileDao;
import com.example.shamo.entitymanager.BaseEntityManager;
import com.example.shamo.model.Profiles;

@Repository
public class ProfileDaoImpl extends BaseEntityManager implements ProfileDao {

	@Override
	public List<Profiles> findAllProfiles() throws Exception {
		String sql = "FROM Profiles";
		List<Profiles> profiles = em.createQuery(sql, Profiles.class).getResultList();
		return profiles;
	}

	@Override
	public Profiles findByIdProfile(Long id) throws Exception {
		Profiles profile = em.find(Profiles.class, id);
		return profile;
	}
	
	@Override
	public Profiles findByUserId(Long userId) throws Exception {
		String sql = "SELECT p FROM Profiles p WHERE p.user.id = :userId";
		Profiles profile = null;
		try {
			profile = new Profiles();
			profile = em.createQuery(sql, Profiles.class).setParameter("userId", userId).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}

	@Override
	public Profiles insertProfile(Profiles profiles) throws Exception {
		em.persist(profiles);
		return profiles;
	}

	@Override
	public Profiles updateProfile(Profiles profiles) throws Exception {
		Profiles updatedProfile = em.merge(profiles);
		em.flush();
		return updatedProfile;
	}

	@Override
	public Boolean deleteProfile(Long id) throws Exception {
		String sql = "DELETE FROM Profiles WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

	

}
