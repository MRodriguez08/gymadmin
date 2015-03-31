package com.gymadmin.web.rest;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gymadmin.persistence.entities.PlanEntity;
import com.gymadmin.services.PlanService;

@RestController
@RequestMapping("/restapi/plan")
public class PlanResource {
	
	private static final Logger logger = Logger.getLogger(PlanResource.class);

	@Autowired
	private PlanService planService;	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<PlanEntity> getAll() {
		List<PlanEntity> plansList = planService.findAll();
		return plansList;
	}
	
	@RequestMapping(value="/{id}" , method = RequestMethod.GET)
	public PlanEntity get(@PathVariable Integer id) {
		PlanEntity plan = null;
		try {
			plan = planService.get(id);
		} catch (Exception e) {
			logger.error(getClass() , e);
		}
		return plan;
	}
	
}
