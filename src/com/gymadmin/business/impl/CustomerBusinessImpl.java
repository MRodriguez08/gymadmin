package com.gymadmin.business.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gymadmin.business.CustomerBusiness;
import com.gymadmin.persistence.PersistenceFactory;
import com.gymadmin.persistence.dao.CustomerDao;
import com.gymadmin.persistence.dao.PaymentDao;
import com.gymadmin.persistence.dao.PaymentStateDao;
import com.gymadmin.persistence.entities.CustomerEntity;
import com.gymadmin.persistence.entities.PaymentEntity;
import com.gymadmin.persistence.entities.PaymentStateEntity;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.repository.HibernateUtil;


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
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	List<CustomerEntity> entities = null;
    	try {
    		entities = customerDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}   
    	return entities;
    }
    
    public List<CustomerEntity> findByFilters(Map<String , String> filters){
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	List<CustomerEntity> entities = null;
    	try {
    		entities = customerDao.findByFilters(filters);
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}   
    	return entities;
    }    

    public void create(CustomerEntity d) throws Exception {
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	try {
    		
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
    			//firstPayment.setState(state);
    		}
    		
    		d.setRegistrationDate(new Date());
    		customerDao.persist(d);    		
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}   
    }
    

    public void edit(CustomerEntity d) throws Exception {
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	try {    		
    		customerDao.merge(d);
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}  
    }
    

    public CustomerEntity get(Integer id) throws Exception {
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	CustomerEntity entity = null;
    	try {    		
    		entity = customerDao.findById(id);
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} 
    	return entity;
    }
    

    public void delete(Integer id) throws Exception {
        
      
    }
    
}
