/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao;

import java.util.List;
import java.util.Map;

import com.gymadmin.persistence.entities.PaymentMethodEntity;


/**
 *
 * @author mrodriguez
 */
public interface PaymentMethodDao extends Dao<Integer , PaymentMethodEntity> {
    
	public PaymentMethodEntity findByName(String name);
	
	public List<PaymentMethodEntity> findByFilters(Map<String , String> filters);
	
}
