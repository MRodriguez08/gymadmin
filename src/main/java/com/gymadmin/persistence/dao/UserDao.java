package com.gymadmin.persistence.dao;

import java.util.Optional;

import com.gymadmin.persistence.entities.UserEntity;


/**
 *
 * @author mrodriguez
 */
public interface UserDao extends Dao<String , UserEntity> {

	public Optional<UserEntity> findByNick(String nick);
	
}
