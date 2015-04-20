package com.gymadmin.services;

import java.util.List;
import java.util.Map;

import com.gymadmin.persistence.entities.PlanEntity;

/**
 *
 * @author mrodriguez
 */
public interface PlanService {
    
    public List<PlanEntity> findAll();
    
    public List<PlanEntity> findByFilters(Map<String , String> filters);
    
    public PlanEntity create(PlanEntity e) throws Exception;
    
    public PlanEntity edit(PlanEntity e) throws Exception;
    
    public PlanEntity get(Integer Id) throws Exception;
    
    public PlanEntity get(String name) throws Exception;
    
    public void delete(Integer Id) throws Exception;
    
}
