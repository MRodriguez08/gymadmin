/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao;

import java.util.List;

import com.gymadmin.persistence.entities.PaymentEntity;


/**
 *
 * @author mrodriguez
 */
public interface PaymentDao extends Dao<Integer , PaymentEntity> {
    
	public List<PaymentEntity> findActiveByCustomer(Integer customerId);
	
}
