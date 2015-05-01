package com.gymadmin.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gymadmin.config.Constants;
import com.gymadmin.persistence.dao.CustomerDao;
import com.gymadmin.persistence.dao.PaymentDao;
import com.gymadmin.persistence.dao.PaymentStateDao;
import com.gymadmin.persistence.dao.SysParamDao;
import com.gymadmin.persistence.entities.CustomerEntity;
import com.gymadmin.persistence.entities.PaymentEntity;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.repository.DateTimeUtil;
import com.gymadmin.services.PaymentService;

/**
 *
 * @author mrodriguez
 */
@Component("paymentService")
public class PaymentServiceImpl implements PaymentService {

	private static Logger logger = Logger.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private SysParamDao sysParamDao;

	@Autowired
	private PaymentStateDao paymentStateDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	public List<PaymentEntity> findAll() {

		List<PaymentEntity> users = paymentDao.findAll();

		return users;
	}

	public List<PaymentEntity> findByFilters(Map<String, String> filters) {
		
		return null;
	}

	public PaymentEntity create(PaymentEntity d) throws Exception {
		
		
		return d;
	}

	public PaymentEntity edit(PaymentEntity d) throws Exception {

		
		paymentDao.merge(d);
		return d;
	}

	public PaymentEntity get(Integer id) throws Exception {
		PaymentEntity entity = paymentDao.findById(id);		
		return entity;
	}

	public void delete(Integer id) throws Exception {
		try {
			PaymentEntity entity = paymentDao.findById(id);
			entity.setState(paymentStateDao.findById(Constants.PAYMENT_STATE_CANCELLED));
			paymentDao.merge(entity);		
		} catch (Exception e) {
			logger.error(getClass().getCanonicalName() , e);
			throw new BusinessException("Error al eliminar plan");
		}
		
	}
	
	@Scheduled(fixedDelay = 5000)
    public void generatePayments(){
    	try {
    		logger.info("Generating payments...");   		
    		Integer dueDateMonthDay =  Integer.valueOf(sysParamDao.findById(Constants.SYSPARAM_DUE_DATE_MONTH_DAY).getValue());
    		
    		List<CustomerEntity> customers = customerDao.findAllByState(true);
    		for (CustomerEntity e : customers){
    			logger.info("Checking payments for " + e.getName());
				
    			//Si no hay cuotas pendientes o por vencer tengo que generar una nueva factura
    			List<PaymentEntity> activePayments = paymentDao.findActiveByCustomer(e.getId());
    			if (activePayments.size() == 0){
    				
    				PaymentEntity newPayment = new PaymentEntity();
    				newPayment.setPaymentGenerationDate(new Date());
    				newPayment.setCustomer(e);
    				newPayment.setPlan(e.getPlan());
    				newPayment.setValidCost(e.getPlan().getCost());
    				newPayment.setPaymentDueDate(DateTimeUtil.getNextDueDate(e.getPaymentPlan().getMonthsCount(), dueDateMonthDay)); 
    				newPayment.setState(paymentStateDao.findById(Constants.PAYMENT_STATE_PENDING));
    				
    				paymentDao.persist(newPayment);
    			}   			
    			
    		}			
		} catch (Exception e) {
			logger.error(getClass().getCanonicalName() , e);
		}
    }

	public void updatePaymentsState() {
		
		
	}

}
