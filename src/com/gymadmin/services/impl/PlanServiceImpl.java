package com.gymadmin.services.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gymadmin.persistence.dao.PlanDao;
import com.gymadmin.persistence.entities.PlanEntity;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.services.PlanService;

/**
 *
 * @author mrodriguez
 */
public class PlanServiceImpl implements PlanService {

	private static Logger logger = Logger.getLogger(PlanServiceImpl.class);

	@Autowired
	private PlanDao planDao;

	public List<PlanEntity> findAll() {

		List<PlanEntity> users = planDao.findAll();	

		return users;
	}

	public List<PlanEntity> findByFilters(Map<String, String> filters) {
		List<PlanEntity> users = planDao.findByFilters(filters);
		return users;
	}

	public void create(PlanEntity d) throws Exception {
		PlanEntity p = planDao.findByName(d.getName());
		if (p != null)
			throw new BusinessException("Ya existe un plan con ese nombre");

		planDao.persist(d);

	}

	public void edit(PlanEntity d) throws Exception {		
		planDao.merge(d);		
	}

	public PlanEntity get(Integer id) throws Exception {
		PlanEntity entity = planDao.findById(id);		
		return entity;
	}

	public PlanEntity get(String name) throws Exception {		
		PlanEntity entity =  planDao.findByName(name);
		
		return entity;
	}

	public void delete(Integer id) throws Exception {

	}

}
