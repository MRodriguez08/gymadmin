/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.PlanDao;
import com.gymadmin.persistence.entities.PlanEntity;

/**
 *
 * @author mrodriguez
 */
@Component("planDao")
public class PlanDaoImpl extends DaoImpl<Integer , PlanEntity> implements PlanDao {
    
	public PlanEntity findByName(String name) {		
		Query namedQuery = em.createNamedQuery("PlanEntity.findByName");
		namedQuery.setParameter("name", name);
		try {
			return (PlanEntity)namedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
				
	}

	public List<PlanEntity> findByFilters(Map<String, String> filters) {	
		Query namedQuery = em.createNamedQuery("PlanEntity.findByFilters");
		namedQuery.setParameter("name", "%" + filters.get("name").toUpperCase() + "%");
		namedQuery.setParameter("description", "%" + filters.get("description").toUpperCase() + "%");
		return (List<PlanEntity>)namedQuery.getResultList();
	}
	
	
}
