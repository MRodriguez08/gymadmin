package com.gymadmin.business;

import java.util.List;
import java.util.Map;

import com.gymadmin.persistence.entities.PlanEntity;

/**
 *
 * @author mrodriguez
 */
public interface PlanBusiness {
    
    public List<PlanEntity> findAll();
    
    public List<PlanEntity> findByFilters(Map<String , String> filters);
    
    public void create(PlanEntity e) throws Exception;
    
    public void edit(PlanEntity e) throws Exception;
    
    public PlanEntity get(Integer Id) throws Exception;
    
    public PlanEntity get(String name) throws Exception;
    
    public void delete(Integer Id) throws Exception;
    
}
