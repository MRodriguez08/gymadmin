package com.gymadmin.web.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gymadmin.persistence.entities.PaymentEntity;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.repository.JSonFactory;
import com.gymadmin.services.PaymentService;

@RestController
@RequestMapping(value = "/api/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentResource {
	
	private static final Logger logger = Logger.getLogger(PaymentResource.class);

	@Autowired
	private PaymentService paymentService;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		try {
			List<PaymentEntity> plansList = paymentService.findAll();
			return new ResponseEntity<>( plansList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(getClass().getCanonicalName() , e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@RequestMapping(value="/{id}" , method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Integer id) {
		try {
			PaymentEntity plan = paymentService.get(id);
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
			paymentService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody PaymentEntity e) {
		try {
			PaymentEntity p = paymentService.create(e);
			return new ResponseEntity<>(p , HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody PaymentEntity e) {
		try {
			PaymentEntity p = paymentService.edit(e);
			return new ResponseEntity<>(p , HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
