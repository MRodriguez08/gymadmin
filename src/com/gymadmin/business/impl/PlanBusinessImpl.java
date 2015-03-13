package com.gymadmin.business.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import com.gymadmin.business.PlanBusiness;
import com.gymadmin.persistence.dao.PlanDao;
import com.gymadmin.persistence.entities.PlanEntity;
import com.gymadmin.persistence.PersistenceFactory;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.repository.HibernateUtil;


/**
 *
 * @author mrodriguez
 */
@ComponentScan
public class PlanBusinessImpl implements PlanBusiness {
	
	private static Logger logger = Logger.getLogger(PlanBusinessImpl.class);
    
    private PlanDao planDao = null;
    
	@Autowired
	public PlanBusinessImpl(PlanDao planDao) {
		this.planDao = planDao;
	}
    
    public List<PlanEntity> findAll(){
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	List<PlanEntity> users = null;
    	try {
    		users = planDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}   
    	return users;
    }
    
    public List<PlanEntity> findByFilters(Map<String , String> filters){
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	List<PlanEntity> users = null;
    	try {
    		users = planDao.findByFilters(filters);
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}   
    	return users;
    }    

    public void create(PlanEntity d) throws Exception {
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	try {
    		
    		PlanEntity p = planDao.findByName(d.getName());
    		if (p != null)
    			throw new BusinessException("Ya existe un plan con ese nombre");
    		
    		planDao.persist(d);    		
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}   
    }
    

    public void edit(PlanEntity d) throws Exception {
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	try {    		
    		planDao.merge(d);
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}  
    }
    

    public PlanEntity get(Integer id) throws Exception {
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	PlanEntity entity = null;
    	try {    		
    		entity = planDao.findById(id);
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} finally{
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} 
    	return entity;
    }
    
    public PlanEntity get(String name) throws Exception {
    	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    	PlanEntity entity = null;
    	try {    		
    		entity = planDao.findByName(name);
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