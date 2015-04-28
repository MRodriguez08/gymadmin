/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao;

import java.util.List;
import java.util.Map;

import com.gymadmin.persistence.entities.PaymentPlanEntity;


/**
 *
 * @author mrodriguez
 */
public interface PaymentPlanDao extends Dao<Integer , PaymentPlanEntity> {
    
	public PaymentPlanEntity findByName(String name);
	
	public List<PaymentPlanEntity> findByFilters(Map<String , String> filters);
	
}
