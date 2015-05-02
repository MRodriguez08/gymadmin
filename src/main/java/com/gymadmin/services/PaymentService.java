package com.gymadmin.services;

import java.util.List;
import java.util.Map;

import com.gymadmin.persistence.entities.PaymentEntity;

/**
 *
 * @author mrodriguez
 */
public interface PaymentService {
    
    public List<PaymentEntity> findAll();
    
    public List<PaymentEntity> findByFilters(Map<String , Object> filters);
    
    public PaymentEntity create(PaymentEntity e) throws Exception;
    
    public PaymentEntity edit(PaymentEntity e) throws Exception;
    
    public PaymentEntity pay(PaymentEntity e) throws Exception;
    
    public PaymentEntity get(Integer Id) throws Exception;
    
    public void delete(Integer Id) throws Exception;
    
    public void generatePayments();
    
    public void updatePaymentsState();
    
}
