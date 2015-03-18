package com.gymadmin.services;

import java.util.List;
import java.util.Map;

import com.gymadmin.persistence.entities.PaymentMethodEntity;

/**
 *
 * @author mrodriguez
 */
public interface PaymentMethodBusiness {
    
    public List<PaymentMethodEntity> findAll();
    
    public List<PaymentMethodEntity> findByFilters(Map<String , String> filters);
    
    public void create(PaymentMethodEntity e) throws Exception;
    
    public void edit(PaymentMethodEntity e) throws Exception;
    
    public PaymentMethodEntity get(Integer Id) throws Exception;
    
    public void delete(Integer Id) throws Exception;
    
}
