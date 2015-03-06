package com.gymadmin.persistence.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.gymadmin.persistence.dao.CustomerDao;
import com.gymadmin.persistence.entities.CustomerEntity;
import com.gymadmin.repository.HibernateUtil;

/**
 *
 * @author mrodriguez
 */
public class CustomerDaoImpl extends DaoImpl<Integer , CustomerEntity> implements CustomerDao {
    
    public CustomerDaoImpl(){
        super();
    }
    
    public CustomerEntity findByName(String name) {		
		Query namedQuery = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("PlanEntity.findByName");
		namedQuery.setParameter("name", name);
		return (CustomerEntity)namedQuery.uniqueResult();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> findByFilters(Map<String, String> filters) {		
		Query namedQuery = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("CustomerEntity.findByFilters");
		namedQuery.setParameter("name", "%" + filters.get("name").toUpperCase() + "%");
		namedQuery.setParameter("surname", "%" + filters.get("surname").toUpperCase() + "%");
		namedQuery.setParameter("email", "%" + filters.get("email").toUpperCase() + "%");
		return (List<CustomerEntity>)namedQuery.list();
	}
    
}
