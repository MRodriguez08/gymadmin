/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.UserDao;
import com.gymadmin.persistence.entities.UserEntity;

/**
 *
 * @author mrodriguez
 */
@Component("userDao")
public class UserDaoImpl extends DaoImpl<String , UserEntity> implements UserDao {
    
	public UserEntity findByNick(String nick) {		
		Query namedQuery = em.createNamedQuery("UserEntity.findByNick");
		namedQuery.setParameter("nick", nick);
		try {
			return (UserEntity)namedQuery.getSingleResult();	
		} catch (NoResultException e) {
			return null;
		}			
	}
	
	
}
