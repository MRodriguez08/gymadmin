/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.PaymentDao;
import com.gymadmin.persistence.entities.PaymentEntity;

/**
 *
 * @author mrodriguez
 */
@Component("paymentDao")
public class PaymentDaoImpl extends DaoImpl<Integer , PaymentEntity> implements PaymentDao {

	@Override
	public List<PaymentEntity> findActiveByCustomer(Integer customerId) {
		Query namedQuery = em.createNamedQuery("PaymentEntity.findActiveByCustomer");
		namedQuery.setParameter("customerId", customerId);
		return (List<PaymentEntity>)namedQuery.getResultList();
	}
	
	@Override
	public List<PaymentEntity> findActive() {
		Query namedQuery = em.createNamedQuery("PaymentEntity.findActive");
		return (List<PaymentEntity>)namedQuery.getResultList();
	}
	
	@Override
	public List<PaymentEntity> findByFilters(Map<String , Object> filters) {
		Query namedQuery = em.createNamedQuery("PaymentEntity.findAllByFilters");
		namedQuery.setParameter("stateId", filters.get("stateId") == null ? 0 :  (Integer)filters.get("stateId") );
		namedQuery.setParameter("customerName", "%" + ( filters.get("customerName") == null ? "" : (String)filters.get("customerName") ) + "%" );
		return (List<PaymentEntity>)namedQuery.getResultList();
	}
    
}
