package com.gymadmin.web.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gymadmin.persistence.entities.PaymentStateEntity;
import com.gymadmin.services.PaymentStateService;

@RestController
@RequestMapping(value = "/api/paymentstate", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentStateResource {
	
	private static final Logger logger = Logger.getLogger(PaymentStateResource.class);

	@Autowired
	private PaymentStateService paymentStateService;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PaymentStateEntity>> getAll() {
		try {
			List<PaymentStateEntity> plansList = paymentStateService.findAll();
			return new ResponseEntity<>( plansList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(getClass().getCanonicalName() , e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}	
}
