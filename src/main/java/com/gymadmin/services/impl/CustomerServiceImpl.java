package com.gymadmin.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gymadmin.persistence.PersistenceFactory;
import com.gymadmin.persistence.dao.CustomerDao;
import com.gymadmin.persistence.dao.PaymentDao;
import com.gymadmin.persistence.dao.PaymentStateDao;
import com.gymadmin.persistence.entities.CustomerEntity;
import com.gymadmin.persistence.entities.PaymentEntity;
import com.gymadmin.persistence.entities.PaymentStateEntity;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.services.CustomerService;


/**
 *
 * @author mrodriguez
 */
@Component("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	private static Logger logger = Logger.getLogger(CustomerServiceImpl.class);
    
	@Autowired
    private CustomerDao customerDao;
    
    
    //private PaymentDao paymmentDao = null;
    //private PaymentStateDao paymmentStateDao = null;
    
    public CustomerServiceImpl(){
    	customerDao = PersistenceFactory.getCustomerDao();
    	//paymmentDao = PersistenceFactory.getPaymentDao();
    	//paymmentStateDao = PersistenceFactory.getPaymentStateDao();
    }
    
    public List<CustomerEntity> findAll(){
    	List<CustomerEntity> entities = customerDao.findAll();
    	return entities;
    }

    public CustomerEntity create(CustomerEntity d) throws Exception {		
		d.setRegistrationDate(new Date().getTime());
		customerDao.persist(d);    		
		return d;
    }
    

    public CustomerEntity edit(CustomerEntity d) throws Exception { 		
		customerDao.merge(d);
		return d;
    }
    

    public CustomerEntity get(Integer id) throws Exception {    	
    	CustomerEntity entity = customerDao.findById(id);		
    	return entity;
    }
    

    public void delete(Integer id) throws Exception {
        try {
        	CustomerEntity entity = customerDao.findById(id);
        	customerDao.remove(entity);
		} catch (Exception e) {
			logger.error(getClass().getCanonicalName() , e);
			throw new BusinessException("Error al eliminar cliente");
		}
      
    }
    
}
