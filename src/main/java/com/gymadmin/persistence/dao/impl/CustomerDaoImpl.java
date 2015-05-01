package com.gymadmin.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.CustomerDao;
import com.gymadmin.persistence.entities.CustomerEntity;

/**
 *
 * @author mrodriguez
 */
@Component("customerDao")
public class CustomerDaoImpl extends DaoImpl<Integer , CustomerEntity> implements CustomerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> findAllByState(Boolean active) {
		Query namedQuery = em.createNamedQuery("CustomerEntity.findAllByState");
		namedQuery.setParameter("active", active);
		return (List<CustomerEntity>)namedQuery.getResultList();
	}
    
}
