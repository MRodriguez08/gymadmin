package com.gymadmin.business.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mrodriguez.commons.exceptions.BusinessException;
import com.mrodriguez.commons.persistence.HibernateUtil;
import com.gymadmin.business.PaymentMethodBusiness;
import com.gymadmin.persistence.dao.PaymentMethodDao;
import com.gymadmin.persistence.entities.PaymentMethodEntity;
import com.gymadmin.persistence.PersistenceFactory;


/**
 *
 * @author mrodriguez
 */
public class PaymentMethodBusinessImpl implements PaymentMethodBusiness {
	
	private static Logger logger = Logger.getLogger(PaymentMethodBusinessImpl.class);
    
    private PaymentMethodDao paymentMethodDao = null;
    
    public PaymentMethodBusinessImpl(){
    	paymentMethodDao = PersistenceFactory.getPaymentMethodDao();
    }
    
    public List<PaymentMethodEntity> findAll(){
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	List<PaymentMethodEntity> users = null;
    	try {
    		users = paymentMethodDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}   
    	return users;
    }
    
    public List<PaymentMethodEntity> findByFilters(Map<String , String> filters){
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	List<PaymentMethodEntity> users = null;
    	try {
    		users = paymentMethodDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}   
    	return users;
    }    

    public void create(PaymentMethodEntity d) throws Exception {
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	try {
    		
    		PaymentMethodEntity check = paymentMethodDao.findByName(d.getName());
    		if (check != null)
    			throw new BusinessException("Ya existe un modo de pago con este nombre");
    		
    		paymentMethodDao.persist(d);    		
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}   
    }
    

    public void edit(PaymentMethodEntity d) throws Exception {
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	try {
    		
    		PaymentMethodEntity check = paymentMethodDao.findByName(d.getName());
    		if (check != null && check.getId() != d.getId())
    			throw new BusinessException("Ya existe un modo de pago con este nombre");
    		
    		paymentMethodDao.merge(d);
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}  
    }
    

    public PaymentMethodEntity get(Integer id) throws Exception {
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	PaymentMethodEntity entity = null;
    	try {    		
    		entity = paymentMethodDao.findById(id);
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
