package com.gymadmin.services;

import java.util.List;
import java.util.Map;

import com.gymadmin.persistence.entities.PaymentStateEntity;

/**
 *
 * @author mrodriguez
 */
public interface PaymentStateService {
    
    public List<PaymentStateEntity> findAll();
    
    public List<PaymentStateEntity> findByFilters(Map<String , String> filters);
    
    public PaymentStateEntity create(PaymentStateEntity e) throws Exception;
    
    public PaymentStateEntity edit(PaymentStateEntity e) throws Exception;
    
    public PaymentStateEntity get(Integer Id) throws Exception;
    
    public void delete(Integer Id) throws Exception;
    
}
