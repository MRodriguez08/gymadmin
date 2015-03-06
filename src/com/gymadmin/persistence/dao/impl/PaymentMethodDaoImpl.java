/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.gymadmin.persistence.dao.PaymentMethodDao;
import com.gymadmin.persistence.entities.PaymentMethodEntity;
import com.gymadmin.repository.HibernateUtil;

/**
 *
 * @author mrodriguez
 */
public class PaymentMethodDaoImpl extends DaoImpl<Integer , PaymentMethodEntity> implements PaymentMethodDao {
    
	public PaymentMethodEntity findByName(String name) {		
		Query namedQuery = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("PaymentMethodEntity.findByName");
		namedQuery.setParameter("name", name);
		return (PaymentMethodEntity)namedQuery.uniqueResult();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentMethodEntity> findByFilters(Map<String, String> filters) {		
		Query namedQuery = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("PaymentMethodEntity.findByFilters");
		namedQuery.setParameter("name", "%" + filters.get("name").toUpperCase() + "%");
		namedQuery.setParameter("description", "%" + filters.get("description").toUpperCase() + "%");
		return (List<PaymentMethodEntity>)namedQuery.list();
	}
	
}
