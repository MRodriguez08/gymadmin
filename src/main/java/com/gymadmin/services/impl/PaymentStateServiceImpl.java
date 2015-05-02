package com.gymadmin.services.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.PaymentStateDao;
import com.gymadmin.persistence.entities.PaymentStateEntity;
import com.gymadmin.services.PaymentStateService;

/**
 *
 * @author mrodriguez
 */
@Component("paymentStateService")
public class PaymentStateServiceImpl implements PaymentStateService {

	private static Logger logger = Logger.getLogger(PaymentStateServiceImpl.class);

	@Autowired	
	private PaymentStateDao paymentStateDao;

	public List<PaymentStateEntity> findAll() {
		List<PaymentStateEntity> list = paymentStateDao.findAll();
		return list;
	}

	public List<PaymentStateEntity> findByFilters(Map<String, String> filters) {

		return null;
	}

	public PaymentStateEntity create(PaymentStateEntity d) throws Exception {


		return null;
	}

	public PaymentStateEntity edit(PaymentStateEntity d) throws Exception {

		return null;
	}

	public PaymentStateEntity get(Integer id) throws Exception {	
		PaymentStateEntity entity = paymentStateDao.findById(id);		
		return entity;
	}

	public void delete(Integer id) throws Exception {
		
	}

}
