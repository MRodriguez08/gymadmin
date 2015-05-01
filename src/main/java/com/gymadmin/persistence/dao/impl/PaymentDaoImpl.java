/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

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
    
}
