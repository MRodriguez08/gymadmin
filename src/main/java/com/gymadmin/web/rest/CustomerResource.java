package com.gymadmin.web.rest;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

import com.gymadmin.config.Constants;
import com.gymadmin.persistence.entities.CustomerEntity;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.repository.JSonFactory;
import com.gymadmin.services.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
	
	private static final Logger logger = Logger.getLogger(CustomerResource.class);

	@Autowired
	private CustomerService customerService;	
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll() {
		try {
			List<CustomerEntity> list = customerService.findAll();
			return new ResponseEntity<>( list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(getClass().getCanonicalName() , e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@RequestMapping(value="/{id}" , method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> get(@PathVariable Integer id) {
		try {
			CustomerEntity plan = customerService.get(id);
			return new ResponseEntity<>(plan , HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/{id}" , method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			customerService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(HttpServletRequest request, @RequestBody CustomerEntity e) {
		try {
			e.setImage((String)request.getSession().getAttribute(Constants.PENDING_FILE_SESS_VAR));
			CustomerEntity p = customerService.create(e);
			return new ResponseEntity<>(p , HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}  finally{
			request.getSession().setAttribute(Constants.PENDING_FILE_SESS_VAR , null);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update( HttpServletRequest request, @RequestBody CustomerEntity e) {
		try {
			if (StringUtils.isEmpty(e.getImage())){
				e.setImage((String)request.getSession().getAttribute(Constants.PENDING_FILE_SESS_VAR));
			}
			CustomerEntity p = customerService.edit(e);
			return new ResponseEntity<>(p , HttpStatus.OK);
		} catch (BusinessException ex) {
			return new ResponseEntity<>(JSonFactory.createSimpleMessage(ex.getMessage()) , HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			logger.error(getClass().getCanonicalName() , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally{
			request.getSession().setAttribute(Constants.PENDING_FILE_SESS_VAR , null);
		}
	}
}
