package com.gymadmin.web.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gymadmin.persistence.entities.PlanEntity;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.repository.JSonFactory;
import com.gymadmin.services.PlanService;

@RestController
@RequestMapping(value = "/api/plan", produces = MediaType.APPLICATION_JSON_VALUE)
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
	public ResponseEntity<?> get(@PathVariable Integer id) {
		try {
			PlanEntity plan = planService.get(id);
			return new ResponseEntity<>(plan , HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			planService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (ConstraintViolationException | DataIntegrityViolationException e) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(e.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody PlanEntity e) {
		try {
			PlanEntity p = planService.create(e);
			return new ResponseEntity<>(p , HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody PlanEntity e) {
		try {
			PlanEntity p = planService.edit(e);
			return new ResponseEntity<>(p , HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
