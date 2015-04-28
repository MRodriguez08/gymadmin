package com.gymadmin.services;

import java.util.List;
import java.util.Map;

import com.gymadmin.persistence.entities.PaymentPlanEntity;

/**
 *
 * @author mrodriguez
 */
public interface PaymentPlanService {
    
    public List<PaymentPlanEntity> findAll();
    
    public List<PaymentPlanEntity> findByFilters(Map<String , String> filters);
    
    public PaymentPlanEntity create(PaymentPlanEntity e) throws Exception;
    
    public PaymentPlanEntity edit(PaymentPlanEntity e) throws Exception;
    
    public PaymentPlanEntity get(Integer Id) throws Exception;
    
    public void delete(Integer Id) throws Exception;
    
}
