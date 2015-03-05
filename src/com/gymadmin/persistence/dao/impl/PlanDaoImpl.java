/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.gymadmin.persistence.dao.PlanDao;
import com.gymadmin.persistence.entities.PlanEntity;
import com.mrodriguez.commons.persistence.HibernateUtil;
import com.mrodriguez.commons.persistence.dao.impl.DaoImpl;

/**
 *
 * @author mrodriguez
 */
public class PlanDaoImpl extends DaoImpl<Integer , PlanEntity> implements PlanDao {
    
	public PlanEntity findByName(String name) {		
		Query namedQuery = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("PlanEntity.findByName");
		namedQuery.setParameter("name", name);
		return (PlanEntity)namedQuery.uniqueResult();		
	}

	@Override
	public List<PlanEntity> findByFilters(Map<String, String> filters) {		
		Query namedQuery = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("PlanEntity.findByFilters");
		namedQuery.setParameter("name", "%" + filters.get("name").toUpperCase() + "%");
		namedQuery.setParameter("description", "%" + filters.get("description").toUpperCase() + "%");
		return (List<PlanEntity>)namedQuery.list();
	}
	
	
}
