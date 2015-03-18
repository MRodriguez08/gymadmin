package com.gymadmin.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.gymadmin.persistence.PersistenceFactory;
import com.gymadmin.persistence.dao.CustomerDao;
import com.gymadmin.persistence.dao.PaymentDao;
import com.gymadmin.persistence.dao.PaymentStateDao;
import com.gymadmin.persistence.entities.CustomerEntity;
import com.gymadmin.persistence.entities.PaymentEntity;
import com.gymadmin.persistence.entities.PaymentStateEntity;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.services.CustomerBusiness;


/**
 *
 * @author mrodriguez
 */
public class CustomerBusinessImpl implements CustomerBusiness {
	
	private static Logger logger = Logger.getLogger(CustomerBusinessImpl.class);
    
    private CustomerDao customerDao = null;
    private PaymentDao paymmentDao = null;
    private PaymentStateDao paymmentStateDao = null;
    
    public CustomerBusinessImpl(){
    	customerDao = PersistenceFactory.getCustomerDao();
    	paymmentDao = PersistenceFactory.getPaymentDao();
    	paymmentStateDao = PersistenceFactory.getPaymentStateDao();
    }
    
    public List<CustomerEntity> findAll(){
    	List<CustomerEntity> entities = customerDao.findAll();		
    	return entities;
    }
    
    public List<CustomerEntity> findByFilters(Map<String , String> filters){
    	List<CustomerEntity> entities = customerDao.findByFilters(filters);
		
    	return entities;
    }    

    public void create(CustomerEntity d) throws Exception {    	
    		
		CustomerEntity p = customerDao.findByName(d.getName());
		if (p != null)
			throw new BusinessException("Ya existe un plan con ese nombre");
		
		if (d.getCreateFirstPayment()){
			PaymentEntity firstPayment = new PaymentEntity();
			
			//make the calculations
			Date dueDate = new Date();
			firstPayment.setPaymentDueDate(dueDate);
			
			firstPayment.setCustomer(d);
			firstPayment.setPlan(d.getCurrentPlan());
			PaymentStateEntity paymentState = paymmentStateDao.findById(3);
		}
		
		d.setRegistrationDate(new Date());
		customerDao.persist(d);    		
		
    }
    

    public void edit(CustomerEntity d) throws Exception { 		
		customerDao.merge(d);		
    }
    

    public CustomerEntity get(Integer id) throws Exception {    	
    	CustomerEntity entity = customerDao.findById(id);		
    	return entity;
    }
    

    public void delete(Integer id) throws Exception {
        
      
    }
    
}
