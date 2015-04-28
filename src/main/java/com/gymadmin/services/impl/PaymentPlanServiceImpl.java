package com.gymadmin.services.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.PaymentPlanDao;
import com.gymadmin.persistence.entities.PaymentPlanEntity;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.services.PaymentPlanService;

/**
 *
 * @author mrodriguez
 */
@Component("paymentPlanService")
public class PaymentPlanServiceImpl implements PaymentPlanService {

	private static Logger logger = Logger.getLogger(PaymentPlanServiceImpl.class);

	@Autowired	
	private PaymentPlanDao paymentPlanDao;

	public List<PaymentPlanEntity> findAll() {
		List<PaymentPlanEntity> users = paymentPlanDao.findAll();
		return users;
	}

	public List<PaymentPlanEntity> findByFilters(Map<String, String> filters) {
		List<PaymentPlanEntity> users = paymentPlanDao.findAll();

		return users;
	}

	public PaymentPlanEntity create(PaymentPlanEntity d) throws Exception {

		PaymentPlanEntity check = paymentPlanDao.findByName(d.getName());
		if (check != null)
			throw new BusinessException(
					"Ya existe un modo de pago con este nombre");

		paymentPlanDao.persist(d);
		return d;
	}

	public PaymentPlanEntity edit(PaymentPlanEntity d) throws Exception {

		PaymentPlanEntity check = paymentPlanDao.findByName(d.getName());
		if (check != null && check.getId() != d.getId())
			throw new BusinessException("Ya existe un modo de pago con este nombre");

		paymentPlanDao.merge(d);
		return d;
	}

	public PaymentPlanEntity get(Integer id) throws Exception {	
		PaymentPlanEntity entity = paymentPlanDao.findById(id);		
		return entity;
	}

	public void delete(Integer id) throws Exception {
		try {
			paymentPlanDao.remove(paymentPlanDao.findById(id));
		} catch (Exception e) {
			logger.error(getClass().getCanonicalName() , e);
			throw new BusinessException("Error eliminando modo de pago");
		}
	}

}
