/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.PlanDao;
import com.gymadmin.persistence.dao.UserDao;
import com.gymadmin.persistence.entities.PlanEntity;
import com.gymadmin.persistence.entities.UserEntity;

/**
 *
 * @author mrodriguez
 */
@Component("userDao")
public class UserDaoImpl extends DaoImpl<String , UserEntity> implements UserDao {
    
	public Optional<UserEntity> findByNick(String nick) {		
		Query namedQuery = em.createNamedQuery("UserEntity.findByNick");
		namedQuery.setParameter("name", nick);
		return Optional.of((UserEntity)namedQuery.getSingleResult());		
	}
	
	
}
