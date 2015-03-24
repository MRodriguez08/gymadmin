package com.gymadmin.persistence;

import com.gymadmin.persistence.dao.CustomerDao;
import com.gymadmin.persistence.dao.PaymentDao;
import com.gymadmin.persistence.dao.PaymentMethodDao;
import com.gymadmin.persistence.dao.PaymentStateDao;
import com.gymadmin.persistence.dao.PlanDao;
import com.gymadmin.persistence.dao.impl.CustomerDaoImpl;
import com.gymadmin.persistence.dao.impl.PaymentDaoImpl;
import com.gymadmin.persistence.dao.impl.PaymentMethodDaoImpl;
import com.gymadmin.persistence.dao.impl.PaymentStateDaoImpl;
import com.gymadmin.persistence.dao.impl.PlanDaoImpl;

/**
 *
 * @author mrodriguez
 */
public class PersistenceFactory {
    
    public static CustomerDao getCustomerDao(){
        return new CustomerDaoImpl();
    }   
    
    public static PaymentDao getPaymentDao(){
        return new PaymentDaoImpl();
    } 
    
    public static PaymentStateDao getPaymentStateDao(){
        return new PaymentStateDaoImpl();
    }     
    
    public static PlanDao getPlanDao(){
        return new PlanDaoImpl();
    }
    
    public static PaymentMethodDao getPaymentMethodDao(){
        return new PaymentMethodDaoImpl();
    } 
    
}
