package com.gymadmin.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.PaymentDao;
import com.gymadmin.persistence.dao.PaymentStateDao;
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
	private PaymentStateDao paymentStateDao;
	
	public List<PaymentEntity> findAll() {

		List<PaymentEntity> users = paymentDao.findAll();	

		return users;
	}

	public List<PaymentEntity> findByFilters(Map<String, String> filters) {
		
		return null;
	}

	public PaymentEntity create(PaymentEntity d) throws Exception {
		
		d.setPaymentDate(new Date()); 
		d.setPaymentDueDate(DateTimeUtil.addDays(new Date(), d.getPaymentPlan().getMonthsCount()));
		d.setState(paymentStateDao.findById(1));
		
		paymentDao.persist(d);
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
			paymentDao.remove(entity);		
		} catch (Exception e) {
			logger.error(getClass().getCanonicalName() , e);
			throw new BusinessException("Error al eliminar plan");
		}
		
	}

}
