package com.gymadmin.web.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gymadmin.persistence.entities.PlanEntity;
import com.gymadmin.services.PlanService;

@RestController
@RequestMapping("/api/plan")
public class PlanResource {
	
	private static final Logger logger = Logger.getLogger(PlanResource.class);

	@Autowired
	private PlanService planService;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PlanEntity>> getAll() {
		try {
			List<PlanEntity> plansList = planService.findAll();
			return new ResponseEntity<>( plansList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(getClass().getCanonicalName() , e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
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
