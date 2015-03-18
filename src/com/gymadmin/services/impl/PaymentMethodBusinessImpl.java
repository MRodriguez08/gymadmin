package com.gymadmin.services.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gymadmin.persistence.dao.PaymentMethodDao;
import com.gymadmin.persistence.entities.PaymentMethodEntity;
import com.gymadmin.persistence.PersistenceFactory;
import com.gymadmin.repository.BusinessException;
import com.gymadmin.services.PaymentMethodBusiness;

/**
 *
 * @author mrodriguez
 */
public class PaymentMethodBusinessImpl implements PaymentMethodBusiness {

	private static Logger logger = Logger
			.getLogger(PaymentMethodBusinessImpl.class);

	private PaymentMethodDao paymentMethodDao = null;

	public PaymentMethodBusinessImpl() {
		paymentMethodDao = PersistenceFactory.getPaymentMethodDao();
	}

	public List<PaymentMethodEntity> findAll() {
		List<PaymentMethodEntity> users = paymentMethodDao.findAll();
		return users;
	}

	public List<PaymentMethodEntity> findByFilters(Map<String, String> filters) {
		List<PaymentMethodEntity> users = paymentMethodDao.findAll();

		return users;
	}

	public void create(PaymentMethodEntity d) throws Exception {

		PaymentMethodEntity check = paymentMethodDao.findByName(d.getName());
		if (check != null)
			throw new BusinessException(
					"Ya existe un modo de pago con este nombre");

		paymentMethodDao.persist(d);
	}

	public void edit(PaymentMethodEntity d) throws Exception {

		PaymentMethodEntity check = paymentMethodDao.findByName(d.getName());
		if (check != null && check.getId() != d.getId())
			throw new BusinessException(
					"Ya existe un modo de pago con este nombre");

		paymentMethodDao.merge(d);
	}

	public PaymentMethodEntity get(Integer id) throws Exception {	
		PaymentMethodEntity entity = paymentMethodDao.findById(id);
		
		return entity;
	}

	public void delete(Integer id) throws Exception {

	}

}
