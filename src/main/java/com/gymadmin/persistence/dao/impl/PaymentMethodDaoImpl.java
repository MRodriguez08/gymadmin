/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.PaymentPlanDao;
import com.gymadmin.persistence.entities.PaymentPlanEntity;

/**
 *
 * @author mrodriguez
 */
@Component("paymentMethodDao")
public class PaymentMethodDaoImpl extends DaoImpl<Integer , PaymentPlanEntity> implements PaymentPlanDao {
    
	public PaymentPlanEntity findByName(String name) {
		Query namedQuery = em.createNamedQuery("PaymentMethodEntity.findByName");
		namedQuery.setParameter("name", name);
		return (PaymentPlanEntity)namedQuery.getSingleResult();		
	}

	@SuppressWarnings("unchecked")
	public List<PaymentPlanEntity> findByFilters(Map<String, String> filters) {		
		Query namedQuery = em.createNamedQuery("PaymentMethodEntity.findByFilters");
		namedQuery.setParameter("name", "%" + filters.get("name").toUpperCase() + "%");
		namedQuery.setParameter("description", "%" + filters.get("description").toUpperCase() + "%");
		return (List<PaymentPlanEntity>)namedQuery.getResultList();
	}
	
}
