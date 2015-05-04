package com.gymadmin.services.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gymadmin.config.Constants;
import com.gymadmin.persistence.dao.CustomerDao;
import com.gymadmin.persistence.entities.CustomerEntity;
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
	
	@Autowired
	private ServletContext servletContext;
    
    public CustomerServiceImpl(){

    }
    
    public List<CustomerEntity> findAll(){
    	List<CustomerEntity> entities = customerDao.findAll();
    	return entities;
    }

    public CustomerEntity create(CustomerEntity d) throws Exception {		
    	
		d.setRegistrationDate(new Date().getTime());
		d.setImage(createAvatar(d.getImage()));
		customerDao.persist(d);
		
		return d;
    }
    

    public CustomerEntity edit(CustomerEntity d) throws Exception { 
   	
		CustomerEntity check = customerDao.findById(d.getId());
		if ( d.getImage() != null && !d.getImage().equals(check.getImage()) ) {
			d.setImage(updateAvatar(check.getImage() , d.getImage()));
		}
    	
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
        	entity.setActive(false);
        	customerDao.merge(entity);
		} catch (Exception e) {
			logger.error(getClass().getCanonicalName() , e);
			throw new BusinessException("Error al eliminar cliente");
		}      
    }
    
    private String updateAvatar(String oldFile, String newfile) throws Exception{
    	
    	String imagesPath = servletContext.getRealPath(Constants.CUSTOMERS_IMAGE_PATH);
    	String oldImage = imagesPath + "/" + oldFile;
		File file = new File(oldImage);				 
		if(file.delete())
			logger.error("Image " + oldImage + " couldn't be deleted");
		
		File oldf =new File(imagesPath + "/" + newfile);
		File newf =new File(imagesPath + "/linked_" + newfile);
 
		if(oldf.renameTo(newf)){
			logger.info("Rename succesful");
			return newf.getName();
		} else {
			throw new Exception("Rename failed");
		}    		
    }
    
    private String createAvatar(String file) throws Exception {
    	
    	String imagesPath = servletContext.getRealPath(Constants.CUSTOMERS_IMAGE_PATH);		
		File oldf =new File(imagesPath + "/" + file);
		File newf =new File(imagesPath + "/linked_" + file);
 
		if(oldf.renameTo(newf)){
			logger.info("Rename succesful");
			return newf.getName();
		} else {
			throw new Exception("Rename failed");
		}    		
    }
    
}
