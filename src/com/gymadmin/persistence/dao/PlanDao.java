
package com.gymadmin.persistence.dao;

import java.util.List;
import java.util.Map;

import com.gymadmin.persistence.entities.PlanEntity;
import com.mrodriguez.commons.persistence.dao.Dao;

/**
 *
 * @author mauricio
 */
public interface PlanDao extends Dao<Integer , PlanEntity> {
	
	public PlanEntity findByName(String name);
	
	public List<PlanEntity> findByFilters(Map<String , String> filters);
    
}
